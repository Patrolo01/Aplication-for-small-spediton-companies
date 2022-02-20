package speditionapp.commands;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

import speditionapp.context.DriverContext;
import speditionapp.context.RouteContext;
import speditionapp.models.Driver;
import speditionapp.models.Route;

public class addDriverToRoute implements Command {

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        ShowAvalibleDrivers showavaldrivers = new ShowAvalibleDrivers();
        RoutesList showroutes = new RoutesList();
        RouteContext routecontext = new RouteContext();
        List<Route> routes = routecontext.getRoutes();
        if(routes.size() == 0){
            System.out.print("There are no routes to choose please add some\n");
            return;
        }
        DriverContext drivercontext = new DriverContext();
        List<Driver> drivers = drivercontext.getDrivers();
        if(drivers.size() == 0){
            System.out.print("There are no drivers to choose please add some\n");
            return;
        }
        List<Driver> avaldrivers = drivercontext.getAvaliblDrivers();
        int idofselectedroute = -1;
        int idofselecteddriver = -1;
        String confirmed = "";
        Route selectedroute = routes.get(0);
        Driver selecteddriver = drivers.get(0);
        while(!confirmed.equals("y")){
            showroutes.execute();
            
            Scanner stdin = new Scanner(System.in);
            System.out.print("Pass id of route you want to link to the driver\n?");
            idofselectedroute = Integer.parseInt(stdin.nextLine()) - 1;
            selectedroute = routes.get(idofselectedroute);
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.printf("%d. %s-%s %dkm\n", selectedroute.getId(), selectedroute.getStart(), selectedroute.getEnd(), selectedroute.getDistance());
            System.out.print("Press y to confirm selection\n?");
            confirmed = stdin.nextLine().toLowerCase();
        }
        System.out.printf("You have selected route number: %d", idofselectedroute+1);
        
        // ToDo 
        // find closest driver to route
        confirmed = "";
        while(!confirmed.equals("y")){
            showavaldrivers.execute();
            
            Scanner stdin = new Scanner(System.in);
            System.out.print("Pass id of driver you want to link to the route\n?");
            idofselecteddriver = Integer.parseInt(stdin.nextLine()) - 1;
            selecteddriver = drivers.get(idofselectedroute);
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.printf("%d. %s %s Avalible: %b, %s, %s, %s\n", selecteddriver.getId(), selecteddriver.getName(), selecteddriver.getSurname(), selecteddriver.getAvalibility(), selecteddriver.getCurrlocation(), selecteddriver.getNrtel(), selecteddriver.getEmail());
            System.out.print("Press y to confirm selection\n?");
            confirmed = stdin.nextLine().toLowerCase();
        }
        System.out.printf("You have selected driver number: %d", idofselectedroute+1);
        selectedroute.setDriver(selecteddriver);
        selecteddriver.setAvalibility(false);
    }
    
}
