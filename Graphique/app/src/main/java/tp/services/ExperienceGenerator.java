package tp.services;

import tp.model.Characters.Personnage;
import tp.model.Characters.Competence;
import tp.services.contracts.IExperienceGenerator;

public class ExperienceGenerator implements IExperienceGenerator {
    @Override
    public int calculerExperience(Personnage personnage) {
        int intelligence = personnage.getNiveauCompetence(Competence.INTELLIGENCE);
        int chance = personnage.getNiveauCompetence(Competence.CHANCE);

        // Calcul de l'expérience en fonction de l'intelligence et de la chance du personnage
        return 1 +(intelligence * 10) + (chance * 5);
    }
}
