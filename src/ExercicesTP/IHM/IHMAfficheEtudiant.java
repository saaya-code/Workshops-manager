package ExercicesTP.IHM;

import ExercicesTP.CRUD.EtudiantDAO;
import ExercicesTP.Helpers.TableModelEtudiant;

import javax.swing.*;
import java.awt.*;

public class IHMAfficheEtudiant extends JInternalFrame {
    EtudiantDAO dao;
    JScrollPane jsp;
    public TableModelEtudiant model;
    JTable jt_Formation;
    JButton exitButton;
    JButton refreshButton;

    public IHMAfficheEtudiant(EtudiantDAO dao){
        this.dao = dao;
        setTitle("Affichage des étudiants");
        setSize(400, 400);
        initializeComponents();
        createLayout();
        addEventListeners();
        setVisible(true);
    }
    void initializeComponents(){
        jt_Formation = new JTable();
        jsp = new JScrollPane(jt_Formation);
        String rq = "SELECT * FROM Etudiant;";
        model = new TableModelEtudiant(dao.selection(rq), this.dao);
        jt_Formation.setModel(model);
        refreshButton = new JButton("Rafrachir");
        exitButton = new JButton("Exit");

    }
    void createLayout(){

        this.add(jsp, BorderLayout.CENTER);
        JPanel pane = new JPanel();
        pane.add(refreshButton);
        pane.add(exitButton);
        this.add(pane,BorderLayout.SOUTH);

    }
    void addEventListeners(){
        refreshButton.addActionListener(e->{
            String rq = "SELECT * FROM ETUDIANT";
            model.updateTableWithNewResultSet(dao.selection(rq));
            JOptionPane.showMessageDialog(this, "Refresh avec success");
        });
        exitButton.addActionListener(e->{
            dispose();
        });
    }
}
