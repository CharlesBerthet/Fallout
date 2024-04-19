import java.util.EnumMap;
import java.util.Map;

public class Personnage {

    private String nom;
    private Map<Competence, Integer> competences;

    public Personnage(String nom) {
        this.nom = nom;
        this.competences = new EnumMap<>(Competence.class);
        initialiserCompetences();
    }


    public String getNom() {
        return nom;
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

}
