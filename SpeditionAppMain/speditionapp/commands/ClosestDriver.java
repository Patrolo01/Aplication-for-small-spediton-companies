package speditionapp.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.Gson;

import speditionapp.context.DriverContext;
import speditionapp.context.RouteContext;
import speditionapp.models.Driver;
import speditionapp.models.Route;

public class ClosestDriver implements Command{

    @Override
    public void execute() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        int destinationsPerRequest = 5;
        RouteContext routecontext = new RouteContext();
        List<Route> routes = routecontext.getRoutes();
        if(routes.size() == 0){
            System.out.print("There are no routes to choose please add some\n");
            return;
        }
        RoutesList routeslist = new RoutesList();
        routeslist.execute();
        System.out.print("\nChoose route number\n?");
        Scanner stdin = new Scanner(System.in);
        int choise = Integer.parseInt(stdin.nextLine()) - 1;
        Route route = routes.get(choise);
        
        DriverContext driverContext = new DriverContext();
        List<Driver> drivers = driverContext.getAvaliblDrivers();
        if(drivers.size() == 0){
            System.out.print("There are no avalible drivers to choose please add some\n");
            return;
        }
        List<Float> distancesFromDriverToRoute = new ArrayList<>();
        
        for(int y = 0; y<(drivers.size()/destinationsPerRequest); y++){
            String jsonin = "{\"locations\": [";
            jsonin += String.format("\"%s\",", route.getStart());
            for (int x=0+(destinationsPerRequest*y); x<destinationsPerRequest+(destinationsPerRequest*y); x++) {
                jsonin += String.format("\"%s\",", drivers.get(x).getCurrlocation());
                //System.out.printf("\nx: %d, id: %d", x, drivers.get(x).getId());
            }
            jsonin += "],\"options\": {\"manyToOne\": true}}";
            try {
                List<Float> tempDistancesToAdd= request(jsonin);
                tempDistancesToAdd.remove(0);
                distancesFromDriverToRoute.addAll(tempDistancesToAdd);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for(int y = drivers.size()-(drivers.size()%destinationsPerRequest); y<drivers.size(); y++){
            String jsonin = "{\"locations\": [";
            jsonin += String.format("\"%s\",", route.getStart());
            jsonin += String.format("\"%s\",", drivers.get(y).getCurrlocation());
            //System.out.printf("\nx: %d, id: %d", y, drivers.get(y).getId());
            jsonin += "],\"options\": {\"manyToOne\": true}}";
            try {
                List<Float> tempDistancesToAdd= request(jsonin);
                tempDistancesToAdd.remove(0);
                distancesFromDriverToRoute.addAll(tempDistancesToAdd);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //System.out.print(distancesFromDriverToRoute);
        Map<Driver, Float> driversMap = new LinkedHashMap<>();

        for(int x = 0; x < drivers.size(); x++){
            driversMap.put(drivers.get(x), distancesFromDriverToRoute.get(x));
        }
        
        // for (Driver driver : keys) {
            // System.out.printf("\n%d. %s %s %s Distance to route starting point %f", driver.getId(), driver.getName(), driver.getSurname(), driver.getCurrlocation(), driversMap.get(driver));
        // }
        List<Entry<Driver, Float>> sortedDriversMap = new ArrayList<>(driversMap.entrySet());
        sortedDriversMap.sort(Entry.comparingByValue());
        for (Entry<Driver,Float> entry : sortedDriversMap) {
            Driver tempDriver = entry.getKey();
            Float tempFloat = entry.getValue();
            Double MilesToKm = tempFloat*1.609; 
            System.out.printf("\n%d. %s %s %s Distance to route starting point %f km", tempDriver.getId(), tempDriver.getName(), tempDriver.getSurname(), tempDriver.getCurrlocation(), MilesToKm);
        }
        System.out.println("\nChoose id of driver you want to add to chosen route ");
        int chosendriverid = Integer.parseInt(stdin.nextLine());
        Driver chosendriver = driverContext.getDrivers().get(chosendriverid-1);
        //System.out.println(chosendriverid + chosendriver.getName() + chosendriver.getSurname());
        route.setDriver(chosendriver);
        chosendriver.setAvalibility(false);



    }

    final List<Float> request(String string) throws IOException{
        List<Float> distancesArray = new ArrayList<Float>();
        URL url = new URL("http://www.mapquestapi.com/directions/v2/routematrix?key=iIa3lMGG9MVShJVZwkj3rAidcpMehyqH");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        int length = string.length();
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        http.connect();
        OutputStream os = http.getOutputStream();
        byte[] bytes = string.getBytes();
        os.write(bytes);
        InputStream is = http.getInputStream();
        String json = new String(is.readAllBytes());
        //System.out.print(json);
        Gson gson = new Gson();
        Request request = gson.fromJson(json, Request.class);
        List<Float> distances = request.getDistance();
        if(distances != null){
            for (Float float1 : distances) {
                distancesArray.add(float1);
            }
        }
        return distancesArray;

    }
    
}
final class Request {
    boolean allToAll;
    List<Float> distance;
    public Request(){};
    public void setAllToAll(boolean allToAll) {
        this.allToAll = allToAll;
    }
    public void setDistance(List<Float> distance) {
        this.distance = distance;
    }
    public List<Float> getDistance() {
        return distance;
    }
}