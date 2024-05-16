package tp.view.component;

import tp.PersonnageSingleton;
import tp.model.Characters.Competence;
import tp.model.Characters.Personnage;
import tp.util.IGameMainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.Map;


@IGameMainMenu("Compétences")
public class CompetencePanel extends JPanel {
    private final Personnage personnage;
    private JLabel pointsRestantsLabel;
    private Map<Competence, JSpinner> competenceSpinners;

    private JLabel remainingPointsLabel;

    public CompetencePanel(PersonnageSingleton personnageSingleton) {
        personnage = personnageSingleton.getInstance();
        this.setLayout(new GridLayout(0, 2));
        competenceSpinners = new EnumMap<>(Competence.class);

        // Création des spinners pour chaque compétence
        for (Competence competence : Competence.values()) {
            JLabel label = new JLabel(competence.toString());
            JSpinner spinner = new JSpinner();

            competenceSpinners.put(competence, spinner);
            updateSpinner(competence);
            this.add(label);
            this.add(spinner);
        }
        // Affichage du nombre de points restants
        remainingPointsLabel = new JLabel("Points restants : " + personnage.getPointsRestants());
        this.add(remainingPointsLabel);

        // Bouton pour sauvegarder les compétences
        JButton saveButton = new JButton("Sauvegarder");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int remainingPoints = personnage.getPointsRestants();
                for (Competence competence : Competence.values()) {
                    int newValue = (int) competenceSpinners.get(competence).getValue();
                    int oldValue = personnage.getNiveauCompetence(competence);
                    int difference = newValue - oldValue;
                    if (difference > 0 && remainingPoints - difference < 0) {
                        // Afficher une boîte de dialogue avec une erreur si le nombre de points restants est insuffisant
                        JOptionPane.showMessageDialog(null, "Vous n'avez plus de points disponibles !");
                        return;
                    }
                }
                for (Competence competence : Competence.values()) {
                    int newValue = (int) competenceSpinners.get(competence).getValue();
                    int oldValue = personnage.getNiveauCompetence(competence);

                    personnage.augmenterCompetence(competence, newValue - oldValue);

                    updateSpinner(competence);
                }
                JOptionPane.showMessageDialog(null, "Compétences sauvegardées avec succès !");
                remainingPointsLabel.setText("Points restants : " + personnage.getPointsRestants());
            }
        });
        this.add(saveButton);
    }

    private void updateSpinner(Competence competence) {
        JSpinner jSpinner = competenceSpinners.get(competence);
        jSpinner.setModel(new SpinnerNumberModel(personnage.getNiveauCompetence(competence), personnage.getNiveauCompetence(competence), 15, 1));

    }
}