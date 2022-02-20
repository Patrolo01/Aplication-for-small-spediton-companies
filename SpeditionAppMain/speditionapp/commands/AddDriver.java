package speditionapp.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import speditionapp.context.DriverContext;
import speditionapp.models.Driver;

public class AddDriver implements Command{

    @Override
    public void execute() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        Scanner stdin = new Scanner(System.in);
        Driver driver = new Driver();
        Class clazz = Driver.class;
        List<Method> methods= Arrays.asList(clazz.getMethods());
        for (Method method : methods) {
            if(method.getName().startsWith("set")){
                System.out.printf("Please pass the %s of the new Driver\n?", method.getName().substring(3));
                Type[] type = method.getGenericParameterTypes();
            //    System.out.print(type[0].getClass());
            //    System.out.print(type[0].getTypeName();
                if(type[0].equals(String.class)){
                    String input = stdin.nextLine();
                    try {
                        method.invoke(driver, input);
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }   
                }
                if(type[0].equals(Integer.class))
                {
                    int input = Integer.parseInt(stdin.nextLine());
                    try {
                        method.invoke(driver, input );
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if(type[0].equals(Boolean.class))
                {
                    boolean input = Boolean.parseBoolean(stdin.nextLine());
                    try {
                        method.invoke(driver, input );
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        DriverContext context = new DriverContext();
        context.getDrivers().add(driver);
        context.actualizeAvalDrivers();
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.print("Driver succesfully added\n"); 
    } 
}
