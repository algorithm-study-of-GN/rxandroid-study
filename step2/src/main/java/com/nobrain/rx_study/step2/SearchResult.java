package com.nobrain.rx_study.step2;


import java.util.List;

public class SearchResult {

    public SearchChannel channel;

    public static class SearchChannel {
        public List<SearchImage> item;
    }

    public static class SearchImage {
        public String title;
        public String thumbnail;

        @Override
        public String toString() {
            return "SearchImage{" +
                    "title='" + title + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    '}';
        }
    }
}
