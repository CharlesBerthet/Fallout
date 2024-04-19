public class Scientifique extends Personnage{

    public Scientifique(String nom) {
        super(nom);
        augmenterCompetence(Competence.INTELLIGENCE, 5);
    }

    @Override
    public void attaqueSpeciale() {
        System.out.println(getNom() + " utilise son attaque spéciale : Injection de sérum");
    }
}
