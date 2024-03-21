package ExercicesTP.Helpers;

import ExercicesTP.CRUD.FormationDAO;
import ExercicesTP.Formation;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {
    ArrayList<Object[]> data;
    ResultSetMetaData rsmd;
    FormationDAO dao;

    public TableModel(ResultSet rs, FormationDAO dao){
        data = new ArrayList<Object[]>();
        this.dao = dao;
        try {
            rsmd = rs.getMetaData();
            Object[] ligne = new Object[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                ligne[i] = rsmd.getColumnName(i+1);
            }
            data.add(ligne);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {

            while (rs.next()) {
                Object[] ligne = new Object[rsmd.getColumnCount()];
                for(int i = 0;i<rsmd.getColumnCount();i++){
                    ligne[i] = rs.getObject(i+1);
                    System.out.println(ligne[i]);
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
        return !getColumnName(columnIndex).equalsIgnoreCase("id");
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
        int id = (int) data.get(rowIndex)[colmunNameToIndex("id")];
        String titre = (String) data.get(rowIndex)[colmunNameToIndex("titre")];
        Date dateF = (Date) data.get(rowIndex)[colmunNameToIndex("DateF")];
        String lieu = (String) data.get(rowIndex)[colmunNameToIndex("lieu")];
        boolean certification = (boolean) data.get(rowIndex)[colmunNameToIndex("certification")];
        switch (getColumnName(columnIndex)){
            case "titre": titre = (String) aValue;
                break;
            case "datef": dateF = (Date) aValue;
                break;
            case "lieu": lieu = (String) aValue;
                break;
            case "certification": certification = (boolean) aValue;

        }
        dao.updateFormation(new Formation(id, titre, dateF, lieu, certification));
        data.get(rowIndex)[columnIndex] = aValue;

    }

    public void insertFormation(int id, String titre, Date dateF, String lieu, boolean certification){
        this.dao.addFormation(new Formation(id, titre, dateF, lieu,certification));
            data.add(new Object[]{id, titre, dateF, lieu, certification});
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null, "done");
    }



    public void supprimerFromation(int id){
        int option = JOptionPane.showConfirmDialog(null, "est ce que vous etes sure ?");
        if(option == JOptionPane.NO_OPTION || option == JOptionPane.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "annulé");
            return;
        }

        this.dao.deleteFormation(id);
            for(int i = 0; i < data.size(); i++){
                if((int)data.get(i)[colmunNameToIndex("id")] == id){
                    data.remove(i);
                    fireTableDataChanged();
                    JOptionPane.showMessageDialog(null, "done");
                    return;
                }
            }

    }
}
