package com.example.timemanager.entities;

import java.time.LocalDate;
import java.util.Random;

public class Task {
    private double totalHours;
    private LocalDate deadline;
    private int id;

    public Task(double totalHours, LocalDate deadline) {
        this.totalHours = totalHours;
        this.deadline = deadline;
        Random rand = new Random();
        this.id = rand.nextInt(10000);
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
