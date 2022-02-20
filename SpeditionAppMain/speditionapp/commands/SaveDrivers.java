package speditionapp.commands;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import speditionapp.context.DriverContext;
import speditionapp.models.Driver;

public class SaveDrivers implements Command {

    @Override
    public void execute() {
        
        // TODO Auto-generated method stub
        System.out.print("Pass the name of the file to which drivers will be saved\n?");
        var stdin = new Scanner(System.in);
        var filename = stdin.nextLine();
        DriverContext context = new DriverContext();
        List<Driver> drivers = context.getDrivers();
        Gson gson = new Gson();
        String JSONString = gson.toJson(drivers);
        // System.out.print(JSONString);
        try {
            FileWriter file = new FileWriter(filename);
            file.write(JSONString);
            file.flush();
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        

        
    }
    
}
