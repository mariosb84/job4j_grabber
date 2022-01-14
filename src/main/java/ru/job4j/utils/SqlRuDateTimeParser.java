package ru.job4j.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("d MM yy, HH:mm");
    private static final DateTimeFormatter FORMATTER_DATE =
            DateTimeFormatter.ofPattern("d MM yy");

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12"));

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(getFormat(parse),
                FORMATTER);

    }
    private String getFormat(String parse) {
        String resultDate;
        String[] dateTime = parse.split(",");
        String date = dateTime[0];
        String time = dateTime[1];
        if (date.equals("сегодня")) {
            resultDate = LocalDate.now().
                    format(FORMATTER_DATE);
        } else if (date.equals("вчера")) {
            resultDate = LocalDate.now().minusDays(1).
                    format(FORMATTER_DATE);
        } else {
            String[] strings = date.split(" ");
            resultDate = strings[0] + " "
                    + MONTHS.get(strings[1])
                    + " " + strings[2];
        }
        return resultDate + "," + time;
    }
}

