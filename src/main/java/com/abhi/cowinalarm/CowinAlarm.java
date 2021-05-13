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
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CowinAlarm {
    private static final Logger log = LoggerFactory.getLogger(CowinAlarm.class);

//    private String pincode[] = {"400701", "400703", "400705", "400708", "400709"},
//            date[] = {"14-05-2021", "15-05-2021", "16-05-2021", "17-05-2021"};

    private String pincode[] = {"400701", "400703", "400705", "400708", "400709"},
            date[] = {"14-05-2021"};

    @Scheduled(fixedDelay = 5000)
    public void scheduleFixedDelayTask() {
        Arrays.stream(pincode).forEach(p -> {
                    Arrays.stream(date).forEach(d -> {
                                HttpResponse<String> resp = null;
                                try {
                                    resp = getResp(p, d);
                                } catch (UnirestException e) {
                                    e.printStackTrace();
                                }
                                if (resp.getStatus() != 200) {
                                    log.info("Pincode: {}, Date: {}, Error while calling the API: {}",
                                            p, d, resp.getStatusText());
                                    return;
                                }
                                RootResp data = getRootResp(resp);
                                if (data.sessions.size() > 0) {
                                    dataCheck(data.sessions, p, d);
                                }
                            }
                    );
                }
        );
    }

    private RootResp getRootResp(HttpResponse<String> resp) {
        log.info(resp.getBody().toString());
        return new Gson().fromJson(resp.getBody(), RootResp.class);
    }

    private HttpResponse<String> getResp(String pincode, String date) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        return Unirest.get("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?" +
                "pincode=" + pincode + "&date=" + date)
                .header("accept", "application/json")
                .header("Accept-Language", "hi_IN")
                .header("User-Agent", "PostmanRuntime/7.26.10")
                .asString();
    }

    private void dataCheck(List<Session> sessionList, String pincode, String date) {
        if (sessionList.size() > 0) {
            Session temp = sessionList.get(0);
            log.info("\nPincode: {}, Date: {}, Min-Age:{}+, capacity: {}, fee: {}, vaccine: {}",
                    pincode, date, temp.getMin_age_limit(), temp.getAvailable_capacity(), temp.getFee(), temp.getVaccine());
            play();
        }
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