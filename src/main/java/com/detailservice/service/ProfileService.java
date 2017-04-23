package com.detailservice.service;

import com.detailservice.model.Profile;
import com.detailservice.model.RandomProfile;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileService implements Serializable {
    private static final Logger log = LogManager.getLogger(ProfileService.class);
    private String url;
    private static Object lock = new Object();
    private int maxAttemptsNumber;
    private RandomProfile randomUser;

    public ProfileService(String url, int maxAttemptsNumber) {
        this.url = url;
        this.maxAttemptsNumber = maxAttemptsNumber;
    }


    public void requestProfile() throws Exception {
        StringBuilder response = new StringBuilder();
        Gson gson = new Gson();
        int attempts = 0;
        URL obj = new URL(url);

        synchronized (lock) {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            while (responseCode != 200 && attempts < maxAttemptsNumber) {
                attempts++;
                log.warn("Trying to get profile from Profile Service. Attempt #" + attempts);
                con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                responseCode = con.getResponseCode();
            }

            if (responseCode != 200) {
                log.error("Couldn't get response from ProfileService");
                throw new Exception();
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }
        this.randomUser = gson.fromJson(response.toString(), RandomProfile.class);
    }

    public Profile getProfile() {
        Profile profile = new Profile();
        if (this.randomUser == null || this.randomUser.getResults() == null) {
            log.error("Couldn't get results from Profile Service");
            return new Profile();
        }

        RandomProfile.Results.Name name = this.randomUser.getResults()[0].getName();
        profile.setName(name.getTitle() + " " + name.getFirst() + " " + name.getLast());
        profile.setEmail(this.randomUser.getResults()[0].getEmail());
        return profile;
    }

    @Override
    public String toString() {
        return "ProfileService{" +
                "randomUser=" + randomUser +
                '}';
    }
}
