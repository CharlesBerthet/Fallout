package tp.services;

import tp.model.Characters.Personnage;
import tp.model.Items.Item;
import tp.services.contracts.*;

import java.util.Random;
public class ExplorationGenerator {

    public void ExplorationGenerator(){

    }

    public void explorer(Personnage personnage) {
        Random rand = new Random();
        int random = rand.nextInt(3);
        if (random == 0) {
            System.out.println("Vous avez trouvé un coffre !");
            looterUnObjet(personnage);
        } else {
            System.out.println("Un combat commence !");
            combat(personnage);
        }
    }

    public void looterUnObjet(Personnage personnage) {
        ILootGenerator lootGenerator = new LootGenerator();
        Item item = lootGenerator.genererLoot();
        System.out.println("Vous avez trouvé un " + item.getDescription());
        personnage.ajouterALInventaire(item);

    }

    public void combat(Personnage personnage) {
        ICombatGenerator combatGenerator = new CombatGenerator();
        if(combatGenerator.combat(personnage)){
            System.out.println("Vous avez gagné le combat !");
            IExperienceGenerator experienceGenerator = new ExperienceGenerator();
            int experience = experienceGenerator.calculerExperience(personnage);
            personnage.augmenterExperience(experience);
            System.out.println("Vous avez gagné " + experience + " points d'expérience !");
        } else {
            System.out.println("Vous avez perdu le combat, vous êtes un looser...");

        }
    }

}