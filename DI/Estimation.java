package DI;

import java.util.Scanner;

public class Estimation {
    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        
        System.out.println("type 'derivation' or 'integration': ");
        while (scnr.hasNext()) {
            String ans = scnr.nextLine();
            if (ans.equals("q")) {
                System.out.println("You have successfully quit the program.");
                System.exit(0);
            } else if (ans.equals("derivation")) {

            } else if (ans.equals("integration")) {

            } else {
                System.out.println("Not a valid input");
            }
            System.out.println("type 'derivation' or 'integration': ");
        }
        scnr.close();
    }

    public static float integrate() {
        return 69;
    }

    public static float derivate() {
        return 69;
    }
}
