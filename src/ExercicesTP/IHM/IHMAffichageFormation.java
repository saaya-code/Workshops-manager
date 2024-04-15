package ExercicesTP.IHM;

import javax.swing.*;
import ExercicesTP.CRUD.FormationDAO;
import ExercicesTP.Helpers.TableModelFormation;

import java.awt.*;

public class IHMAffichageFormation extends JInternalFrame {
    private JLabel idLabel, titleLabel, dateLabel, lieuLabel, certificationLabel;
    private JTextField idField, titleField, dateTextField, lieuTextField;
    private JCheckBox certificationCheckbox;
    private JButton rechercheButton, cancelButton, modifierButton, supprimerButton;
    public FormationDAO formationDAO;
    public TableModelFormation model;
    JTable jt_Formation;


    IHMAffichageFormation(FormationDAO dao){
        setTitle("Affichage des formations");
        setSize(400, 400);
        setVisible(true);
        formationDAO = dao;
        initilizeComponents();
        createLayout();
        addEventListeners();

    }
    public void initilizeComponents() {
        idLabel = new JLabel("ID :");
        titleLabel = new JLabel("Titre :");
        dateLabel = new JLabel("Date :");
        lieuLabel = new JLabel("Lieu :");
        certificationLabel = new JLabel("Certification :");

        jt_Formation = new JTable();
        String rq = "SELECT * FROM FORMATION;";
        model = new TableModelFormation(formationDAO.selection(rq), formationDAO);
        jt_Formation.setModel(model);


        idField = new JTextField(15);
        titleField = new JTextField(20);

        dateTextField = new JTextField(15);
        lieuTextField = new JTextField(15);

        certificationCheckbox = new JCheckBox();

        rechercheButton = new JButton("Rechercher");
        modifierButton = new JButton("Modifier");
        supprimerButton = new JButton("Supprimer");
        cancelButton = new JButton("Annuler");
    }
    public void createLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel2 = new JPanel(new GridBagLayout());

        // Add labels and corresponding components
        c.fill = GridBagConstraints.HORIZONTAL;
        //addComponent(panel, idLabel, c, 0, 0, 1, 1);
        //addComponent(panel, idField, c, 1, 0, 2, 1);
        addComponent(panel, titleLabel, c, 0, 1, 1, 1);
        addComponent(panel, titleField, c, 1, 1, 2, 1);
        addComponent(panel, dateLabel, c, 0, 2, 1, 1);
        addComponent(panel, dateTextField, c, 1, 2, 2, 1);
        addComponent(panel, lieuLabel, c, 0, 3, 1, 1);
        addComponent(panel, lieuTextField, c, 1, 3, 2, 1);
        addComponent(panel, certificationLabel, c, 0, 4, 1, 1);
        addComponent(panel, certificationCheckbox, c, 1, 4, 1, 1);
        addComponent(panel2, rechercheButton, c, 5, 1, 1, 1);
        addComponent(panel2, modifierButton, c, 5, 2, 1, 1);
        addComponent(panel2, supprimerButton, c, 5, 3, 1, 1);
        addComponent(panel2, cancelButton, c, 5, 4, 1, 1);

        // Add buttons
        getContentPane().add(panel);
        getContentPane().add(panel2);
        getContentPane().setLayout(new FlowLayout());

        setSize(500,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        JScrollPane scrollPane = new JScrollPane(jt_Formation);
        this.add(scrollPane, BorderLayout.CENTER);


    }
    private void addComponent(JPanel panel, JComponent component, GridBagConstraints c, int x, int y, int width, int height) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        panel.add(component, c);
    }
    private void addEventListeners(){
        rechercheButton.addActionListener((e)->{
            String rq = "SELECT * FROM FORMATION WHERE ";
            if(!titleField.getText().isEmpty()){
                rq += "titre = '"+titleField.getText()+"' AND ";
            }
            if(!dateTextField.getText().isEmpty()){
                rq += "datef = '"+dateTextField.getText()+"' AND ";
            }
            if(!lieuTextField.getText().isEmpty()){
                rq += "lieu = '"+lieuTextField.getText()+"' AND ";
            }
            if(certificationCheckbox.isSelected()){
                rq += "certif = true AND ";
            }
            rq = rq.substring(0, rq.length()-4);
            model.updateTableWithNewResultSet(formationDAO.selection(rq));
        });
        cancelButton.addActionListener((e)->{
            dispose();
        });
    }

}
