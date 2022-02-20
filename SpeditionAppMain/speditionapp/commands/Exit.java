package speditionapp.commands;

public class Exit implements Command {
    @Override
    public void execute() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        // TODO Auto-generated method stub
        System.out.print("Program is exiting...");
        System.exit(0);
    }
}
