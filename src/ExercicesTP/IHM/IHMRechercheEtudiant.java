package ExercicesTP.IHM;

import javax.swing.*;
import java.awt.*;

import ExercicesTP.CRUD.EtudiantDAO;
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
    JTable jt_Etudiant;

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

        numEtdField = new JTextField(15);
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
        String rq = "SELECT titre,lieu,datef FROM FORMATION f,demandeetd d,ETUDIANT e WHERE (e.id=d.idEtudiant) and (f.idF = d.idFormation) ;";
        model = new TableModelEtudiant(dao.selection(rq), dao);
        jt_Etudiant.setModel(model);

    }

    private void createLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel2 = new JPanel(new GridBagLayout());

        // Add labels and corresponding components
        c.fill = GridBagConstraints.HORIZONTAL;
        addComponent(panel, numEtdLabel, c, 0, 0, 1, 1);
        addComponent(panel, numEtdField, c, 1, 0, 2, 1);
        addComponent(panel, nomLabel, c, 0, 1, 1, 1);
        addComponent(panel, nomField, c, 1, 1, 2, 1);
        addComponent(panel, prenomLabel, c, 0, 2, 1, 1);
        addComponent(panel, prenomField, c, 1, 2, 2, 1);
        addComponent(panel, filiereLabel, c, 0, 3, 1, 1);
        addComponent(panel, filiereComboBox, c, 1, 3, 2, 1);
        addComponent(panel, niveauLabel, c, 0, 4, 1, 1);
        addComponent(panel, niveauComboBox, c, 1, 4, 1, 1);
        addComponent(panel, groupeLabel, c, 0, 5, 1, 1);
        addComponent(panel, groupeComboBox, c, 1, 5, 1, 1);

        addComponent(panel2, rechercheButton, c, 5, 0, 1, 1);
        addComponent(panel2, modifierButton, c, 5, 1, 1, 1);
        addComponent(panel2, supprimerButton, c, 5, 2, 1, 1);
        addComponent(panel2, cancelButton, c, 5, 3, 1, 1);

        addComponent(panel, ajouterDemandeButton, c, 0, 7, 1, 1);
        addComponent(panel, supprimerDemandeButton, c, 1, 7, 1, 1);
        addComponent(panel, demandesBox, c, 2,7,1, 1);

        addComponent(panel, listeDemandesLabel, c, 0, 10, 1, 1);
        JScrollPane scrollPane = new JScrollPane(jt_Etudiant);

        addComponent(panel, scrollPane, c, 2, 10, 6, 1);

        // Add buttons
        getContentPane().add(panel);
        getContentPane().add(panel2);
        getContentPane().setLayout(new FlowLayout());
        modifierButton.setEnabled(false);
        supprimerButton.setEnabled(false);
        //rechercheButton.setEnabled(false);



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
      //TODO implement
    }

    private void addEventListeners() {
       //TODO implement
        rechercheButton.addActionListener(e -> {
            String rq = "SELECT titre,lieu,datef FROM FORMATION f,demandeetd d,ETUDIANT e WHERE (e.id=d.idEtudiant) and (f.idF = d.idFormation) ;";
            model.updateTableWithNewResultSet(dao.selection(rq));

        });
    }


}
