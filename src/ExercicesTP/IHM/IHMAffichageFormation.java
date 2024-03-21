package ExercicesTP.IHM;

import javax.swing.*;
import ExercicesTP.CRUD.FormationDAO;
import ExercicesTP.Helpers.TableModel;

public class IHMAffichageFormation extends JInternalFrame {
    private JLabel idLabel, titleLabel, dateLabel, lieuLabel, certificationLabel;
    private JTextField idField, titleField, dateTextField, lieuTextField;
    private JCheckBox certificationCheckbox;
    private JButton rechercheButton, cancelButton, modifierButton, supprimerButton;
    public FormationDAO formationDAO;
    public TableModel model;
    JTable jt_Formation;


    IHMAffichageFormation(FormationDAO dao){
        setTitle("Affichage des formations");
        setSize(400, 400);
        setVisible(true);
        formationDAO = dao;
        initilizeComponents();
        createLayout();
        //addEventListeners();

    }
    public void initilizeComponents() {
        idLabel = new JLabel("ID :");
        titleLabel = new JLabel("Titre :");
        dateLabel = new JLabel("Date :");
        lieuLabel = new JLabel("Lieu :");
        certificationLabel = new JLabel("Certification :");

        jt_Formation = new JTable();
        String rq = "SELECT * FROM FORMATION;";
        model = new TableModel(formationDAO.selection(rq), formationDAO);
        jt_Formation.setModel(model);


        idField = new JTextField(15);
        titleField = new JTextField(20);

        // Sample data for comboboxes (replace with actual data source)
        dateTextField = new JTextField(15);
        lieuTextField = new JTextField(15);

        certificationCheckbox = new JCheckBox();

        rechercheButton = new JButton("Rechercher");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        cancelButton = new JButton("Annuler");
    }
    public void createLayout() {
        JPanel panel = new JPanel();
        panel.add(rechercheButton);
        panel.add(modifierButton);
        panel.add(supprimerButton);
        panel.add(cancelButton);
        panel.add(jt_Formation);

        this.add(panel);


    }

}
