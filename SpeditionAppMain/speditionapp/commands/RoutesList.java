package speditionapp.commands;

import java.util.List;

import speditionapp.context.RouteContext;
import speditionapp.models.Route;

public class RoutesList implements Command{
    
        @Override
        public void execute() {
            // TODO Auto-generated method stub
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            RouteContext context = new RouteContext();
            List<Route> routes = context.getRoutes();
            for (Route route : routes) {
                if(route.getDriver() != null){
                    System.out.printf("%d. %s-%s %dkm Assigned Driver: %d. %s %s\n", route.getId(), route.getStart(), route.getEnd(), route.getDistance(), route.getDriver().getId(), route.getDriver().getName(), route.getDriver().getSurname());
                }
                else{
                    System.out.printf("%d. %s-%s %dkm\n", route.getId(), route.getStart(), route.getEnd(), route.getDistance());
                }
            }
        }
}