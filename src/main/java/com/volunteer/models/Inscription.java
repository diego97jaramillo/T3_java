package com.volunteer.models;

import java.sql.Date;
import java.sql.Timestamp;

public class Inscription {
    private int id;
    private int userId;
    private int projectId;
    private Date date;

    public Inscription(int userId, int projectId) {
        this.userId = userId;
        this.projectId = projectId;
        this.date = new Date(System.currentTimeMillis());
    }

    public Inscription(int id, int userId, int projectId, String date) {
        this.userId = userId;
        this.projectId = projectId;
        this.date = Date.valueOf(date);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "userId=" + userId +
                ", projectId=" + projectId +
                ", date=" + date +
                '}';
    }
}
