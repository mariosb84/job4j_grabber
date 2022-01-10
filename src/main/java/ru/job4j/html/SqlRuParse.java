package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.Post;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
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
                getDetails("https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t"));
    }
    public static Post getDetails(String string) throws IOException {
        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
        Document doc = Jsoup.connect(string).get();
        String title = doc.select(".messageHeader").get(0).ownText();
        String description = doc.select(".msgBody").get(1).text();
        LocalDateTime created = sqlRuDateTimeParser.parse(doc.select(".msgFooter").get(0).text().substring(0, 16));
        return new Post(title, string, description, created);
    }
}
