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
                System.out.println("Points restants à répartir : " + pointsRestant);
                System.out.println("1. Augmenter la force");
                System.out.println("2. Augmenter l'agilité");
                System.out.println("3. Augmenter l'intelligence");
                System.out.print("Choisissez une caractéristique à améliorer (1-3) : ");

                int choix = sc.nextInt();

                switch (choix) {
                    case 1:
                        System.out.print("Combien de points voulez-vous ajouter à la force (entre 3 et 15) : ");
                        int PointForceAjouter = sc.nextInt();
                        personnage.setForce(PointForceAjouter);
                        pointsRestant -= PointForceAjouter;
                        break;

                    case 2:
                        System.out.print("Combien de points voulez-vous ajouter à l'agilité (entre 3 et 15) : ");
                        int PointAgiliteAjouter = sc.nextInt();
                        personnage.setAgilite(PointAgiliteAjouter);
                        pointsRestant -= PointAgiliteAjouter;
                        break;

                    case 3:
                        System.out.print("Combien de points voulez-vous ajouter à l'intelligence (entre 3 et 15) : ");
                        int PointIntelligenceAjouter = sc.nextInt();
                        personnage.setIntelligence(PointIntelligenceAjouter);
                        pointsRestant -= PointIntelligenceAjouter;
                        break;
                }
            } catch (Exception e) {
                System.err.println("\033[0;31m" +e.getMessage()+"\033[0m");
            }
        }

        System.out.println("Nom du personnage : " + personnage.getNom());
        System.out.println("Force : " + personnage.getForce());
        System.out.println("Agilité : " + personnage.getAgilite());
        System.out.println("Intelligence : " + personnage.getIntelligence());

        sc.close();
    }
}
