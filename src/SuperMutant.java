public class SuperMutant extends Personnage{

    public SuperMutant(String nom) {
        super(nom);
        augmenterCompetence(Competence.FORCE, 5);
    }

    @Override
    public void attaqueSpeciale() {
        System.out.println(getNom() + " utilise son attaque spéciale : Lancer de cailloux");
    }
}
