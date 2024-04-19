public class Personnage {

    private String nom;
    private int force;
    private int agilite;
    private int intelligence;

    public Personnage(String nom) {
        this.nom = nom;
        this.force = 0;
        this.agilite = 0;
        this.intelligence = 0;
    }


    public String getNom() {
        return nom;
    }

    public int getForce() {
        return force;
    }
    public void setForce(int forceAdd) {
        int forceTemp = force + forceAdd;
        if (forceTemp < 3 || forceTemp > 15) {
            throw new IllegalArgumentException("La force doit être comprise entre 3 et 15");
        }
        this.force = forceTemp;
    }

    public int getAgilite() {
        return agilite;
    }
    public void setAgilite(int agiliteAdd) {
        int agiliteTemp = agilite + agiliteAdd;
        if (agiliteTemp < 3 || agiliteTemp > 15) {
            throw new IllegalArgumentException("L'agilité doit être comprise entre 3 et 15");
        }
        this.agilite = agiliteTemp;
    }
    
    public int getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(int intelligenceAdd) {
        int intelligenceTemp = intelligence + intelligenceAdd;
        if (intelligenceTemp < 3 || intelligenceTemp > 15) {
            throw new IllegalArgumentException("L'intelligence doit être comprise entre 3 et 15");
        }
        this.intelligence = intelligenceTemp;
    }

}
