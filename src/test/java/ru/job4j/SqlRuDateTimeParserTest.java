package ru.job4j;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.time.LocalDate;

public class SqlRuDateTimeParserTest {

    @Test
    public void whenParseToday() {
        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
        String test = "сегодня, 23:47";
        assertThat(sqlRuDateTimeParser.parse(test).toString(),
                is(LocalDate.now().toString() + "T" + "23:47"));
    }
    @Test
    public void whenParseYesterday() {
        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
        String test = "вчера, 23:47";
        assertThat(sqlRuDateTimeParser.parse(test).toString(),
                is(LocalDate.now().minusDays(1).toString() + "T" + "23:47"));
    }
    @Test
    public void whenParseToDate() {
        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
        String test = "27 дек 21, 23:47";
        assertThat(sqlRuDateTimeParser.parse(test).toString(), is("2021-12-27T23:47"));
    }
}
