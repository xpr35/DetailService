package com.detailservice.service;

import com.detailservice.dao.CellDao;
import com.detailservice.model.Cell;
import com.detailservice.model.Profile;
import com.detailservice.model.Profiles;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class DetailService {

    private static final Logger log = LogManager.getLogger(DetailService.class);

    private static String profileServiceURL = "https://randomuser.me/api/?phone=1111111111&inc=name,email";

    public Profiles getDetails(Long cellId) throws PersistenceException, InterruptedException {

        final CellDao dao = new CellDao();
        final Profiles profiles = new Profiles();

        List<Cell> cells = dao.getUsers(cellId);

        List<Thread> threads = new ArrayList<Thread>();

        for (Cell c : cells) {
            class ProfileRequesterThread implements Runnable {
                private Cell cell;
                private Profile profile;

                ProfileRequesterThread(Cell cell) {
                    this.cell = cell;
                }

                public void run() {
                    ProfileService randomProfile = new ProfileService(profileServiceURL, 5);
                    try {
                        randomProfile.requestProfile();
                    } catch (Exception e) {
                        log.warn("Couldn't request profile for " + this.cell.getCtn());
                    }
                    this.profile = randomProfile.getProfile();
                    this.profile.setCtn(cell.getCtn());
                    profiles.addProfile(this.profile);
                }
            }

            Thread profileThread = new Thread(new ProfileRequesterThread(c));
            profileThread.start();
            threads.add(profileThread);
        }

        for (Thread thread : threads) {
            thread.join();
        }
        return profiles;
    }
}
