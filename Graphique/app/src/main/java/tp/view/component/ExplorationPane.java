package tp.view.component;

import tp.util.IGameMainMenu;
import tp.util.IOnShown;
import tp.view.component.map.PixelMap;

import javax.swing.*;
import java.awt.*;

@IGameMainMenu("Exploration")
public class ExplorationPane extends JPanel implements IOnShown {


    private final JPanel mainPane;
    private final PixelMap pixelMap;


    public ExplorationPane(PixelMap pixelMap, Events events) {
        this.pixelMap = pixelMap;

        this.setLayout(new BorderLayout());


        JLabel label = new JLabel("Exploration");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label, BorderLayout.NORTH);


        mainPane = new JPanel(new BorderLayout());
        mainPane.add(pixelMap, BorderLayout.WEST);
        mainPane.add(events, BorderLayout.EAST);

        this.add(mainPane);
        pixelMap.requestFocus();
    }


    @Override
    public void onShown() {
        this.pixelMap.requestFocus();
    }
}
