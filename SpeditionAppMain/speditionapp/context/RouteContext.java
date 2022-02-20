package speditionapp.context;

import java.util.ArrayList;
import java.util.List;

import speditionapp.models.Route;

public class RouteContext {
    private static List<Route> routes;

    public RouteContext(){
        if(routes == null){
            routes = new ArrayList<Route>();
        }
    }
    
    public static List<Route> getRoutes(){
        return routes;
    }

    public static void setRoutes(List<Route> routes) {
        RouteContext.routes = routes;
    }

}
