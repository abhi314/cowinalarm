package com.abhi.cowinalarm.model;

import java.util.List;

public class RootResp {
    public List<Session> sessions;

    public RootResp() {
    }

    public RootResp(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "RootResp{" +
                "sessions=" + sessions +
                '}';
    }
}
