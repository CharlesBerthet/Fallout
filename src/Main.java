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
        personnages.get(0).equiperArmure(combinaisonAbri);

        FatMan fatMan = new FatMan();
        personnages.get(0).equiperArme(fatMan);

    }
}
