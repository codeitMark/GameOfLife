import java.util.*;

public class GameOfLife {
    Verden verden;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hvor mange rader skal det ha?");
        int x = sc.nextInt();
        System.out.println("Hvor mange kolonner skal det ha?");
        int y = sc.nextInt();
        Verden verden = new Verden(x, y);
        verden.tegn();
        System.out.println("Press enter for å fortsette, q for å avslutte");
        String input = sc.nextLine();
        input = sc.nextLine();
        while (!input.equals("q")){
            verden.oppdatering();
            verden.tegn();
            System.out.println("Press enter for å fortsette, q for å avslutte");
            input = sc.nextLine();
        }
        sc.close();
    }
}
