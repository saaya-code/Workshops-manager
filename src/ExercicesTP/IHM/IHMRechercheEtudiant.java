package ExercicesTP.IHM;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ExercicesTP.CRUD.EtudiantDAO;
import ExercicesTP.Etudiant;
import ExercicesTP.Helpers.TableModelEtudiant;
import ExercicesTP.Helpers.TableModelFormation;

public class IHMRechercheEtudiant extends JInternalFrame {

    private JLabel numEtdLabel, nomLabel, prenomLabel, filiereLabel, niveauLabel, groupeLabel, listeDemandesLabel;
    private JTextField numEtdField, nomField, prenomField ;
    private  JComboBox<String> filiereComboBox,demandesBox;
    private JComboBox<Integer> niveauComboBox, groupeComboBox;
    private JButton ajouterDemandeButton, supprimerDemandeButton, rechercheButton, cancelButton, modifierButton, supprimerButton;
    public EtudiantDAO dao;
    public TableModelEtudiant model;
    public JTable jt_Etudiant;
    public JScrollPane sp;
    public IHMRechercheEtudiant(EtudiantDAO dao) {

        super("Recherche d'un etudiant");
        this.dao = dao;
        initializeComponents();
        createLayout();
        addEventListeners();
        this.setVisible(true);
    }

    private void initializeComponents() {
        numEtdLabel = new JLabel("Numéro étudiant :");
        nomLabel = new JLabel("Nom :");
        prenomLabel = new JLabel("Prénom :");
        filiereLabel = new JLabel("Filière :");
        niveauLabel = new JLabel("Niveau :");
        groupeLabel = new JLabel("Groupe :");
        listeDemandesLabel = new JLabel("Liste des demandes :");

        numEtdField = new JTextField(20);
        nomField = new JTextField(20);
        prenomField = new JTextField(20);

        // Sample data for comboboxes (replace with actual data source)
        filiereComboBox = new JComboBox<>(new String[]{"FIA", "Licence Sc Info", "Licence Info de Gest", "Maths Info", "Physique Info", "Chimie Info", "Bio Info", "Eco Info", "Génie Info", "Info de Gestion", "Info de Comm"});
        niveauComboBox = new JComboBox<>(new Integer[]{1, 2, 3});
        groupeComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        ajouterDemandeButton = new JButton("Ajouter demande");
        supprimerDemandeButton = new JButton("Supprimer demande");
        rechercheButton = new JButton("Rechercher");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        cancelButton = new JButton("Annuler");

        demandesBox = new JComboBox<String>();
        listeDemandesLabel = new JLabel("Liste des demandes :");
        jt_Etudiant = new JTable();
        String rq = "SELECT titre,lieu,datef FROM FORMATION f,demandeetd d,ETUDIANT e WHERE (e.id=d.idEtudiant) and (f.idF = d.idFormation) and 5=7;";
        model = new TableModelEtudiant(dao.selection(rq), dao);
        jt_Etudiant.setModel(model);
        sp = new JScrollPane(jt_Etudiant);

    }

    private void createLayout() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        JPanel p4 = new JPanel(new FlowLayout());
        JPanel p5 = new JPanel(new FlowLayout());
        JPanel p6 = new JPanel(new FlowLayout());
        JPanel p7 = new JPanel(new FlowLayout());
        JPanel p8 = new JPanel(new FlowLayout());
        p1.add(numEtdLabel);
        p1.add(numEtdField);
        p1.add(rechercheButton);

        p2.add(nomLabel);
        p2.add(nomField);
        p2.add(modifierButton);

        p3.add(prenomLabel);
        p3.add(prenomField);
        p3.add(supprimerButton);

        p4.add(filiereLabel);
        p4.add(filiereComboBox);
        p4.add(cancelButton);

        p5.add(niveauLabel);
        p5.add(niveauComboBox);

        p6.add(groupeLabel);
        p6.add(groupeComboBox);

        p7.add(ajouterDemandeButton);
        p7.add(supprimerDemandeButton);
        p7.add(demandesBox);

        p8.add(listeDemandesLabel);
        p8.add(sp);

        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(p7);
        this.add(p8);
        ResultSet rs = dao.selection("SELECT titre FROM FORMATION;");
        try {
            while(rs.next()){
                String titre = rs.getString(1);
                demandesBox.addItem(titre);
            }
        }catch (SQLException e){
            e.printStackTrace();
            }






        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addComponent(JPanel panel, JComponent component, GridBagConstraints c, int x, int y, int width, int height) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        panel.add(component, c);
    }

    private void clearInputs() {
          numEtdField.setText("");
            nomField.setText("");
            prenomField.setText("");
            filiereComboBox.setSelectedIndex(0);
            niveauComboBox.setSelectedIndex(0);
            groupeComboBox.setSelectedIndex(0);

    }

    private void addEventListeners() {
       //TODO implement
        rechercheButton.addActionListener(e -> {
            String rq = "SELECT titre,lieu,datef FROM FORMATION f,demandeetd d,ETUDIANT e WHERE (e.id=d.idEtudiant) and (f.idF = d.idFormation) and e.id = "+numEtdField.getText()+";";
            model.updateTableWithNewResultSet(dao.selection(rq));
            String rq2 = "SELECT nom,prenom,filiere,niveau,groupe FROM ETUDIANT WHERE id = "+numEtdField.getText()+";";
            ResultSet rs = dao.selection(rq2);
            try {
                if (rs.next()) {
                    nomField.setText(rs.getString(1));
                    prenomField.setText(rs.getString(2));
                    filiereComboBox.setSelectedItem(rs.getString(3));
                    niveauComboBox.setSelectedItem(rs.getInt(4));
                    groupeComboBox.setSelectedItem(rs.getInt(5));

                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }

        });
        modifierButton.addActionListener(e -> {
            dao.updateEtudiant(new Etudiant(Integer.parseInt(numEtdField.getText()),nomField.getText(),prenomField.getText(),(String)filiereComboBox.getSelectedItem(),(int)niveauComboBox.getSelectedItem(),(int)groupeComboBox.getSelectedItem()));
            String rq = "UPDATE ETUDIANT SET nom = '"+nomField.getText()+"', prenom = '"+prenomField.getText()+"', filiere = '"+filiereComboBox.getSelectedItem()+"', niveau = "+niveauComboBox.getSelectedItem()+", groupe = "+groupeComboBox.getSelectedItem()+" WHERE id = "+numEtdField.getText()+";";
            ResultSet rs = dao.selection(rq);
            model.updateTableWithNewResultSet(dao.selection("SELECT titre,lieu,datef FROM FORMATION f,demandeetd d,ETUDIANT e WHERE (e.id=d.idEtudiant) and (f.idF = d.idFormation) and e.id = "+numEtdField.getText()+";"));
            JOptionPane.showMessageDialog(this, "Etudiant modifié");
        });
        supprimerButton.addActionListener(e -> {
            dao.deleteEtudiant(Integer.parseInt(numEtdField.getText()));
            model.updateTableWithNewResultSet(dao.selection("SELECT titre,lieu,datef FROM FORMATION f,demandeetd d,ETUDIANT e WHERE 7=5;"));
            JOptionPane.showMessageDialog(this, "Etudiant supprimé");
            clearInputs();
        });

    }


}
