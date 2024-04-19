import java.util.EnumMap;
import java.util.Map;

public abstract class Personnage {

    private String nom;
    private Map<Competence, Integer> competences;
    private Arme arme;
    private Armure armure;

    public Personnage(String nom) {
        this.nom = nom;
        this.competences = new EnumMap<>(Competence.class);
        initialiserCompetences();
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

}
