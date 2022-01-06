package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.utils.SqlRuDateTimeParser;

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
            }
        }
    }
}
