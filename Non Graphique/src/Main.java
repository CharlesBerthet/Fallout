import WasteLand.Characters.Mercenaire;
import WasteLand.Characters.Personnage;
import WasteLand.Characters.Scientifique;
import WasteLand.Characters.SuperMutant;
import WasteLand.Generators.ExplorationGenerator;
import WasteLand.Items.Armes.FatMan;
import WasteLand.Items.Armures.CombinaisonAbri;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Personnage> personnages = new ArrayList<>();

        personnages.add(new Mercenaire("John"));
        personnages.add(new SuperMutant("Gronk"));
        personnages.add(new Scientifique("Dr. Smith"));

        for (Personnage personnage : personnages) {
            System.out.println("Nom du personnage : " + personnage.getNom());
            personnage.afficherCompetences();
            personnage.attaqueSpeciale();
            System.out.println();
        }

        CombinaisonAbri combinaisonAbri = new CombinaisonAbri();
        FatMan fatMan = new FatMan();
        System.out.println(combinaisonAbri);
        System.out.println(fatMan);

        personnages.get(0).ajouterALInventaire(combinaisonAbri);
        personnages.get(0).ajouterALInventaire(fatMan);

        personnages.get(0).afficherInventaire();

        System.out.println("Points de vie du personnage : " + personnages.get(0).getPointsDeVie());
        System.out.println("Points de vie du personnage : " + personnages.get(1).getPointsDeVie());

        ExplorationGenerator exploration = new ExplorationGenerator();
        exploration.explorer(personnages.get(0));
    }
}
