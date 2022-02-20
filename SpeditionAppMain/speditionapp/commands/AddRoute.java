package speditionapp.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import speditionapp.context.RouteContext;
import speditionapp.models.Route;
import speditionapp.models.Setting;

public class AddRoute implements Command {

    @Override
    public void execute() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Random random = new Random();
        Setting settings = new Setting();
        // TODO Auto-generated method stub
        Scanner stdin = new Scanner(System.in);
        URL url;
        Route route = new Route();
        System.out.print("Please pass the Start of the new Route\n?");
        String start = stdin.nextLine();
        route.setStart(start);
        System.out.print("Please pass the End of the new Route\n?");
        String end = stdin.nextLine();
        route.setEnd(end);
        if (settings.getUseapi() == true) {
            try {
                url = new URL(String.format(
                        "http://www.mapquestapi.com/directions/v2/route?key=iIa3lMGG9MVShJVZwkj3rAidcpMehyqH&from=%s&to=%s",
                        route.getStart(), route.getEnd()));
                HttpURLConnection con;
                try {
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String string = in.lines().collect(Collectors.joining());
                    JsonParser praser = new JsonParser();
                    JsonElement jsontree = praser.parse(string);
                    JsonElement z = jsontree.getAsJsonObject().get("route").getAsJsonObject().get("distance");
                    System.out.printf("Lenght of the given route is %d km\n", z.getAsInt());
                    route.setDistance((int) Math.round(z.getAsInt()*1.609));

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
        else{
            System.out.print("Please pass the Distance of the new Route\n?");
            int distance = Integer.parseInt(stdin.nextLine());
            route.setDistance(distance);
        }

        // System.out.print("Please pass the Distance of the new Route\n?");
        // int distance = Integer.parseInt(stdin.nextLine());
        // route.setDistance(distance);
        RouteContext context = new RouteContext();
        context.getRoutes().add(route);
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.print("Route added succesfully\n");

    }
}
