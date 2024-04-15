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

    public Object[] fetchFormationData(int idFormation, int idEtudiant){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT f.titre, f.lieu, f.datef FROM formation f,Etudiant e,demandeetd d WHERE f.IdF=d.IdFormation and e.id=d.IdEtudiant and f.IdF = ? and e.id = ?");
            ps.setInt(1, idFormation);
            ps.setInt(2, idEtudiant);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Object[]{rs.getString(2), rs.getDate(3), rs.getString(4)};
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public void insertDemande(int idFormation, int idEtudiant){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("INSERT INTO demandeetd (IdFormation, IdEtudiant) VALUES (?,?)");
            ps.setInt(1, idFormation);
            ps.setInt(2, idEtudiant);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int supprimeDemandePublic(int idEtudiant, String lieu, String titre){
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT id FROM demandeetd WHERE IdEtudiant = ? and IdFormation in (SELECT IdF FROM formation WHERE lieu = ? and titre = ?)");
            ps.setInt(1, idEtudiant);
            ps.setString(2, lieu);
            ps.setString(3, titre);
            ResultSet rs = ps.executeQuery();
            int result = 0;
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("id : "+id);
                ps = con.prepareStatement("DELETE FROM demandeetd WHERE id = ?");
                ps.setInt(1, id);
                result = ps.executeUpdate();
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selection(String rq){
        try {
            return st.executeQuery(rq);
        } catch (SQLException e) {
            return null;
        }
    }
}
