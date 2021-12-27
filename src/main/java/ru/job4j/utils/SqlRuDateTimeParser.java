package ru.job4j.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(
           /* Map.entry("1", "янв"),
            Map.entry("2", "фев"),
            Map.entry("3", "мар"),
            Map.entry("4", "апр"),
            Map.entry("5", "май"),
            Map.entry("6", "июн"),
            Map.entry("7", "июл"),
            Map.entry("8", "авг"),
            Map.entry("9", "сен"),
            Map.entry("10", "окт"),
            Map.entry("11", "ноя"),
            Map.entry("12", "дек")*/);

    @Override
    public LocalDateTime parse(String parse) {
       return LocalDateTime.parse(parse,
               DateTimeFormatter.ofPattern("d MMM yy, HH:m"));
    }
}

