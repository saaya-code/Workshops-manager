package TP_Base;

import com.mysql.jdbc.log.NullLogger;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    ArrayList<Object[]> data;
    ResultSetMetaData rsmd;
    EtudiantDao dao;
    MyTableModel(ResultSet rs, EtudiantDao dao){
        data = new ArrayList<Object[]>();
        this.dao = new EtudiantDao(Config.URL,Config.USERNAME,Config.PASSWORD);
        try {
            rsmd = rs.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            while (rs.next()) {
                Object[] ligne = new Object[rsmd.getColumnCount()];
                for(int i = 0;i<rsmd.getColumnCount();i++){
                    ligne[i] = rs.getObject(i+1);
                }
                data.add(ligne);
            }
        }catch(SQLException SQLErr){
            System.out.println("Error "+SQLErr);
        }


    }


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column + 1);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return !getColumnName(columnIndex).equalsIgnoreCase("cin");
    }
    int colmunNameToIndex(String colmunName){
        for (int i = 0; i < getColumnCount(); i++) {
            if(getColumnName(i).equalsIgnoreCase(colmunName)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String nom = (String) data.get(rowIndex)[colmunNameToIndex("nom")];
        String prenom = (String) data.get(rowIndex)[colmunNameToIndex("prenom")];
        int cin = (int) data.get(rowIndex)[colmunNameToIndex("cin")];
        double moyenne = (double) data.get(rowIndex)[colmunNameToIndex("moyenne")];
        switch (getColumnName(columnIndex)){
            case "nom": nom = (String) aValue;
            break;
            case "prenom": prenom = (String) aValue;
            break;
            case "moynne": moyenne = (double) aValue;
        }
        dao.modfierEtudiant(nom, prenom, cin, moyenne);
        data.get(rowIndex)[columnIndex] = aValue;

    }

    public void insertEtudiant(String nom, String prenom, int cin, double moyenne){
        if(this.dao.insertEtudiant(nom,prenom,cin,moyenne) > 0) {
            data.add(new Object[]{nom, prenom, cin, moyenne});
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null, "done");

        }else {
            JOptionPane.showMessageDialog(null, "Not inserted..");
        }
    }
    public void supprimerEtudiant(int cin){
        int option = JOptionPane.showConfirmDialog(null, "est ce que vous etes sure ?");
        if(option == JOptionPane.NO_OPTION || option == JOptionPane.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "annulé");
            return;
        }

        if(this.dao.supprimerEtudiant(cin) > 0){
            for(int i = 0; i < data.size(); i++){
                if((int)data.get(i)[colmunNameToIndex("cin")] == cin){
                    data.remove(i);
                    fireTableDataChanged();
                    JOptionPane.showMessageDialog(null, "done");
                    return;
                }
            }
        }else {
            JOptionPane.showMessageDialog(null, "Not deleted..");
        }
    }
}
