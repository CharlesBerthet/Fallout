package tp.services.mapProvider;

import com.google.common.base.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tp.services.mapProvider.*;
import tp.PersonnageSingleton;
import tp.model.Items.Item;
import tp.services.CombatGenerator;
import tp.services.ExperienceGenerator;
import tp.services.LootGenerator;
import tp.services.mapProvider.IWorldMapProvider;
import tp.services.mapProvider.WorldMapTile;
import tp.util.MessageService;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


public abstract class POI {
    private final CoordStruct position;

    public POI(CoordStruct position) {
        this.position = position;
    }

    public CoordStruct getPosition() {
        return position;
    }
    @Autowired
    protected LootGenerator lootGenerator;
    @Autowired
    protected PersonnageSingleton personnageSingleton;
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected CombatGenerator combatGenerator;
    @Autowired
    protected ExperienceGenerator experienceGenerator;
    public void interact() {

    }

    @Component
    @Scope("prototype")
    public static class MonsterPOI extends POI {
        @Override
        public void interact() {
            super.interact();
            messageService.addMessage("Vous avez rencontré un monstre", Color.red);
            combatGenerator.combat(personnageSingleton.getInstance());
        }


        public MonsterPOI(CoordStruct position) {
            super(position);
        }

    }

    @Component

    @Scope("prototype")
    public static class DungeonPOI extends POI {
        public DungeonPOI(CoordStruct coordStruct) {
            super(coordStruct);
        }

        @Override
        public void interact() {
            super.interact();
            messageService.addMessage("Vous avez rencontré un donjon", Color.red);

            for (var i : new Random().ints(10).boxed().collect(Collectors.toList())) {
                if(i%2==0){
                    Item newItem = lootGenerator.genererLoot();
                    personnageSingleton.getInstance().ajouterALInventaire(newItem);
                    messageService.addMessage("Nouveau loot -> " + newItem.getDescription(), Color.blue);
                }else {
                    messageService.addMessage("Vous avez rencontré un monstre", Color.red);
                    if (!combatGenerator.combat(personnageSingleton.getInstance())) {
                        break;
                    }
                }
            }
        }
    }



    @Component

    @Scope("prototype")
    public static class TreasurePOI extends POI {
        @Override
        public void interact() {
            super.interact();
            messageService.addMessage("Vous avez trouvé un trésor", Color.green);
            Item newItem = lootGenerator.genererLoot();
            personnageSingleton.getInstance().ajouterALInventaire(newItem);
            messageService.addMessage("Nouveau loot -> " + newItem.getDescription(), Color.blue);

        }

        public TreasurePOI(CoordStruct coordStruct) {
            super(coordStruct);
        }
    }

    public enum POITypes {

        Monster(0, MonsterPOI.class),
        Treasure(1, TreasurePOI.class),
        Dungeon(2, DungeonPOI.class),
        ;

        public final Class<? extends POI> poiType;

        POITypes(int i, Class<? extends POI> poiType) {
            this.poiType = poiType;
        }
    }

    @Component
    public static class PoiSupplier extends RandomEnumGenerator<POITypes> implements Supplier<POITypes> {

        public PoiSupplier() {
            super(POITypes.class);
        }

        @Override
        public POITypes get() {
            return this.nextEnum();
        }
    }

    public static class PseudoNormalNumberGenerator {
        private final Random random;
        private final double maxDeviation;

        public PseudoNormalNumberGenerator(int maxDeviation) {
            this.random = new Random();
            this.maxDeviation = maxDeviation;
        }

        protected int nextGaussianInRange() {
            double u1 = 1.0 - random.nextDouble();
            double u2 = 1.0 - random.nextDouble();
            double randStdNormal = Math.sqrt(-2.0 * Math.log(u1)) * Math.sin(2.0 * Math.PI * u2);
            return (int) ((Math.abs(randStdNormal)*maxDeviation)%maxDeviation);
        }

        public static void main(String[] args) {
            PseudoNormalNumberGenerator generator = new PseudoNormalNumberGenerator(5); // Example with maximum deviation of 10
            for (int i = 0; i < 15; i++) {
                System.out.println(generator.nextGaussianInRange());
            }
        }
    }

    public abstract static class RandomEnumGenerator<T extends Enum<T>>  extends PseudoNormalNumberGenerator {
        private final Class<T> enumClass;

        public T nextEnum() {
            return enumClass.getEnumConstants()[this.nextGaussianInRange()];
        }
        public RandomEnumGenerator(Class<T> enumClass) {
            super(enumClass.getEnumConstants().length);
            this.enumClass = enumClass;
        }
    }

    @Component
    public static class MapPOIGenerator {

        private final ArrayList<POI> poies;

        public ArrayList<POI> getPoies() {
            return poies;
        }

        public MapPOIGenerator(IWorldMapProvider mapProvider, Supplier<POITypes> randomPoiGenerator, PoiFactory poiFactroy){

            poies = new ArrayList<>();
            for (int i = mapProvider.getMapMatrix().length - 1; i >= 1; i--) {
                for (int j = 1; j < mapProvider.getMapMatrix()[i].length; j++) {
                    if (mapProvider.getMapMatrix()[i][j] == WorldMapTile.WATER) {
                        continue;
                    }
                    if (new Random().nextDouble()>0.2f) {
                        continue;
                    }

                    poies.add(poiFactroy.createPoi(randomPoiGenerator.get().poiType, new CoordStruct(i, j)));
                }
            }

        }

        public Optional<POI> popPoi(CoordStruct position) {
            Optional<POI> optionalPOI = getPoies().stream().filter(e -> e.getPosition().equals(position)).findAny();
            optionalPOI.ifPresent(poi -> getPoies().remove(poi));
            return optionalPOI;
        }
    }

    @Component
    public static class PoiFactory{
        private final ApplicationContext context;
        public <T extends POI> T createPoi(Class<T> poiType, CoordStruct position) {

            return context.getBean( poiType, position);

        }
        public PoiFactory(ApplicationContext context) {

            this.context = context;
        }

    }
}
