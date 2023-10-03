import java.util.*;

public class Hovedprogram {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hvor mange rader skal det ha?");
        int x = sc.nextInt();
        System.out.println("Hvor mange kolonner skal det ha?");
        int y = sc.nextInt();
        GameOfLifeGUI GUI = new GameOfLifeGUI(x, y);
        sc.close();
    }
}
