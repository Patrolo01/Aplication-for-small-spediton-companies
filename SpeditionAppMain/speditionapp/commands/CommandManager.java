package speditionapp.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.BranchElement;

import speditionapp.models.Setting;

public class CommandManager {
    private Map<String, Command> commands;
    private Setting settings = new Setting();

    public CommandManager(){
        commands = new HashMap<>();
        addCommand(new Exit());
        addCommand(new AddDriver());
        addCommand(new AddRoute());
        addCommand(new RoutesList());
        addCommand(new DriversList());
        addCommand(new SaveDrivers());
        addCommand(new SaveRoutes());
        addCommand(new LoadDrivers());
        addCommand(new LoadRoutes());
        addCommand(new addDriverToRoute());
        addCommand(new ShowAvalibleDrivers());
        addCommand(new GenerateRandomDrivers());
        addCommand(new GenerateRandomRoutes());
        addCommand(new GetDistance());
        addCommand(new ClosestDriver());
        addCommand(new Settings());
    }

    private void addCommand(Command command){
        Class clazz = command.getClass();
        String commandLabel = clazz.getSimpleName().toLowerCase();
        commands.put(commandLabel, command);
    }
    public void loopdev(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.print("You succesfully choose Dev platform");
        Scanner stdin = new Scanner(System.in);
        System.out.print(">");
        while(stdin.hasNextLine()){
            String command = stdin.nextLine();
            if(commands.containsKey(command)){
                commands.get(command).execute();
            }
            else{
                System.out.print(command + "\n");
                System.out.print("Command does not exists\n");
            }
            System.out.print(">");
        }
        
    }
    public void loopuser(){
        Scanner stdin = new Scanner(System.in);
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.print("You succesfully choose User platform");
        String choise = "";
        String in;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while(choise != "exit"){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.print("1. Add Driver\n");
            System.out.print("2. Add Route\n");
            System.out.print("3. Show all drivers\n");
            System.out.print("4. Show avalible drivers\n");
            System.out.print("5. Show all routes\n");
            System.out.print("6. Assign driver to the route\n");
            System.out.print("7. Settings\n");
            System.out.print("S. Save routes and drivers to datafile\n");
            System.out.print("L. Load routes and drivers from datafile\n");
            System.out.print("!!! FOR TESTING PURPOSE ONLY !!!\n");
            System.out.print("8. Generate random drivers\n");
            System.out.print("9. Generate random routes\n>");
            choise = stdin.nextLine().toUpperCase();
            switch(choise){
                case "1":
                    commands.get("adddriver").execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                case "2":
                    commands.get("addroute").execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                case "3":
                    commands.get("driverslist").execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                case "4":
                    commands.get("showavalibledrivers").execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                case "5":
                    new RoutesList().execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                case "6":
                    if(settings.getUseapi() == false){
                        new addDriverToRoute().execute();
                    }
                    else{
                        new ClosestDriver().execute();
                    }
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    
                    break;
                case "7":
                    commands.get("settings").execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;

                case "S":
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
                    new SaveDrivers().execute();
                    new SaveRoutes().execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                case "L":
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
                    new LoadDrivers().execute();
                    new LoadRoutes().execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                case "8":
                    new GenerateRandomDrivers().execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                case "9":
                    new GenerateRandomRoutes().execute();
                    System.out.printf("\nPress Enter to continue\n?");
                    in = stdin.nextLine();
                    break;
                default:
                    System.out.print("\nYou have selected unexisting option\n>");
            }
        }
        commands.get("exit").execute();
    }
}
