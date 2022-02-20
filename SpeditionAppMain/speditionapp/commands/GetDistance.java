package speditionapp.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.module.ModuleDescriptor.Requires;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.Parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import speditionapp.context.RouteContext;
import speditionapp.models.Route;

public class GetDistance implements Command {

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        RouteContext routecontext = new RouteContext();
        List<Route> routes = routecontext.getRoutes();
        URL url;
        for (Route route : routes) {
            System.out.printf("%s --> %s\n", route.getStart(), route.getEnd());
            try {
                url = new URL(String.format("http://www.mapquestapi.com/directions/v2/route?key=iIa3lMGG9MVShJVZwkj3rAidcpMehyqH&from=%s&to=%s", route.getStart(), route.getEnd()));
                HttpURLConnection con;
                try {
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String string = in.lines().collect(Collectors.joining());
                    JsonParser praser = new JsonParser();
                    JsonElement jsontree = praser.parse(string);
                    JsonElement z =  jsontree.getAsJsonObject().get("route").getAsJsonObject().get("distance");
                    System.out.print(z.getAsFloat());
                    route.setDistance(z.getAsInt());    
                    


                    // System.out.print(string);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        }
        
    }
    
}
