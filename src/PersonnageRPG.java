import java.util.Scanner;

public class PersonnageRPG {

    public  static void main(String[] args) {
        String nomPersonnage;

        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez le nom de votre personnage : ");
        nomPersonnage = sc.nextLine();

        System.out.println("Le nom de votre personnage est : " + nomPersonnage);

        sc.close();
    }

}
