package speditionapp.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import speditionapp.context.DriverContext;
import speditionapp.models.Driver;

public class GenerateRandomDrivers implements Command{
    DriverContext context = new DriverContext();
    @Override
    public void execute() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        // TODO Auto-generated method stub
        DriverContext context = new DriverContext();
        List<String> names = new ArrayList<String>();
        List<String> surnames = new ArrayList<String>();
        List<String> cities = new ArrayList<String>();
        try (BufferedReader namesreader = new BufferedReader(new FileReader("names.txt"))) {
            for(int x = 0; x<48; x++){
                names.add(namesreader.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try (BufferedReader surnamesreader = new BufferedReader(new FileReader("surnames.txt"))) {
            for(int x = 0; x<49; x++){
                surnames.add(surnamesreader.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try (BufferedReader citiesreader = new BufferedReader(new FileReader("cities.txt"))) {
            for(int x = 0; x<99; x++){
                cities.add(citiesreader.readLine());
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.print(names.size());
        // System.out.print(surnames.size());
        // System.out.print(cities.size());
        System.out.print("Please pass how many random drivers you want to be generated\n?");
        Scanner stdin = new Scanner(System.in);
        int noofdriverstobegenerated = Integer.parseInt(stdin.nextLine());
        for(int x=0; x<noofdriverstobegenerated; x++){
            Driver driver = new Driver();
            String name = names.get(new Random().nextInt(48));
            String surname = surnames.get(new Random().nextInt(49));
            String currlocation = cities.get(new Random().nextInt(99));
            int nrtel = (int)Math.floor(Math.random()*(999999999-100000000+1)+100000000);
            String email = String.format("%s.%s@wp.pl",name, surname );
            Boolean avalibility = false;
            if(new Random().nextFloat() > 0.5){
                avalibility = true;
            }
            driver.setName(name);
            driver.setAvalibility(avalibility);
            driver.setCurrlocation(currlocation);
            driver.setEmail(email);
            driver.setNrtel(String.valueOf(nrtel));
            driver.setSurname(surname);
            context.getDrivers().add(driver);       
        }
        context.actualizeAvalDrivers();
    }
}
