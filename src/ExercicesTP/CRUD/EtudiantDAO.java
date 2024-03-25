package ExercicesTP.CRUD;
import ExercicesTP.Config;
import ExercicesTP.Etudiant;
import ExercicesTP.interfaces.EtudiantDaoCRUD;
import TP_Base.MyConnexion;

import java.sql.*;


public class EtudiantDAO implements EtudiantDaoCRUD{


    Statement st = null;
    Connection con = null;

    public EtudiantDAO(Connection con) {
        try {
            st = con.createStatement();
            this.con = con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addEtudiant(Etudiant etudiant) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO etudiant (id,nom, prenom, filiere, niveau, groupe) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, etudiant.getId());
            ps.setString(2, etudiant.getNom());
            ps.setString(3, etudiant.getPrenom());
            ps.setString(4, etudiant.getFiliere());
            ps.setInt(5, etudiant.getNiveau());
            ps.setInt(6, etudiant.getGroupe());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEtudiant(int id) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM etudiant WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEtudiant(Etudiant etudiant) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE etudiant SET nom = ?, prenom = ?, filiere = ?, niveau = ?, groupe = ? WHERE id = ?");
            ps.setString(1, etudiant.getNom());
            ps.setString(2, etudiant.getPrenom());
            ps.setString(3, etudiant.getFiliere());
            ps.setInt(4, etudiant.getNiveau());
            ps.setInt(5, etudiant.getGroupe());
            ps.setInt(6, etudiant.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Etudiant getEtudiant(int id) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT * FROM etudiant WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("filiere"), rs.getInt("niveau"), rs.getInt("groupe"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Etudiant[] getAllEtudiants() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM etudiant");
            Etudiant[] etudiants = new Etudiant[getRowCount(rs)];
            int i = 0;
            while (rs.next()) {
                etudiants[i] = new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("filiere"), rs.getInt("niveau"), rs.getInt("groupe"));
                i++;
            }
            return etudiants;
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
