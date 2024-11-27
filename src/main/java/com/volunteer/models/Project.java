package com.volunteer.models;

import java.sql.Date;

public class Project {
    private int id;
    private String title;
    private String description;
    private Date start_date;
    private Date end_date;
    private int created_By;

    public Project() {}
    public Project(int id, String title, String description, String start_date, String end_date, int created_By) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start_date = Date.valueOf(start_date);
        this.end_date = Date.valueOf(end_date);
        this.created_By = created_By;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getCreated_By() {
        return created_By;
    }

    public void setCreated_By(int created_By) {
        this.created_By = created_By;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", created_By=" + created_By +
                '}';
    }
}
