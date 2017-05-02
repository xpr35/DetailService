package com.detailservice.service;

import com.detailservice.model.Cell;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProfileService implements Serializable {
    private static final Logger log = LogManager.getLogger(ProfileService.class);
    private static final Object lock = new Object();
    private int maxAttemptsNumber;
    private RandomProfile randomProfile;
    private ExecutorService executorService;

    private String getUrl(Long ctn) {
        return "https://randomuser.me/api/?phone=" + ctn + "&inc=name,email";
    }

    public ProfileService(int maxThreads, int maxAttemptsNumber) {
        this.maxAttemptsNumber = maxAttemptsNumber;
        this.executorService = Executors.newFixedThreadPool(maxThreads);
    }

    public Future<Profile> get(Cell cell) {
        return executorService.submit(new ProfileRequesterWorker(cell.getCtn()));
    }

    public void requestProfile(Long ctn) throws Exception {
        StringBuilder response = new StringBuilder();
        Gson gson = new Gson();
        int attempts = 0;
        URL obj = new URL(getUrl(ctn));

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
        this.randomProfile = gson.fromJson(response.toString(), RandomProfile.class);
    }

    public Profile getProfile(Long ctn) {
        try {
            this.requestProfile(ctn);
        } catch (Exception e) {
            log.warn("Couldn't request profile");
        }
        Profile profile = new Profile();
        if (this.randomProfile == null || this.randomProfile.getResults() == null) {
            log.error("Couldn't get results from Profile Service");
            return new Profile();
        }

        RandomProfile.Results.Name name = this.randomProfile.getResults()[0].getName();
        profile.setName(name.getTitle() + " " + name.getFirst() + " " + name.getLast());
        profile.setEmail(this.randomProfile.getResults()[0].getEmail());
        profile.setCtn(ctn);
        return profile;
    }

    @Override
    public String toString() {
        return "ProfileService{" +
                "randomProfile=" + randomProfile +
                '}';
    }

    class ProfileRequesterWorker implements Callable {
        private Long ctn;

        ProfileRequesterWorker(Long ctn) {
            this.ctn = ctn;
        }

        public Profile call() {
            return getProfile(this.ctn);
        }
    }
}
