package in.create.arena.blogapp.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class DemoClass {

    static int loadTime = 0;

    private LinuxFileHandling linuxHandler;

    static {
        loadTime++;
        System.out.println(String.format("Class initializing for %d times", loadTime));
    }

    public DemoClass() {
        linuxHandler = new LinuxFileHandling();
    }

    public static void main(String[] args) throws ParseException {
        //handleDateString();
        DemoClass demoOnFriday = new DemoClass();
        demoOnFriday.linuxHandler.start();
    }

    private static void handleDateString() throws ParseException{
        String dateString = "2019-09-23T00:00:00.0000000Z";
        Instant instant = Instant.parse(dateString);
        System.out.println("Instant : " + Date.from(instant).toInstant());
        System.out.println("Instant Zone : " + instant.atZone(ZoneId.of(ZoneOffset.UTC.getId())).toLocalDate());
        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        System.out.println("LocalDate : " + result.toString());
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'");
        Date date = sDateFormat.parse(dateString);
        System.out.println(date.toString());
    }
}
