package com.example.timemanager.entities;

import java.time.LocalDate;

public class Day {
    private LocalDate date;
    private int busyHours;
    private int totalHours;
    private DayType type;

    public Day(LocalDate date, int busyHours, int totalHours, DayType type) {
        this.date = date;
        this.busyHours = busyHours;
        this.totalHours = totalHours;
        this.type = type;
    }

    // getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getBusyHours() {
        return busyHours;
    }

    public void setBusyHours(int busyHours) {
        this.busyHours = busyHours;
    }

    public void addBusyHours (double add){
        busyHours += add;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public DayType getType() {
        return type;
    }

    public void setType(DayType type) {
        this.type = type;
    }

    public int getAvailableHours(){
        return totalHours - busyHours;
    }
}

