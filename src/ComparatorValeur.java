import java.util.Comparator;

public class ComparatorValeur implements Comparator<Item>{

        @Override
        public int compare(Item item1, Item item2) {
            if (item1 instanceof IValuable && item2 instanceof IValuable) {
                return (int) (((IValuable) item1).aPourValeur() - ((IValuable) item2).aPourValeur());
            } else {
                return 0;
            }
        }
}
