package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.Parse;
import ru.job4j.Post;
import ru.job4j.utils.DateTimeParser;
import ru.job4j.utils.SqlRuDateTimeParser;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws Exception {
        SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser());
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
                        + sqlRuParse.dateTimeParser.parse(parseString));
                System.out.println("Page:" + page);
                System.out.println();
            }
        }
        System.out.println(
               // getDetails("https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t"));
        getDetails("https://www.sql.ru/forum/1338739/ishhu-s-developer-v-msk"));
        System.out.println();
        System.out.println("Метод Detail: ");
        System.out.println(sqlRuParse.detail("https://www.sql.ru/forum/1196621/shpargalki"));
        System.out.println();
        System.out.println("Метод List: ");
        System.out.println(sqlRuParse.list("https://www.sql.ru/forum/job-offers"));
    }

    public static Post getDetails(String string) throws IOException {
        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
        Document doc = Jsoup.connect(string).get();
        String title = doc.select(".messageHeader").get(0).ownText();
        String description = doc.select(".msgBody").get(1).text();
        LocalDateTime created = sqlRuDateTimeParser.parse(doc.select(".msgFooter").get(0).text().substring(0, 16));
        return new Post(title, string, description, created);
    }

    @Override
    public List<Post> list(String link) throws IOException, ParseException {
        ArrayList<Post> result = new ArrayList<>();
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                result.add(detail(href.attr("href")));
            }
        return result;
    }

    @Override
    public Post detail(String link) throws IOException, ParseException {
        Document doc = Jsoup.connect(link).get();
        String title = doc.select(".messageHeader").get(0).ownText();
        String description = doc.select(".msgBody").get(1).text();
        LocalDateTime created = dateTimeParser.parse(doc.select(".msgFooter").get(0).text().substring(0, 16));
        return new Post(title, link, description, created);
    }
}
