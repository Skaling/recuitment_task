

import java.io.IOException;
import java.util.ArrayList;



public class main {


    public static void main(String[] args) throws IOException {

        JsonService service = new JsonService();
        HtmlAnalyzerService analyzerService = new HtmlAnalyzerService();

        String sites_list_json = service.getSiteList("FC Barcelona");

        ArrayList urlList;

        urlList = service.getArrayList(sites_list_json);
        String best_matching_url = analyzerService.findTheBestUrl(urlList);

        System.out.println(best_matching_url);

    }
}
