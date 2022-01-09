package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.util.Arrays;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
        for (int i = 1; i <= 5; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers" + "/" + i).get();
            String page = doc.select("form > input[type=hidden]:nth-child(2)").attr("value");
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                System.out.println(href.attr("href"));
                System.out.println(href.text());
                Element parent = td.parent();
                String parseString = parent.child(5).text();
                System.out.println(parseString);
                System.out.println("Java_LocalDateTime: "
                        + sqlRuDateTimeParser.parse(parseString));
                System.out.println("Page:" + page);
                System.out.println();
            }
        }
        System.out.println(
                getDetails("https://www.sql.ru/forum/1341077/programmist-delphi-c-so-znaniem-angliyskogo-na-udalenku"));
    }
    public static String getDetails(String string) throws IOException {
        Document doc = Jsoup.connect(string).get();
        return  "Описание : " + System.lineSeparator()
        + doc.select("#content-wrapper-forum > table:nth-child(4) > tbody > tr:nth-child(2) > td:nth-child(2)").text()
        + System.lineSeparator()
                + System.lineSeparator()
                + "Дата : " + System.lineSeparator()
                + doc.select("#content-wrapper-forum > table:nth-child(4) > tbody > tr:nth-child(3) > td")
                .text().substring(0, 16);

    }
}
