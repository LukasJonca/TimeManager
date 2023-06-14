package com.example.timemanager;

import com.example.timemanager.entities.Calendar;
import com.example.timemanager.entities.Day;
import com.example.timemanager.entities.DayType;
import com.example.timemanager.entities.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TimeManagerController {

    @PostMapping("/schedule")
    public ResponseEntity<CalculationResult> calculateSchedule(@RequestBody ScheduleRequest request) {
        Calendar calendar = new Calendar();
        calendar.setDays(request.getDays());

        boolean isAllTasksScheduled = true;

        for(Task task : request.getTasks()) {
            for(Day day : calendar.getDays()) {
                if(day.getAvailableHours() > 0) {
                    // Assign as many hours from the task to the day as possible
                    double taskHours = Math.min(day.getAvailableHours(), task.getTotalHours());
                    calendar.assignTaskHours(task, day);

                    // If the task is not fully scheduled, set isAllTasksScheduled to false
                    if(task.getTotalHours() > 0) {
                        isAllTasksScheduled = false;
                    }

                    // Break if the task is fully scheduled
                    if(task.getTotalHours() == 0) {
                        break;
                    }
                }
            }
        }

        // Create a CalculationResult object to hold the days and the task completion status
        CalculationResult result = new CalculationResult();
        result.setDays(calendar.getDays());
        result.setIsTaskFullyScheduled(isAllTasksScheduled);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        // Retrieve all tasks logic here
        // ...
        List<Day> days = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

        // Populate the lists with some test data
        days.add(new Day(LocalDate.of(2023, Month.JUNE, 9), 2, 16, DayType.WORKDAY));
        days.add(new Day(LocalDate.of(2023, Month.JUNE, 10), 2, 16, DayType.WEEKEND));
        days.add(new Day(LocalDate.of(2023, Month.JUNE, 11), 0, 16, DayType.HOLIDAY));

        tasks.add(new Task(5, LocalDate.of(2023, Month.JUNE, 10)));
        tasks.add(new Task(6, LocalDate.of(2023, Month.JUNE, 11)));
        tasks.add(new Task(3, LocalDate.of(2023, Month.JUNE, 12)));

        Calendar calendar = new Calendar(days, tasks);

        return ResponseEntity.ok(tasks);
    }

    static class ScheduleRequest {
        private List<Task> tasks;
        private List<Day> days;

        public List<Task> getTasks() {
            return tasks;
        }

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
        }

        public List<Day> getDays() {
            return days;
        }

        public void setDays(List<Day> days) {
            this.days = days;
        }
    }

    public class CalculationResult {
        private List<Day> days;
        private boolean isTaskFullyScheduled;

        public List<Day> getDays() {
            return days;
        }

        public void setDays(List<Day> days) {
            this.days = days;
        }

        public boolean getIsTaskFullyScheduled() { return isTaskFullyScheduled; }

        public void setIsTaskFullyScheduled(boolean isTaskFullyScheduled) { this.isTaskFullyScheduled = isTaskFullyScheduled; }
    }


}
