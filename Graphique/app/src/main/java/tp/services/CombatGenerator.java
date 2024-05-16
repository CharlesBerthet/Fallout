package tp.services;

import tp.model.Characters.Personnage;
import tp.services.contracts.*;

import java.util.Random;

public class CombatGenerator implements ICombatGenerator {
    private Random random;

    public CombatGenerator() {
        this.random = new Random();
    }

    @Override
    public boolean combat(Personnage personnage) {
        //Points de vie du monstre entre 50 et 100
        int pointsDeViePersonnage = personnage.getPointsDeVie();
        double randomValue = 0.9 + (1.2 - 0.9) * random.nextDouble();

        int pointsDeVieMonstre = (int)(pointsDeViePersonnage * randomValue); // Points de vie du monstre entre 90 et 120% de ceux du personnage

        do {
            // Calcul des dégâts infligés par le personnage et le monstre
            int degatsPersonnage = personnage.getDegats() + random.nextInt(11); // Dégâts entre 0 et 10 supplémentaires
            randomValue = 0.9 + (1.2 - 0.9) * random.nextDouble();
            int degatsMonstre = (int)(degatsPersonnage * randomValue); // Dégâts du monstre entre 90 et 120% de ceux du personnage

            // Appliquer les dégâts
            System.out.println("Le personnage inflige " + degatsPersonnage + " dégâts au monstre.");
            System.out.println("Le monstre inflige " + degatsMonstre + " dégâts au personnage.");
            pointsDeVieMonstre -= degatsPersonnage;
            pointsDeViePersonnage -= degatsMonstre;
            System.out.println("Points de vie du monstre : " + pointsDeVieMonstre);
            System.out.println("Points de vie du personnage : " + pointsDeViePersonnage);
            System.out.println("______________________________");

        } while (pointsDeVieMonstre > 0 && pointsDeViePersonnage > 0);
        return pointsDeVieMonstre <= 0;
    }
}
