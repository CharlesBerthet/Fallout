package tp.model.Characters;

import tp.model.Items.Armes.Machette;
import tp.model.Items.Armures.CombinaisonAbri;
import tp.model.Items.Item;
import tp.model.Items.Armes.Arme;
import tp.model.Items.Armures.Armure;
import tp.services.*;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;

public class Personnage {

    public int x;
    public int y;
    private String nom;
    private Map<Competence, Integer> competences;
    private int pointsRestants = 20;
    private Integer pointsDeVie;
    private Arme arme;
    private Armure armure;
    private Set<Item> inventaire = new HashSet<>();
    private Integer niveauExperience = 10;
    private Integer experience = 0;

    public Personnage(String nom) {
        this.nom = nom;
        this.competences = new EnumMap<>(Competence.class);
        initialiserCompetences();
        arme = new Machette();
        armure = new CombinaisonAbri();
        this.ajouterALInventaire(arme);
        this.ajouterALInventaire(armure);
    }
    private void initialiserCompetences() {
        for (Competence competence : Competence.values()) {
            competences.put(competence, 3);
        }
    }

    public void augmenterCompetence(Competence competence, int points) {
        if (competences.containsKey(competence) == false) {
            throw new IllegalArgumentException("La compétence " + competence + " n'existe pas.");
        }
        Integer niveauActuel = competences.get(competence);
        Integer nouveauNiveau = niveauActuel + points;
        if (nouveauNiveau < 3 || nouveauNiveau > 15) {
            throw new IllegalArgumentException("La valeur de la compétence doit être entre 3 et 15.");
        }

        pointsRestants -= points;
        competences.put(competence, nouveauNiveau);
    }

    public int getNiveauCompetence(Competence competence) {
        return competences.get(competence);
    }

    public void afficherCompetences() {
        System.out.println("Compétences de " + nom + " :");
        for (Competence competence : Competence.values()) {
            System.out.println(String.format("%-10s \t niveau : %d", competence, getNiveauCompetence(competence)));
        }
    }

    public int getPointsRestants() {
        return pointsRestants;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPointsDeVie() {
        return this.pointsDeVie + getNiveauCompetence(Competence.FORCE)*10;
    }

    public void setPointsDeVie(Integer pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    void attaqueSpeciale(){
        //TODO
    };


    public Arme getArme() {
        return arme;
    }

    public void equiperArme(Arme arme) {
        this.arme = arme;
    }

    public int getDegats() {
        if (this.arme == null) {
            return 10;
        }else{
            return 10 + this.arme.getDegats();
        }

    }

    public Armure getArmure() {
        return armure;
    }

    public void equiperArmure(Armure armure) {
        this.armure = armure;
    }

    public Set<Item> getInventaire() {
        return this.inventaire;
    }

    public void ajouterALInventaire(Item item) {
        this.inventaire.add(item);
    }

    public void augmenterExperience(int experience) {
        this.experience += experience;
        if (this.experience >= this.niveauExperience) {
            this.niveauExperience =  (int)(Math.pow(niveauExperience, 0.3));
            this.experience = 0;
        }
    }

}

