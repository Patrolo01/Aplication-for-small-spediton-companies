package speditionapp.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import speditionapp.context.DriverContext;
import speditionapp.models.Driver;

public class LoadDrivers implements Command{

    @Override
    public void execute() {
        
        // TODO Auto-generated method stub
        System.out.print("Pass the name of the file from which drivers will be loaded\n?");
        var stdin = new Scanner(System.in);
        var filename = stdin.nextLine();

        DriverContext context = new DriverContext();
        Gson gson = new Gson();
        try (JsonReader reader = new JsonReader(new FileReader(filename))) {
            Type type = new TypeToken<ArrayList<Driver>>(){}.getType();
            List<Driver> drivers = gson.fromJson(reader, type);
            context.setDrivers(drivers);
            context.actualizeAvalDrivers();
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        




    }
    
}
