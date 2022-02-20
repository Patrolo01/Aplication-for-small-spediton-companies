package speditionapp.context;

import java.util.ArrayList;
import java.util.List;

import speditionapp.models.Driver;

public class DriverContext {
    private static List<Driver> drivers;
    private static List<Driver> avalibledrivers;

    public DriverContext(){
        if(drivers == null){
            drivers = new ArrayList<Driver>();
            avalibledrivers = new ArrayList<Driver>();
        }
    }
    public static List<Driver> getDrivers() {
        return drivers;
    }
    public static List<Driver> getAvaliblDrivers(){
        return avalibledrivers;
    }
    public void setDrivers(List<Driver> drivers){
        this.drivers = drivers;
    }
    public void actualizeAvalDrivers(){
        for (Driver driver : drivers) {
            if(driver.getAvalibility().equals(true) && !avalibledrivers.contains(driver)){
                avalibledrivers.add(driver);
            }
        }
    }

}
