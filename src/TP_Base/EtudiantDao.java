package TP_Base;

import javax.xml.transform.Result;
import java.sql.*;

public class EtudiantDao implements EtudiantDaoCRUD{

    Connection con = null;
    Statement st = null;
    EtudiantDao(String url, String username, String password){
        this.con = MyConnexion.getConnection(url, username, password);
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int insertEtudiant(String nom, String prenom, int cin, double moyenne) {
        String sql1 = "INSERT INTO ETUDIANT VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql1);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setInt(3, cin);
            ps.setDouble(4, moyenne);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur sql "+ e.getMessage());
            return 0;
        }

    }

    @Override
    public int supprimerEtudiant(int cin) {
        String sql1 = "DELETE FROM ETUDIANT WHERE (cin=?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql1);
            ps.setInt(1,cin);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error sql + "+ e.getMessage());
            return 0;
        }
    }

    @Override
    public int modfierEtudiant(String nom, String prenom, int cin, double moyenne) {
        String sql = "UPDATE ETUDIANT SET nom=?,prenom=?,moyenne=? WHERE cin=?;";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setDouble(3, moyenne);
            ps.setInt(4, cin);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error sql : "+e.getMessage());
            return 0;
        }
    }

    ResultSet selection(String req){
        try{
            PreparedStatement ps = con.prepareStatement(req);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur sql + "+ e.getMessage());
            return null;
        }
    }
    void afficheResultSet(ResultSet rs){
        try {
            while(rs.next()){
                String nom = rs.getString(1);
                String prenom = rs.getString(2);
                int cin = rs.getInt(3);
                double moyenne = rs.getDouble(4);
                System.out.println("Nom: "+nom+" Prénom: "+prenom+ " cin: "+cin+ " moyenne: "+ moyenne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void afficheAll(String req) {
        afficheResultSet(selection(req));
    }
}
