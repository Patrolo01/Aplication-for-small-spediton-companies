package speditionapp.commands;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import speditionapp.context.RouteContext;
import speditionapp.models.Route;

public class LoadRoutes implements Command{

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        System.out.print("Pass the name of the file from which routes will be loaded\n?");
        var stdin = new Scanner(System.in);
        var filename = stdin.nextLine();
        RouteContext context = new RouteContext();
        List<Route> routes = context.getRoutes();
        Gson gson = new Gson();
        try (FileReader file = new FileReader(filename)) {
            Type type = new TypeToken<ArrayList<Route>>(){}.getType();
            JsonReader jsonreader = new JsonReader(file);
            List<Route> loadedroutes = gson.fromJson(jsonreader, type);
            System.out.print(loadedroutes);
            routes.addAll(loadedroutes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    
}
