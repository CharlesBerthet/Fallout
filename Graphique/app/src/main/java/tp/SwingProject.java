package tp;

import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;

@ComponentScan("tp")
public class SwingProject extends JFrame {


    public static void main(String[] args) {
        FlatLightLaf.setup();
        var context = new AnnotationConfigApplicationContext(SwingProject.class);
        SwingUtilities.invokeLater(() -> {

            var swingProject = context.getBean(App.class);

            swingProject.setVisible(true);
        });
    }


}
