package WasteLand.Items.Armures;

import WasteLand.Items.IValuable;
import WasteLand.Items.Item;

public abstract class Armure extends Item implements IValuable {

    private int protection;

    public Armure(String description, int poids, int prix, int protection) {
        super(description, poids, prix);
        this.protection = protection;
    }

    public int getProtection() {
        return protection;
    }

    @Override
    public int aPourValeur() {
        return (int) (this.getProtection());
    }

}
