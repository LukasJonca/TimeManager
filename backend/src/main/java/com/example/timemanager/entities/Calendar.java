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
    // Methods to add days and tasks
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
            day.addBusyHours(day.getAvailableHours());
            task.setTotalHours(task.getTotalHours() - day.getAvailableHours());
        }
    }

    // Method to assign tasks to days
    public void assignTasks() {
        // Sort tasks by deadline
        tasks.sort(Comparator.comparing(Task::getDeadline));

        for (Task task : tasks) {
            for (Day day : days) {
                if (day.getDate().isBefore(task.getDeadline()) || day.getDate().isEqual(task.getDeadline())) {
                    if (day.getAvailableHours() >= task.getTotalHours()) {
                        // Assign the task to the day and reduce the available hours

                        break;
                    } else {
                        // If the task can't be completed in one day, divide it between multiple days
                        task.setTotalHours(task.getTotalHours() - day.getAvailableHours());
                        day.addBusyHours(day.getAvailableHours() - day.getBusyHours());
                    }
                }
            }
        }
    }

    // Method to check if all tasks can be completed within their deadlines
    public boolean canCompleteAllTasks() {
        for (Task task : tasks) {
            if (task.getTotalHours() > 0) {
                return false;
            }
        }
        return true;
    }


}

