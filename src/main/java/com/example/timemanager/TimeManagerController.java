package com.example.timemanager;

import com.example.timemanager.entities.Calendar;
import com.example.timemanager.entities.Day;
import com.example.timemanager.entities.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TimeManagerController {

    @PostMapping("/schedule")
    public ResponseEntity<List<Day>> calculateSchedule(@RequestBody ScheduleRequest request) {
        Calendar calendar = new Calendar();
        calendar.setDays(request.getDays());

        for(Task task : request.getTasks()) {
            for(Day day : calendar.getDays()) {
                if(day.getAvailableHours() > 0) {
                    // Assign as many hours from the task to the day as possible
                    double taskHours = Math.min(day.getAvailableHours(), task.getTotalHours());
                    calendar.assignTaskHours(task, day);

                    // Break if the task is fully scheduled
                    if(task.getTotalHours() == 0) {
                        break;
                    }
                }
            }
        }

        return ResponseEntity.ok(calendar.getDays());
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

}
