import java.util.Random;

public class LootGenerator implements ILootGenerator {
    private Random random;

    public LootGenerator() {
        this.random = new Random();
    }

    @Override
    public Item genererLoot() {
        int choix = random.nextInt(3);
        switch (choix) {
            case 0:
                return new CombinaisonAbri();
            case 1:
                return new FatMan();
            case 2:
                return new Machette();
            default:
                return null;
        }
    }
}