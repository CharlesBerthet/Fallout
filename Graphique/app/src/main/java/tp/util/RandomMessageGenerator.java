package tp.util;

import org.springframework.stereotype.Component;
import tp.util.MessageService;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class RandomMessageGenerator {
    private final MessageService messageService;
    private final Timer timer;

    public RandomMessageGenerator(MessageService messageService) {
        this.messageService = messageService;
        messageService.addMessage("Salut");

        this.timer = new Timer();
    }

    // Méthode pour démarrer le service
    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Ajout aléatoire de messages
                String[] messages = {
                        "You find a rusty pistol.",
                        "You discover a hidden stash of caps.",
                        "A radroach jumps out of nowhere!",
                        "You encounter a friendly wastelander.",
                        "You find an old Nuka-Cola bottle.",
                        "You are attacked by raiders!",
                        "You find a locked chest.",
                        "You find a pack of RadAway.",
                        "You stumble upon a ruined building.",
                        "You hear mysterious noises in the distance.",
                        "You find a note with coordinates.",
                        "You find an abandoned campsite.",
                        "You spot a mutant creature.",
                        "You encounter a friendly merchant.",
                        "You find a cache of ammunition.",
                        "You hear a distant explosion.",
                        "You find a hidden underground bunker.",
                        "You are hit by a radiation storm!",
                        "You find an old military outpost.",
                        "You discover a mutant settlement.",
                        "You find a rare pre-war artifact.",
                        "You encounter a group of feral ghouls.",
                        "You find a working terminal.",
                        "You are ambushed by a group of scavengers.",
                        "You discover an old radio transmitter.",
                        "You find a stash of pre-war money.",
                        "You are chased by a pack of wild dogs.",
                        "You encounter a friendly robot.",
                        "You find an old pre-war vehicle.",
                        "You are caught in a trap!",
                        "You find a hidden bunker entrance."
                };
                int randomIndex = (int) (Math.random() * messages.length);
                String randomMessage = messages[randomIndex];
                messageService.addMessage(randomMessage);
            }
        }, 0, 5000); // Ajoute un message toutes les 5 secondes (5000 millisecondes)
    }

    // Méthode pour arrêter le service
    public void stop() {
        timer.cancel();
    }
}
