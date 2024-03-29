//package com.abhi.cowinalarm;
//
//import com.abhi.cowinalarm.model.RootResp;
//import com.abhi.cowinalarm.model.Session;
//import com.google.gson.Gson;
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author Abhishek Patil
// * A simple scheduler to check
// * vaccine availability on cowin.gov.in
// */
//@SpringBootApplication
//@EnableScheduling
//public class CowinalarmApplication {
//    private static final Logger log = LoggerFactory.getLogger(CowinalarmApplication.class);
//    private final String[] pincode = {"744302", "400709"};
//    private final String[] date = {"21-05-2021"};
//    private final boolean onlyLookingForFirstDose = true;//make this false to search for the second dose
//
//    public static void main(String[] args) {
//        SpringApplication.run(CowinalarmApplication.class, args);
//    }
//
//    @Scheduled(fixedDelay = 5000)
//    public void scheduleFixedDelayTask() {
//        Arrays.stream(pincode).forEach(p -> {
//                    Arrays.stream(date).forEach(d -> {
//                                HttpResponse<String> resp = null;
//                                try {
//                                    resp = getResp(p, d);
//                                } catch (UnirestException e) {
//                                    log.error("Error while connecting to Cowin website:" + e.getMessage(), e);
//                                }
//                                assert resp != null;
//                                if (resp.getStatus() != 200) {
//                                    log.info("Pincode: {}, Date: {}, Error while calling the API: {}",
//                                            p, d, resp.getStatusText());
//                                    return;
//                                }
//                                RootResp data = getRootResp(resp);
//                                if (data.getSessions().size() > 0) {
//                                    dataCheck(data.sessions, p, d);
//                                }
//                            }
//                    );
//                }
//        );
//    }
//
//    private RootResp getRootResp(HttpResponse<String> resp) {
//        return new Gson().fromJson(resp.getBody(), RootResp.class);
//    }
//
//    private HttpResponse<String> getResp(String pincode, String date) throws UnirestException {
//        Unirest.setTimeouts(0, 0);
//        return Unirest.get("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?" +
//                "pincode=" + pincode + "&date=" + date)
//                .header("accept", "application/json")
//                .header("Accept-Language", "hi_IN")
//                .header("User-Agent", "PostmanRuntime/7.26.10")
//                .asString();
//    }
//
//    private void dataCheck(List<Session> sessionList, String pincode, String date) {
//        if (onlyLookingForFirstDose)
//            sessionList = sessionList.stream()
//                    .filter(s -> s.getMin_age_limit() == 18)
//                    .filter(s -> s.getAvailable_capacity_dose1() > 0)
//                    .collect(Collectors.toList());
//        if (sessionList.size() > 0) {
//            play();
//            sessionList.stream().forEach(s -> logOut(pincode, date, s));
//        }
//    }
//
//    private void logOut(String pincode, String date, Session s) {
//        log.info("\nPincode: {}, Date: {}, Min-Age:{}+, capacity: {}, fee: {}, vaccine: {}",
//                pincode, date, s.getMin_age_limit(),
//                onlyLookingForFirstDose ? s.getAvailable_capacity_dose1() : s.getAvailable_capacity(), s.getFee(),
//                s.getVaccine());
//    }
//
//    private void play() {
//        try {
//            JFXPanel j = new JFXPanel();
//            String uriString = new ClassPathResource("nuke_alarm.mp3").getFile().toURI().toString();
//            MediaPlayer Player = new MediaPlayer(new Media(uriString));
//            Player.play();
//        } catch (Exception ex) {
//            log.error("Error while playing media" + ex.getMessage(), ex);
//        }
//    }
//
//}
