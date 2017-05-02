package com.detailservice.service;

import com.detailservice.dao.CellDao;
import com.detailservice.model.Cell;
import com.detailservice.model.Profile;
import com.detailservice.model.Profiles;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DetailService {

    private static final Logger log = LogManager.getLogger(DetailService.class);

    public Profiles getDetails(Long cellId) throws PersistenceException, InterruptedException {

        final CellDao dao = new CellDao();
        final Profiles profiles = new Profiles();

        List<Cell> cells = dao.getUsers(cellId);
        ProfileService profileService = new ProfileService(15, 5);

        for (Cell cell : cells) {
            try {
                Profile pr = profileService.get(cell).get();
                profiles.addProfile(pr);
            } catch (ExecutionException e) {
                log.error("Execution exception while getting profile: " + e.getMessage());
            }
        }
        return profiles;
    }
}
