package speditionapp.commands;

import java.util.List;

import speditionapp.context.DriverContext;
import speditionapp.models.Driver;

public class DriversList implements Command {

    @Override
    public void execute() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        DriverContext context = new DriverContext();
        List<Driver> drivers = context.getDrivers();
        for (Driver driver : drivers) {
            System.out.printf("%d. %s %s Avalible: %b, %s, %s, %s\n", driver.getId(), driver.getName(), driver.getSurname(), driver.getAvalibility(), driver.getCurrlocation(), driver.getNrtel(), driver.getEmail());
        }
    }
    
}
