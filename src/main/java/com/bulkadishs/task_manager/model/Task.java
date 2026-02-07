package com.bulkadishs.task_manager.model;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate date;
    private boolean isCompleted;

    public Task(String title, String description, LocalDate date, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.isCompleted = isCompleted;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Task{name: " + title + ", description: " + description + "}";
    }
}
