package com.example.timemanager.entities;

import java.time.LocalDate;

public class Task {
    private double totalHours;
    private LocalDate deadline;

    public Task(double totalHours, LocalDate deadline) {
        this.totalHours = totalHours;
        this.deadline = deadline;
    }

    // getters and setters
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
