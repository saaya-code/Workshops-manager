package TP_Base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;


public class GestionEtudiant extends JFrame {
    JPanel northPannel;
    JLabel nomField;
    JLabel prenomField;
    JLabel cinField;
    JLabel moyenneField;
    JTextField nom;
    JTextField prenom;
    JTextField cin;
    JTextField moyenne;
    JButton valider;
    JTable jt_Etud;
    MyTableModel model;

    GestionEtudiant(){
        this.setTitle("Gestion de l'etudiant");
        this.setSize(700,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        nomField = new JLabel("Nom");
        prenomField = new JLabel("Prenom");
        cinField = new JLabel("Cin");
        moyenneField = new JLabel("Moyenne");
        nom = new JTextField(5);
        prenom = new JTextField(5);
        cin = new JTextField(5);
        moyenne = new JTextField(5);
        northPannel = new JPanel();
        valider = new JButton("Valider");


        northPannel.add(nomField);
        northPannel.add(nom);
        northPannel.add(prenomField);
        northPannel.add(prenom);
        northPannel.add(cinField);
        northPannel.add(cin);
        northPannel.add(moyenneField);
        northPannel.add(moyenne);
        northPannel.add(valider);
        northPannel.setBackground(Color.LIGHT_GRAY);


        jt_Etud = new JTable();
        String rq = "SELECT * FROM ETUDIANT;";
        EtudiantDao dao = new EtudiantDao(Config.URL, Config.USERNAME, Config.PASSWORD);
        ResultSet rs = dao.selection(rq);
        model = new MyTableModel(rs, dao);
        jt_Etud.setModel(model);


        this.add(new JScrollPane(jt_Etud));
        this.add(northPannel, BorderLayout.NORTH);
        this.setVisible(true);



        // ecouteur d'evts


        valider.addActionListener((ae)->{
            String nomTextValue = nom.getText();
            String prenomTextValue = prenom.getText();
            int cinTextValue = Integer.parseInt(cin.getText());
            double moyenneTextValue = Double.parseDouble(moyenne.getText());
            model.insertEtudiant(nomTextValue,prenomTextValue, cinTextValue, moyenneTextValue);

        });

        jt_Etud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3){
                    int row = jt_Etud.rowAtPoint(e.getPoint());
                    //jt_Etud.setRowSelectionInterval(row, row);
                    JPopupMenu jpm = new JPopupMenu();
                    JMenuItem supprimer = new JMenuItem("Supprimer");
                    jpm.add(supprimer);
                    jpm.show(jt_Etud, e.getX(), e.getY());
                    supprimer.addActionListener((ae)->{
                        int cin = (int) model.getValueAt(row, 2);
                        model.supprimerEtudiant(cin);
                    });

                }
            }
        });
    }
}
