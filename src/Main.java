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


    }
}
