package ExercicesTP.CRUD;

import ExercicesTP.Formation;
import ExercicesTP.interfaces.FormationDaoCRUD;
import java.sql.*;

public class FormationDAO implements FormationDaoCRUD {

    Connection con = null;
    Statement st = null;
    public FormationDAO(Connection con){
        try {
        this.con = con;
        st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addFormation(Formation formation) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO formation (idF, titre, datef, lieu, certif) VALUES (?,?,?,?,?)");
            ps.setInt(1, formation.getId());
            ps.setString(2, formation.getTitle());
            ps.setDate(3, formation.getDateF());
            ps.setString(4, formation.getLieu());
            ps.setBoolean(5, formation.getCertification());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFormation(int id) {
        try {
            st.executeUpdate("DELETE FROM formation WHERE IdF = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selection(String req){
        try{
            PreparedStatement ps = con.prepareStatement(req);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur sql + "+ e.getMessage());
            return null;
        }
    }
    public void updateFormation(Formation formation) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE formation SET titre = ?, datef = ?, lieu = ?, certif = ? WHERE IdF = ?");
            ps.setString(1, formation.getTitle());
            ps.setDate(2, formation.getDateF());
            ps.setString(3, formation.getLieu());
            ps.setBoolean(4, formation.getCertification());
            ps.setInt(5, formation.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Formation getFormation(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM formation WHERE IdF = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Formation(rs.getInt(1), rs.getString(2), rs.getDate(4), rs.getString(3), rs.getBoolean(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Formation[] getAllFormations() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM formation");
            Formation[] formations = new Formation[getRowCount(rs)];
            int i = 0;
            while (rs.next()) {
                formations[i] = new Formation(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getBoolean(5));
                i++;
            }
            return formations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getRowCount(ResultSet rs) {
        try {
            int rowCount = 0;
            while(rs.next())
                rowCount++;
            return rowCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
