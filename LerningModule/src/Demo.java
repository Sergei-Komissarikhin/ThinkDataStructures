import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        String url = "http://en.wikipedia.org/wiki/Java_(programming_language)";

        //Загрузка и парсинг документа
        Connection conn = Jsoup.connect(url);
        Document doc = conn.get();

        //выбирает текст контента и разделяет его по параграфам
        Element content = doc.getElementById("mw-content-text");
        Elements paragraphs = content.select("a");

        Iterator<Element> nodeIterator = paragraphs.iterator();
        while(nodeIterator.hasNext()){

            Element node = nodeIterator.next();
                if(node.parent().tag().toString().equals("i")) {
                    System.out.println(node);
                }
        }


//        Element firstPara = paragraphs.get(0);
//
//        //Запуск рекурсивного метода
//        recursiveDFS(firstPara);

        //Запуск итеративного метода
        //iterativeDFS(firstPara);

    }
    //Рекурсивный метод поиска в глубину
    private static void recursiveDFS(Node node){
        if(node instanceof TextNode){
            System.out.print(node);
        }

        for(Node child: node.childNodes()){
            recursiveDFS(child);
        }
    }

    //Итеративный метод поиска в глубину
    private static void iterativeDFS(Node root){
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            if (node instanceof TextNode){
                System.out.print(node);
            }

            List<Node> nodes = new ArrayList<>(node.childNodes());
            Collections.reverse(nodes);

            for(Node child: nodes){
                stack.push(child);
            }
        }
    }


}

