package tp.services;

import tp.model.Items.*;

import java.util.Comparator;

public class ComparatorPoids implements Comparator<Item> {

    @Override
    public int compare(Item item1, Item item2) {
        return (int) (item1.getPoids() - item2.getPoids());
    }
}