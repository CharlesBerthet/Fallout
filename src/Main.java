import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String nomPersonnage = "";

        while (nomPersonnage.length() < 3) {
            System.out.println("Entrez le nom de votre personnage (3 caractères minimum) : ");
            nomPersonnage = sc.nextLine();
        }

        Personnage personnage = new Personnage(nomPersonnage);

        int pointsRestant = 25;
        while (pointsRestant > 0) {
            try {
                System.out.println("Points restants : " + pointsRestant);
                System.out.println("Entrez la compétence à améliorer : ");
                for (int index = 0; index < Competence.values().length; index++) {
                    System.out.println(String.format("%d. %s", index + 1, Competence.values()[index]));
                }

                int choix = sc.nextInt();

                if (choix < 1 || choix > Competence.values().length) {
                    throw new IllegalArgumentException("Veuillez choisir un nombre entre 1 et " + Competence.values().length + " : ");
                }
                Competence competenceAChoisir = Competence.values()[choix - 1];
                System.out.println("Combien de points voulez-vous ajouter à " + competenceAChoisir + " ?");
                int points = sc.nextInt();

                try {
                    personnage.augmenterCompetence(competenceAChoisir, points);
                    pointsRestant -= points;
                } catch (IllegalArgumentException e) {
                    System.err.println("\033[0;31m" + e.getMessage() + "\033[0m");
                }

            } catch (IllegalArgumentException e) {
                System.err.println("\033[0;31m" + e.getMessage() + "\033[0m");
            }
        }
        personnage.afficherCompetences();

    }
}
