package ru.job4j;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.text.ParseException;

public interface Grab {
    void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException, IOException, ParseException;
}
