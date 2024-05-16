package tp.model.Items;

import tp.services.contracts.IValuable;

public abstract class Item {
    private String description;
    private int poids;
    private int prix;

    public Item(String description, int poids, int prix) {
        this.description = description;
        this.poids = poids;
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public int getPoids() {
        return poids;
    }

    public int getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        if (this instanceof IValuable) {
            return this.description + " - Valeur : " + ((IValuable) this).aPourValeur();
        } else {
            return this.description;
        }
    }
}
