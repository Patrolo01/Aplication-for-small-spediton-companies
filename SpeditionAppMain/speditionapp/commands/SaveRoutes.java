package speditionapp.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import speditionapp.context.RouteContext;
import speditionapp.models.Route;

public class SaveRoutes implements Command{

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        System.out.print("Pass the name of the file to which routes will be saved\n?");
        var stdin = new Scanner(System.in);
        var filename = stdin.nextLine();
        RouteContext context = new RouteContext();
        List<Route> routes = context.getRoutes();
        Gson gson = new Gson();
        String Jsonstring = gson.toJson(routes);
        try {
            FileWriter file = new FileWriter(filename);
            file.write(Jsonstring);
            file.flush();
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        
    }
    
}
