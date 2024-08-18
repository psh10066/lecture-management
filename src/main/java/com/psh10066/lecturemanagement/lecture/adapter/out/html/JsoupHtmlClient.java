package com.psh10066.lecturemanagement.lecture.adapter.out.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsoupHtmlClient implements HtmlClient {

    @Override
    public Document connect(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
