package com.example.timemanager;

import com.example.timemanager.entities.Calendar;
import com.example.timemanager.entities.Day;
import com.example.timemanager.entities.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
                    double taskHours = Math.min(day.getAvailableHours(), task.getTotalHours());
                    calendar.assignTaskHours(task, day);
                    System.out.println("Task " + task.getId() + " assigned " + taskHours + " hours on " + day.getDate());

                    if(task.getTotalHours() == 0) {
                        break;
                    }
                }
            }
            if(task.getTotalHours() > 0) {
                isAllTasksScheduled = false;
            }
        }

        CalculationResult result = new CalculationResult();
        result.setDays(calendar.getDays());
        result.setIsTaskFullyScheduled(isAllTasksScheduled);

        return ResponseEntity.ok(result);
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
