package tp.view.component;

import tp.PersonnageSingleton;
import tp.util.IGameMainMenu;

import tp.model.Characters.Personnage;
import tp.model.Items.Item;

import javax.swing.*;
import java.awt.*;
import java.util.Set;


@IGameMainMenu("Inventaire")
public class InventoryPane extends JPanel {

    private final Personnage personnage;

    public InventoryPane(PersonnageSingleton personnageSingleton) {

        personnage = personnageSingleton.getInstance();
        this.setLayout(new GridLayout(0,3));

        Set<Item> inventaire = personnage.getInventaire();

        JList<Item> list = new JList<>();

        if (inventaire.isEmpty()) {
            JLabel label = new JLabel("Votre inventaire est vide");
            this.add(label, BorderLayout.CENTER);
        }else{
            for(Item objet : inventaire) {
                list.setListData(inventaire.toArray(new Item[0]));
                this.add(list);
            }
        }

    }
}
