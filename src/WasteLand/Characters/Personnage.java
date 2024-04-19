package WasteLand.Characters;

import WasteLand.Comparators.ComparatorPoids;
import WasteLand.Comparators.ComparatorPrix;
import WasteLand.Comparators.ComparatorValeur;
import WasteLand.Items.Armes.Arme;
import WasteLand.Items.Armures.Armure;
import WasteLand.Items.Item;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;

public abstract class Personnage {

    private String nom;
    private Map<Competence, Integer> competences;
    private Arme arme;
    private Armure armure;
    private Set<Item> inventaire = new HashSet<>();
    private int pointsDeVie;
    private int niveauExperience = 10;
    private int experience = 0;

    public Personnage(String nom) {
        this.nom = nom;
        this.competences = new EnumMap<>(Competence.class);
        initialiserCompetences();
        this.pointsDeVie = 100;
    }


    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    private void initialiserCompetences() {
        for (Competence competence : Competence.values()) {
            competences.put(competence, 0);
        }
    }

    public void augmenterCompetence(Competence competence, int points) {
        /// Effectuez les vérifications nécessaires
        if (points < 0 || points > 15) {
            throw new IllegalArgumentException("Le nombre de points doit être compris entre 0 et 15");
        }
        int nouveauNiveau = competences.get(competence) + points;

            competences.put(competence, nouveauNiveau);
        }

    public int getNiveauCompetence(Competence competence) {
        return competences.get(competence);
    }

    public void afficherCompetences() {
        System.out.println("Compétences de " + nom + " :");
        for (Competence competence : Competence.values()) {
            System.out.println(String.format("%-10s \t niveau : %d", competence, competences.get(competence)));
        }
    }

    public abstract void attaqueSpeciale();

    public Arme getArme() {
        return arme;
    }
    public void equiperArme(Arme arme) {
        this.arme = arme;
    }

    public Armure getArmure() {
        return armure;
    }
    public void equiperArmure(Armure armure) {
        this.armure = armure;
    }

    public int getDegats() {
        if (this.arme == null) {
            return 10;
        }else{
            return 10 + this.arme.getDegats();
        }
    }


    public void ajouterALInventaire(Item item) {
        this.inventaire.add(item);
    }

    public void afficherInventaire() {
        System.out.println("Inventaire de " + this.getNom());
        System.out.println("______________________________");
        Scanner sc = new Scanner(System.in);
        System.out.println("Trier l'inventaire par :"); 
        System.out.println("Arme / Armure / Autres (1/2/3)");
        System.out.println("Poids / Prix / Valeur (W/P/V)");
        String reponse = sc.nextLine();

        Comparator comparator = null;
        switch (reponse.charAt(1)) {
            case 'W':
                comparator = new ComparatorPoids();
                break;
            case 'P':
                comparator = new ComparatorPrix();
                break;
            case 'V':
                comparator = new ComparatorValeur();
                break;
        }

        this.inventaire.stream().filter(e->{
            switch (reponse.charAt(0)) {
                case '1':
                    return e instanceof Arme;
                case '2':
                    return e instanceof Armure;
                case '3':
                    return !(e instanceof Arme) && !(e instanceof Armure);
                default:
                    return true;
            }
        }).sorted(comparator).forEach(System.out::println);
    }

    public int getPointsDeVie() {
        return this.pointsDeVie + getNiveauCompetence(Competence.FORCE)*10;
    }
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public void augmenterExperience(int experience) {
        this.experience += experience;
        if (this.experience >= this.niveauExperience) {
            this.niveauExperience = (int)(Math.pow(niveauExperience, 0.3));
            this.experience = 0;
        }
    }

}
