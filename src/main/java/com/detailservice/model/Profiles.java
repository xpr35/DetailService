package com.detailservice.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Profiles {
    private int total;

    private List<Profile> results;

    {
        total = 0;
        results = Collections.synchronizedList(new ArrayList<Profile>());
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Profile> getResults() {
        return results;
    }

    public void setResults(List<Profile> results) {
        this.results = results;
    }

    public synchronized void addProfile(Profile profile) {
        this.results.add(profile);
        total++;
    }

    @Override
    public String toString() {
        return "Profiles{" +
                "total=" + total +
                ", results=" + results +
                '}';
    }
}
