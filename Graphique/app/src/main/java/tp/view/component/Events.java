package tp.view.component;

import org.springframework.stereotype.Component;
import tp.util.MessageService;

import javax.swing.*;
import java.awt.*;

@Component
public class Events extends JTextPane {
    private final MessageService messageService;

    public Events(MessageService messageService) {
        this.messageService = messageService;
        setEditable(false); // Empêcher l'édition du texte
        setPreferredSize(new Dimension(300, 0)); // Taille par défaut
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Bordure

        messageService.addObserver((o, arg) -> {
            if (arg instanceof String) {
                String message = (String) arg;
                this.setText(this.getText() + message + "\n");
            }
        });
    }
}
