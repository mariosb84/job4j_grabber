package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.Parse;
import ru.job4j.Post;
import ru.job4j.utils.DateTimeParser;
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

    @Override
    public List<Post> list(String link) throws IOException, ParseException {
        ArrayList<Post> result = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Document doc = Jsoup.connect(link + "/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                Post post = detail(href.attr("href"));
                if (!post.getTitle().contains("JS") && (!post.getTitle().contains("javascript"))) {
                    result.add(post);
                }
            }
        }
        return result;
    }

    @Override
    public Post detail(String link) throws IOException, ParseException {
        Document doc = Jsoup.connect(link).get();
        String title = doc.select(".messageHeader").get(0).ownText();
        String description = doc.select(".msgBody").get(1).text();
        String dateTimeText = doc.select(".msgFooter").get(0).text();
        LocalDateTime created = dateTimeParser.parse(
                dateTimeText.substring(0, dateTimeText.indexOf("[")).trim());
        return new Post(title, link, description, created);
    }

}
