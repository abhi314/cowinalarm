package com.abhi.cowinalarm;

import com.abhi.cowinalarm.model.RootResp;
import com.abhi.cowinalarm.model.Session;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Abhishek Patil
 * A simple scheduler to check
 * vaccine availability on cowin.gov.in
 */
@SpringBootApplication
@EnableScheduling
public class CowinalarmApplicationByDistrict {
    private static final Logger log = LoggerFactory.getLogger(CowinalarmApplicationByDistrict.class);
    private final int district = 392;
    private final List<Integer> pincodes = Arrays.asList(400703, 400709, 400710, 400705, 400701);
    private final String[] date = {"06-08-2021", "07-08-2021"};

    public static void main(String[] args) {
        SpringApplication.run(CowinalarmApplicationByDistrict.class, args);
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduleFixedDelayTask() {
        Arrays.stream(date).forEach(d -> {
                    HttpResponse<String> resp = null;
                    try {
                        resp = getResp(d);
                    } catch (Exception e) {
                        log.error("Error while connecting to Cowin website:" + e.getMessage());
                    }
                    if (null == resp) {
                        log.error("Null/empty response from Cowin website");
                        return;
                    } else if (resp.getStatus() != 200) {
                        log.info("Date: {}, Error while calling the API: {}", d, resp.getStatusText());
                        return;
                    }
                    RootResp data = getRootResp(resp);
                    if (data.getSessions().size() > 0) {
                        try {
                            dataCheck(data.sessions, d);
                        } catch (InterruptedException e) {
                            log.error("Exception : " + e.getMessage());
                        }
                    }
                }
        );
    }

    private RootResp getRootResp(HttpResponse<String> resp) {
        return new Gson().fromJson(resp.getBody(), RootResp.class);
    }

    private HttpResponse<String> getResp(String date) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        return Unirest.get("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?" +
                        "district_id=" + district + "&date=" + date)
                .header("accept", "application/json")
                .header("Accept-Language", "hi_IN")
                .header("User-Agent", "PostmanRuntime/7.26.10")
                .asString();
    }

    private void dataCheck(List<Session> sessionList, String date) throws InterruptedException {
        sessionList = sessionList.stream()
                .filter(s -> s.getMin_age_limit() == 45)
                .filter(s -> pincodes.contains(s.getPincode()))
                .filter(s -> s.getAvailable_capacity_dose2() > 0)
                .filter(s -> s.getFee_type().equalsIgnoreCase("free"))
                .filter(s -> s.getVaccine().equalsIgnoreCase("COVISHIELD"))
                .collect(Collectors.toList());
        if (sessionList.size() > 0) {
            play();
            sessionList.stream().forEach(s -> logOut(s.getPincode(), date, s));
            Thread.sleep(5000);
        }
    }

    private void logOut(int pincode, String date, Session s) {
        log.info("\nPincode: {}, Date: {},  capacity: {}", pincode, date, s.getAvailable_capacity_dose2());
    }

    private void play() {
        try {
            JFXPanel j = new JFXPanel();
            String uriString = new ClassPathResource("nuke_alarm.mp3").getFile().toURI().toString();
            MediaPlayer Player = new MediaPlayer(new Media(uriString));
            Player.play();
        } catch (Exception ex) {
            log.error("Error while playing media" + ex.getMessage(), ex);
        }
    }

}
