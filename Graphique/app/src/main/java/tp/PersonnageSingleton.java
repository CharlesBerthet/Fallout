package tp;

import org.springframework.stereotype.Component;

import tp.model.Characters.Personnage;

@Component
public class PersonnageSingleton {
    private Personnage personnage;

    public Personnage getInstance() {
        if (personnage == null) {
            personnage = new Personnage("Charles");
        }
        return personnage;
    }
}
