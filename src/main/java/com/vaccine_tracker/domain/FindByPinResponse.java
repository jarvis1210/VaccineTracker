package com.vaccine_tracker.domain;

import java.util.List;


public class FindByPinResponse {
    private List<Session> sessions;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}

