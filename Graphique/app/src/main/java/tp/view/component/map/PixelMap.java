package tp.view.component.map;

import org.springframework.stereotype.Component;
import tp.services.mapProvider.*;
import tp.services.mapProvider.WorldMapTile;
import tp.services.mapProvider.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Component
public class PixelMap extends JPanel implements KeyListener {
    private final MapController mapController;


    public PixelMap(MapController mapController) {
        this.mapController = mapController;
        addKeyListener(this);
        this.setSize(500, 500);
        this.setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        WorldMapTile[][] map = mapController.getMap();
        // Draw the map
        int cellSize = 10; // Adjust cell size as needed
        int x = 0;
        int y = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                g1.setColor(getColorIndex(map[i][j]));
                g1.fillRect(i * 10, j * 10, 10, 10);
            }
        }


        // Draw player character

        for (var poi : mapController.getPOI()) {
            if (poi.getClass().equals(POI.MonsterPOI.class)) {
                g1.setColor(Color.ORANGE);
            } else if (poi.getClass().equals(POI.TreasurePOI.class)) {
                g1.setColor(Color.GREEN);
            } else if (poi.getClass().equals(POI.DungeonPOI.class)) {
                g1.setColor(Color.MAGENTA);
            }
            g1.fillRect(poi.getPosition().x * cellSize, poi.getPosition().y * cellSize, cellSize, cellSize);
        }

        g1.setColor(Color.YELLOW); // Change color to represent player character
        CoordStruct personnagePosition = this.mapController.getPersonnagePosition();
        g1.fillRect(personnagePosition.x * cellSize, personnagePosition.y * cellSize, cellSize, cellSize);
    }

    private Color getColorIndex(WorldMapTile worldMapTile) {
        switch (worldMapTile) {
            case WATER: return new Color(60,150,170);
            case BEACH: return new Color(215,235,235);
            case LAND: return new Color(70,175,100);
            case DESERT: return new Color(225,215,145);
            case HILL: return new Color(125,105,90);
            case MOUNTAIN: return new Color(86,71,61);
            default: return Color.white;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Call PersonnageController to move the player character

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                mapController.move(Direction.NORD);
                break;
            case KeyEvent.VK_DOWN:
                mapController.move(Direction.SUD);
                break;
            case KeyEvent.VK_LEFT:
                mapController.move(Direction.OUEST);
                break;
            case KeyEvent.VK_RIGHT:
                mapController.move(Direction.EST);
                break;
        }
        repaint(); // Redraw the panel after the player's position changes
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}