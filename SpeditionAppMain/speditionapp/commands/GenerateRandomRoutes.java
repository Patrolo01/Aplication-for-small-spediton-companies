package speditionapp.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import speditionapp.context.RouteContext;
import speditionapp.models.Route;

public class GenerateRandomRoutes implements Command{

    @Override
    public void execute() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        // TODO Auto-generated method stub
        RouteContext context = new RouteContext();
        List<String> cities = new ArrayList<String>();
        try (BufferedReader citiesreader = new BufferedReader(new FileReader("cities.txt"))) {
            for(int x = 0; x<99; x++){
                cities.add(citiesreader.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.print("Please pass how many random routes you want to be generated\n?");
        Scanner stdin = new Scanner(System.in);
        int noofroutestobegenerated = Integer.parseInt(stdin.nextLine());
        for(int x=0; x<noofroutestobegenerated; x++){
            Route route = new Route();
            int distance = (int)Math.floor(Math.random()*(500-30+1)+30);
            String start = cities.get(new Random().nextInt(99));
            String end = cities.get(new Random().nextInt(99));
            while(start.equals(end)){
                end = cities.get(new Random().nextInt(99));
            }
            route.setDistance(distance);
            route.setStart(start);
            route.setEnd(end);
            context.getRoutes().add(route);
        }
    }
    
}
