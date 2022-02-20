package speditionapp.commands;

import java.util.List;
import java.util.Scanner;

import speditionapp.context.DriverContext;
import speditionapp.models.Driver;

public class ShowAvalibleDrivers implements Command{

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Scanner stdin = new Scanner(System.in);
        DriverContext context = new DriverContext();
        List<Driver> drivers = context.getAvaliblDrivers();
        for (Driver driver : drivers) {
            System.out.printf("%d. %s %s Avalible: %b, %s, %s, %s\n", driver.getId(), driver.getName(), driver.getSurname(), driver.getAvalibility(), driver.getCurrlocation(), driver.getNrtel(), driver.getEmail());
        }
        
        // System.out.printf("\nPress Enter to continue\n?");
        // String in = stdin.nextLine();
    }
    
}
