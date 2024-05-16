package tp.util;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Observable;

@Component
public class MessageService extends Observable {
    private ArrayList<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
        setChanged();
        notifyObservers(message);
    }

    // Obtenir la liste des messages
    public ArrayList<String> getMessages() {
        return messages;
    }

    public void addMessage(String s, Color color) {
        //Color to HEX String
        var hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        String formatted = MessageFormat.format("<span style=\"color: {0}\">{1}</span>", hex, s);
        messages.add(formatted);
        setChanged();
        notifyObservers(formatted);
    }
}
