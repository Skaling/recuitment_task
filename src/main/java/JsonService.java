import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonService {

    private  ResteasyClient client;
    private  ResteasyWebTarget target;


    public String getSiteList(String place) {
        client = new ResteasyClientBuilder().build();
        target = client.target("https://en.wikipedia.org/w/api.php")
                .queryParam("action", "query")
                .queryParam("generator", "search")
                .queryParam("gsrlimit", "10")
                .queryParam("gsrqiprofile", "classic_noboostlinks")
                .queryParam("format", "json")
                .queryParam("prop", "info")
                .queryParam("inprop","url")
                .queryParam("gsrsearch", place);



        Response response = target.request().get();
        String value = response.readEntity(String.class);

        response.close();

        return value;
    }

    public ArrayList getArrayList(String sites_list) {

        ObjectMapper om = new ObjectMapper();
        JsonNode node = null;

        try {
            node = om.readTree(sites_list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<JsonNode> users = node.findValues("fullurl");

        ArrayList<String> arrayLinks = new ArrayList<String>();

        for(JsonNode jsonNode: users) {
            arrayLinks.add(jsonNode.getTextValue());
        }

        return arrayLinks;
    }


}
