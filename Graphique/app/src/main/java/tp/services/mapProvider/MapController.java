package tp.services.mapProvider;

import java.util.Collection;
import tp.services.mapProvider.*;
import tp.PersonnageSingleton;

public class MapController {
    public WorldMapTile[][] getMap() {
        return mapProviderMap.getMapMatrix();
    }

    public Collection<POI> getPOI() {
        return mapPOIGenerator.getPoies();
    }
    public CoordStruct getPersonnagePosition() {
        return personnageSingleton.getInstance().getPosition();
    }
    public void move(Direction direction) {
        CoordStruct position = personnageSingleton.getInstance().getPosition();

        int x = position.x;
        int y = position.y;
        if (direction == Direction.NORD) {
            y--;
        } else if (direction == Direction.SUD) {
            y++;
        } else if (direction == Direction.EST) {
            x++;
        } else if (direction == Direction.OUEST) {
            x--;
        }

        if (mapProviderMap.getMapMatrix()[x][y] == WorldMapTile.WATER)
            messageService.addMessage("Votre personnage ne peut pas se déplacer sur l'eau.");
        else {
            if (direction == Direction.NORD) {
                personnageSingleton.getInstance().move(0, -1);
            } else if (direction == Direction.SUD) {
                personnageSingleton.getInstance().move(0, 1);
            } else if (direction == Direction.EST) {
                personnageSingleton.getInstance().move(1, 0);
            } else if (direction == Direction.OUEST) {
                personnageSingleton.getInstance().move(-1, 0);
            }

            messageService.addMessage("");
            messageService.addMessage("Le personnage se déplace vers le " + direction);
            explorationGenerator.explorer(personnageSingleton.getInstance());
        }
    }
}
