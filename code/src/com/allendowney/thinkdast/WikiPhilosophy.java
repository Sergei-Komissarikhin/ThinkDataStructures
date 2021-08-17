package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPhilosophy {

    final static List<String> visited = new ArrayList<String>();
    final static WikiFetcher wf = new WikiFetcher();

    /**
     * Tests a conjecture about Wikipedia and Philosophy.
     *
     * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
     *
     * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String destination = "https://en.wikipedia.org/wiki/Philosophy";
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";

        testConjecture(destination, source, 20);
    }

    /**
     * Starts from given URL and follows first link until it finds the destination or exceeds the limit.
     *
     * @param destination
     * @param source
     * @throws IOException
     */
    public static void testConjecture(String destination, String source, int limit) throws IOException {
        // TODO: FILL THIS IN!

        String url = source;

        for(int i = 0; i < limit; i++){

            if(url.equals(destination)){
                System.out.println("****************I found it!********************");
                break;
            }

            if(!visited.contains(url)) {
                visited.add(url);
            }else{
                System.out.println("Error. I was here.");
                break;
            }

            Elements elements = wf.fetchWikipedia(url);
            WikiParser wp = new WikiParser(elements);
            Element element = wp.findFirstLink();

            if(element == null){
                System.out.println("I don't find valid link!");
                return;
            }else {
                System.out.println(element.text());
                url = "https://en.wikipedia.org" + element.attr("href");
            }






        }

        visited.forEach(System.out::println);


    }
}
