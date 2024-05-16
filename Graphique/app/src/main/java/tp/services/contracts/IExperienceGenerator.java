package tp.services.contracts;

import tp.model.Characters.Personnage;

public interface IExperienceGenerator {
    int calculerExperience(Personnage personnage);
}
