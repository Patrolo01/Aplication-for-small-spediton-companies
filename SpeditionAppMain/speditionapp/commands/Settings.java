package speditionapp.commands;

import java.util.Scanner;

import speditionapp.models.Setting;

public class Settings implements Command{

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        Setting setting = new Setting();
        Scanner stdin = new Scanner(System.in);
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.printf("Use api Setting is set to: %b", setting.getUseapi());
        System.out.printf("\nPass 1 to activate api funtion or 0 to deactivate it");
        System.out.printf("\n>");
        int choise = Integer.parseInt(stdin.nextLine());
        System.out.println(choise);
        if(choise == 1){
            setting.setUseapi(true);
            System.out.println(setting.getUseapi());
        }
        else{
            setting.setUseapi(false);
            System.out.println(setting.getUseapi());
        }
        
    }

    
}
