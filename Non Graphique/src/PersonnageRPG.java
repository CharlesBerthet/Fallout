import java.util.Scanner;

public class PersonnageRPG {

    public  static void main(String[] args) {
        String nomPersonnage;

        int agilite = 0;
        int force = 0;
        int intelligence = 0;
        int pointsRestant = 25;

        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez le nom de votre personnage : ");
        nomPersonnage = sc.nextLine();

        System.out.println("Le nom de votre personnage est : " + nomPersonnage);

        while (pointsRestant > 0) {
            try {
                System.out.println("Points restants à répartir : " + pointsRestant);
                System.out.println("1. Augmenter la force");
                System.out.println("2. Augmenter l'agilité");
                System.out.println("3. Augmenter l'intelligence");
                System.out.println("Choisissez une caractéristique à améliorer : ");

                int choix = sc.nextInt();

                switch(choix) {
                    case 1:
                        System.out.println("Combien de pojnts voulez-vous ajouter en force ?");
                        int pointsForce = sc.nextInt();
                        if (pointsForce > pointsRestant) {
                            throw new IllegalArgumentException("Vous n'avez pas assez de points à répartir");
                        }
                        int forceTemp = force + pointsForce;
                        if (forceTemp < 3 || forceTemp > 15) {
                            throw new IllegalArgumentException("La force doit être comprise entre 3 et 15");
                        }
                        force = forceTemp;
                        pointsRestant -= pointsForce;
                        break;

                    case 2:
                        System.out.println("Combien de pojnts voulez-vous ajouter en agilité ?");
                        int pointsAgilite = sc.nextInt();
                        if (pointsAgilite > pointsRestant) {
                            throw new IllegalArgumentException("Vous n'avez pas assez de points à répartir");
                        }
                        int agiliteTemp = agilite + pointsAgilite;
                        if (agiliteTemp < 3 || agiliteTemp > 15) {
                            throw new IllegalArgumentException("L'agilité doit être comprise entre 3 et 15");
                        }
                        agilite = agiliteTemp;
                        pointsRestant -= pointsAgilite;
                        break;

                    case 3:
                        System.out.println("Combien de pojnts voulez-vous ajouter en intelligence ?");
                        int pointsIntelligence = sc.nextInt();
                        if (pointsIntelligence > pointsRestant) {
                            throw new IllegalArgumentException("Vous n'avez pas assez de points à répartir");
                        }
                        int intelligenceTemp = intelligence + pointsIntelligence;
                        if (intelligenceTemp < 3 || intelligenceTemp > 15) {
                            throw new IllegalArgumentException("L'intelligence doit être comprise entre 3 et 15");
                        }
                        intelligence = intelligenceTemp;
                        pointsRestant -= pointsIntelligence;
                        break;
                }
            }
            catch (Exception e) {
                System.err.println("\033[0;31m" + e.getMessage()+ "\033[0m");
            }
        }

        sc.close();
    }

}
