package ExercicesTP.IHM;
import ExercicesTP.CRUD.EtudiantDAO;
import ExercicesTP.Etudiant;

import javax.swing.*;
import java.awt.*;

public class IHMAjoutEtudiant extends JInternalFrame{
    public JLabel nomLabel, prenomLabel, numEtdLabel, filiereLabel, niveauLabel, groupeLabel;
    public JTextField nomField, prenomField, numEtdField;
    public JComboBox<String> filiereComboBox;
    public JComboBox<Integer> niveauComboBox, groupeComboBox;
    public EtudiantDAO dao;
    public JButton addButton, cancelButton;
    public IHMAjoutEtudiant(EtudiantDAO dao){
        setTitle("Ajout d'un étudiant");
        setSize(400, 400);
        initializeComponents();
        createLayout();
        addEventListeners();
        setVisible(true);
        this.dao = dao;
    }
    private void addComponent(JPanel panel, JComponent component, GridBagConstraints c, int x, int y, int width, int height) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        panel.add(component, c);
    }
    public void initializeComponents() {
        nomLabel = new JLabel("Nom :");
        prenomLabel = new JLabel("Prénom :");
        numEtdLabel = new JLabel("Numéro étudiant :");
        filiereLabel = new JLabel("Filière :");
        niveauLabel = new JLabel("Niveau :");
        groupeLabel = new JLabel("Groupe :");

        nomField = new JTextField(15);
        prenomField = new JTextField(20);
        numEtdField = new JTextField(15);

        // Sample data for comboboxes (replace with actual data source)
        filiereComboBox = new JComboBox<>(new String[]{"FIA", "Licence Sc Info", "Licence Info de Gest", "Maths Info", "Physique Info", "Chimie Info", "Bio Info", "Eco Info", "Génie Info", "Info de Gestion", "Info de Comm"});
        niveauComboBox = new JComboBox<>(new Integer[]{1, 2, 3});
        groupeComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        addButton = new JButton("Ajouter");
        cancelButton = new JButton("Annuler");

    }

    public void createLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Add labels and corresponding components
        c.fill = GridBagConstraints.HORIZONTAL;
        addComponent(panel, numEtdLabel, c, 0, 2, 1, 1);
        addComponent(panel, numEtdField, c, 1, 2, 2, 1);
        addComponent(panel, nomLabel, c, 0, 0, 1, 1);
        addComponent(panel, nomField, c, 1, 0, 2, 1);
        addComponent(panel, prenomLabel, c, 0, 1, 1, 1);
        addComponent(panel, prenomField, c, 1, 1, 2, 1);
        addComponent(panel, filiereLabel, c, 0, 3, 1, 1);
        addComponent(panel, filiereComboBox, c, 1, 3, 2, 1);
        addComponent(panel, niveauLabel, c, 0, 4, 1, 1);
        addComponent(panel, niveauComboBox, c, 1, 4, 2, 1);
        addComponent(panel, groupeLabel, c, 0, 5, 1, 1);
        addComponent(panel, groupeComboBox, c, 1, 5, 2, 1);
        addComponent(panel, addButton, c, 0, 6, 1, 1);
        addComponent(panel, cancelButton, c, 1, 6, 1, 1);
        add(panel);
    }
    public void emptyForm(){
        nomField.setText("");
        prenomField.setText("");
        numEtdField.setText("");
        filiereComboBox.setSelectedIndex(0);
        niveauComboBox.setSelectedIndex(0);
        groupeComboBox.setSelectedIndex(0);
    }
    public void addEventListeners(){
        addButton.addActionListener((e)->{
            // Get the values from the form
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int numEtd = Integer.parseInt(numEtdField.getText());
            String filiere = (String) filiereComboBox.getSelectedItem();
            int niveau = (int) niveauComboBox.getSelectedItem();
            int groupe = (int) groupeComboBox.getSelectedItem();
            Etudiant etudiant = new Etudiant(numEtd, nom, prenom, filiere, niveau, groupe);
            dao.addEtudiant(etudiant);
            JOptionPane.showMessageDialog(this, "Etudiant ajouté avec succès");
            emptyForm();
        });

        cancelButton.addActionListener((e)->{
            emptyForm();
            dispose();
        });
    }

}