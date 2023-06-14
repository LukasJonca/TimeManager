package com.example.timemanager;
import com.example.timemanager.entities.Calendar;
import com.example.timemanager.entities.Day;
import com.example.timemanager.entities.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.timemanager.entities.DayType;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TimeManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimeManagerApplication.class, args);

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

        // Print out the updated days
        System.out.println("Days after assigning tasks:");
        for (Day day : calendar.getDays()) {
            System.out.println("Date: " + day.getDate() + ", Busy hours: " + day.getBusyHours() + ", Total hours: " + day.getTotalHours() + ", Type: " + day.getType());
        }
    }

}
