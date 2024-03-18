package ExercicesTP.IHM;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ExercicesTP.CRUD.FormationDAO;
import ExercicesTP.Formation;

public class IHMRechercheFormation extends JFrame {

    private JLabel referenceLabel, titleLabel, dateLabel, lieuLabel, certificationLabel;
    private JTextField referenceField, titleField;
    private JTextField dateTextField, lieuTextField;
    private JCheckBox certificationCheckbox;
    private JButton rechercheButton, cancelButton, modifierButton, supprimerButton;
    public FormationDAO formationDAO;
    public IHMRechercheFormation() {
        super("Ajout d'une formation");
        initializeComponents();
        createLayout();
        addEventListeners();
        formationDAO = new FormationDAO();
        this.setVisible(true);
    }

    private void initializeComponents() {
        referenceLabel = new JLabel("Référence :");
        titleLabel = new JLabel("Titre :");
        dateLabel = new JLabel("Date :");
        lieuLabel = new JLabel("Lieu :");
        certificationLabel = new JLabel("Certification :");

        referenceField = new JTextField(15);
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

    private void createLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel2 = new JPanel(new GridBagLayout());

        // Add labels and corresponding components
        c.fill = GridBagConstraints.HORIZONTAL;
        addComponent(panel, referenceLabel, c, 0, 0, 1, 1);
        addComponent(panel, referenceField, c, 1, 0, 2, 1);
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
        setLocationRelativeTo(null);
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
        referenceField.setText("");
        titleField.setText("");
        dateTextField.setText("");
        lieuTextField.setText("");
        certificationCheckbox.setSelected(false);
    }
    private void addEventListeners() {
        rechercheButton.addActionListener(e -> {
            Formation formation = formationDAO.getFormation(Integer.parseInt(referenceField.getText()));
            if (formation != null) {
                titleField.setText(formation.getTitle());
                dateTextField.setText(formation.getDateF().toString());
                lieuTextField.setText(formation.getLieu());
                certificationCheckbox.setSelected(formation.getCertification());
            } else {
                clearInputs();
                JOptionPane.showMessageDialog(this, "Formation non trouvée");
            }

        });

        cancelButton.addActionListener(e -> dispose());

        modifierButton.addActionListener(e -> {
            Formation formation = new Formation(Integer.parseInt(referenceField.getText()), titleField.getText(), Formation.parseDateToSqlDate(dateTextField.getText()), lieuTextField.getText(), certificationCheckbox.isSelected());
            formationDAO.updateFormation(formation);
            JOptionPane.showMessageDialog(this, "Formation modifiée");
        });
        supprimerButton.addActionListener(e -> {
            formationDAO.deleteFormation(Integer.parseInt(referenceField.getText()));
            JOptionPane.showMessageDialog(this, "Formation supprimée");
            clearInputs();

        });

    }


}
