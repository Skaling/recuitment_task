
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


public class HtmlAnalyzerService {


    public String findTheBestUrl(ArrayList<String> urlList)  {
            ArrayList<String> topUrls = new ArrayList<>();

            try {
                for(int i=0; i<urlList.size(); i++) {
                    int index = i;
                    Document doc = Jsoup.connect(urlList.get(i)).get();
                    if(doc.getElementsByTag("table") != null) {
                        ArrayList<Element> tables = doc.getElementsByTag("table");

                        tables.forEach(table -> {

                             if(table.attr("class").equals("infobox vcard")) {
                                 if(table.getElementsByClass("image").size()>0) {
                                     ArrayList<Element> th_elements_list = table.getElementsByTag("th");
                                     List<String> th_titles = new ArrayList();

                                     for (Element element : th_elements_list) {
                                         th_titles.add(element.text());
                                     }

                                     if (th_titles.contains("Full name") && th_titles.contains("League")) {
                                         topUrls.add(urlList.get(index));
                                     }
                                 }
                             }
                        });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
        }
            return topUrls.get(0);
    }
}
