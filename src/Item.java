public abstract class Item {
    private String description;
    private int poids;
    private int prix;

    public Item(String description, int poids, int prix) {
        this.description = description;
        this.poids = poids;
        this.prix = prix;
    }
}
