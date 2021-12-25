package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");
        /*Elements row2 = doc.select(".altCol");*/
        for (Element td : row) {
            Element href = td.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            Element parent = td.parent();
            /*System.out.println(parent.tag());
            System.out.println(parent.children().size());
            System.out.println(parent.children().get(5).text());*/
            System.out.println(parent.child(5).text());
        }
    }
}
