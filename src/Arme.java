abstract class Arme extends Item{

    private int degats;

    public Arme(String description, int poids, int prix, int degats) {
        super(description, poids, prix);
        this.degats = degats;
    }

    public int getDegats() {
        return degats;
    }
}
