public class Armure extends Item{

    private int protection;

    public Armure(String description, int poids, int prix, int protection) {
        super(description, poids, prix);
        this.protection = protection;
    }

    public int getProtection() {
        return protection;
    }

}
