package tp.model.Items.Armes;

import tp.services.contracts.IValuable;
import tp.model.Items.Item;

public abstract class Arme extends Item implements IValuable {

    private int degats;

    public Arme(String description, int poids, int prix, int degats) {
        super(description, poids, prix);
        this.degats = degats;
    }

    public int getDegats() {
        return degats;
    }

    @Override
    public int aPourValeur() {
        return (int) (this.getDegats());
    }
}
