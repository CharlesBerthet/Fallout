package tp;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import tp.util.IGameMainMenu;
import tp.util.IOnShown;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Map;

@Component
public class App extends JFrame {


    public App(ApplicationContext context) {


        setTitle("Java Swing Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane jTabbedPane = new JTabbedPane();
        this.add(jTabbedPane, BorderLayout.CENTER);


        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(IGameMainMenu.class);
        beansWithAnnotation.forEach((s, o) -> {

            jTabbedPane.add(s, (java.awt.Component) o);

        });
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (jTabbedPane.getSelectedComponent() instanceof IOnShown) {
                    ((IOnShown) jTabbedPane.getSelectedComponent()).onShown();
                }
            }
        });
        setLocationRelativeTo(null); // Center the frame

        pack();
    }
}
