package ExercicesTP.IHM;

import ExercicesTP.CRUD.FormationDAO;
import TP_Base.Config;
import TP_Base.MyConnexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class Principale extends JFrame {
    JMenuBar menuBar;
    JMenu menuFormation;
    JMenu menuEtudiant;
    JMenu menuEnseignant;

    JMenuItem menuItemAjouteurEtudiant;
    JMenuItem menuItemRechercherEtudiant;
    JMenuItem menuItemAfficherEtudiant;

    JMenuItem menuItemAjouteurEnseignant;
    JMenuItem menuItemRechercherEnseignant;
    JMenuItem menuItemAfficherEnseignant;

    JMenuItem menuItemAjouteurFormation;
    JMenuItem menuItemRechercherFormation;
    JMenuItem menuItemAfficherFormation;
    JDesktopPane desktop;
    FormationDAO formationDao;
    Principale() {
        menuBar = new JMenuBar();
        this.setTitle("Gestion des formation");
        this.setSize(1000, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        desktop = new JDesktopPane();
        this.add(desktop);

        menuFormation = new JMenu("Formation");
        menuEtudiant = new JMenu("Etudiant");
        menuEnseignant = new JMenu("Enseignant");

        menuItemAjouteurFormation = new JMenuItem("Ajouteur");
        menuItemRechercherFormation = new JMenuItem("Rechercher");
        menuItemAfficherFormation = new JMenuItem("Afficher");

        menuFormation.add(menuItemAjouteurFormation);
        menuFormation.add(menuItemRechercherFormation);
        menuFormation.add(menuItemAfficherFormation);

        menuItemAjouteurEtudiant = new JMenuItem("Ajouteur");
        menuItemRechercherEtudiant = new JMenuItem("Rechercher");
        menuItemAfficherEtudiant = new JMenuItem("Afficher");
        menuEtudiant.add(menuItemAjouteurEtudiant);
        menuEtudiant.add(menuItemRechercherEtudiant);
        menuEtudiant.add(menuItemAfficherEtudiant);

        menuItemAjouteurEnseignant = new JMenuItem("Ajouteur");
        menuItemRechercherEnseignant = new JMenuItem("Rechercher");
        menuItemAfficherEnseignant = new JMenuItem("Afficher");

        menuEnseignant.add(menuItemAjouteurEnseignant);
        menuEnseignant.add(menuItemRechercherEnseignant);
        menuEnseignant.add(menuItemAfficherEnseignant);


        menuBar.add(menuFormation);
        menuBar.add(menuEtudiant);
        menuBar.add(menuEnseignant);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
        formationDao = new FormationDAO();
        // listenners
        menuItemAjouteurFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IHMAjoutFormation ihmAjout = new IHMAjoutFormation(formationDao);
                desktop.add(ihmAjout);
            }
        });
        menuItemRechercherFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IHMRechercheFormation ihmRecherche = new IHMRechercheFormation(formationDao);
                desktop.add(ihmRecherche);
            }
        });

        menuItemAfficherFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IHMAffichageFormation ihmAffichage = new IHMAffichageFormation(formationDao);
                desktop.add(ihmAffichage);
            }
        });






    }


}