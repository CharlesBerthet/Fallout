package WasteLand.Comparators;

import WasteLand.Items.Item;

import java.util.Comparator;

public class ComparatorPrix implements Comparator<Item>{

    @Override
    public int compare(Item item1, Item item2) {
        return (int) (item1.getPrix() - item2.getPrix());
    }
}
