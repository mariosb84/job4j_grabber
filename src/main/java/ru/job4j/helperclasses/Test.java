package ru.job4j.helperclasses;


import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
       /* System.out.println(new ReadProperties2().read());
        System.out.println(new ReadProperties3().read());*/

        ZoneOffset contractualOffset = ZoneOffset.UTC;
        String stringWeVeGot = "Fri, 07 Aug 2020 18:00:00 +0000";
        LocalDateTime convertedDateTime = OffsetDateTime
                .parse(stringWeVeGot, DateTimeFormatter.RFC_1123_DATE_TIME)
                .withOffsetSameInstant(contractualOffset)
                .toLocalDateTime();
        System.out.println(convertedDateTime);
    }
    public static class ReadProperties2 {
        public int read() {
            String path = "rabbit.properties";
            Config config = new Config(path);
            config.load();
            return Integer.parseInt(config.value("rabbit.interval"));
        }
    }
    public static class ReadProperties3 {
        public String read() {
            String path = "rabbit.properties";
            Config config = new Config(path);
            config.load();
            return config.value("rabbit.interval");
        }
    }
}
