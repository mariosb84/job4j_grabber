package ru.job4j.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "янв."),
            Map.entry("февр", "февр."),
            Map.entry("мар", "мар."),
            Map.entry("апр", "апр."),
            Map.entry("май", "мая"),
            Map.entry("июн", "июн."),
            Map.entry("июл", "июл."),
            Map.entry("авг", "авг."),
            Map.entry("сен", "сент."),
            Map.entry("окт", "окт."),
            Map.entry("ноя", "нояб."),
            Map.entry("дек", "дек."));

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(getFormat(parse),
                DateTimeFormatter.ofPattern("d MMM yy, HH:mm"));

    }
    private String getFormat(String parse) {
        String resultDate;
        String myFormatDate = "d MMM yy";
        String[] dateTime = parse.split(",");
        String date = dateTime[0];
        String time = dateTime[1];
        if (date.equals("сегодня")) {
            resultDate = LocalDate.now().
                    format(DateTimeFormatter.
                            ofPattern(myFormatDate));
        } else if (date.equals("вчера")) {
            resultDate = LocalDate.now().minusDays(1).
                    format(DateTimeFormatter.
                            ofPattern(myFormatDate));
        } else {
            String[] strings = date.split(" ");
            resultDate = strings[0] + " "
                    + MONTHS.get(strings[1])
                    + " " + strings[2];
        }
        return resultDate + "," + time;
    }
}

