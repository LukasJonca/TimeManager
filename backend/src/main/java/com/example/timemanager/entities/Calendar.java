package com.example.timemanager.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Calendar {
    private List<Day> days;
    private List<Task> tasks;

    public Calendar() {
        this.days = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public Calendar(List<Day> days, List<Task> tasks) {
        this.days = days;
        this.tasks = tasks;
    }
    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void addDay(Day day) {
        this.days.add(day);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void assignTaskHours(Task task, Day day){
        if (task.getTotalHours() <= day.getAvailableHours()) {
            day.addBusyHours(task.getTotalHours());
            task.setTotalHours(0);
        } else {
            task.setTotalHours(task.getTotalHours() - day.getAvailableHours());
            day.addBusyHours(day.getAvailableHours());
        }
    }

    public void assignTasks() {
        tasks.sort(Comparator.comparing(Task::getDeadline));

        for (Task task : tasks) {
            for (Day day : days) {
                if (day.getDate().isBefore(task.getDeadline()) || day.getDate().isEqual(task.getDeadline())) {
                    if (day.getAvailableHours() >= task.getTotalHours()) {
                        break;
                    } else {
                        task.setTotalHours(task.getTotalHours() - day.getAvailableHours());
                        day.addBusyHours(day.getAvailableHours() - day.getBusyHours());
                    }
                }
            }
        }
    }

    public boolean canCompleteAllTasks() {
        for (Task task : tasks) {
            if (task.getTotalHours() > 0) {
                return false;
            }
        }
        return true;
    }

}

