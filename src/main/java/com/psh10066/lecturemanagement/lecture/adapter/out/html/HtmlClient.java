package com.psh10066.lecturemanagement.lecture.adapter.out.html;

import org.jsoup.nodes.Document;

public interface HtmlClient {

    Document connect(String url);
}
