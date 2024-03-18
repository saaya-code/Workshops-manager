package ExercicesTP;
import TP_Base.MyConnexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exercice1 {
    public static void main(String[] args) {
        Statement st = null;
        Connection con = MyConnexion.getConnection(Config.URL, Config.USERNAME, Config.PASSWORD);
        try {
            st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM ETUDIANT;");
            String line = "------------------------";
            System.out.println(line);
            System.out.println("| Nom | Prenom  | Moyenne |");
            System.out.println(line);
            while (res.next()){
                System.out.println(" | "+res.getString(2) + " | " + res.getString(3) + " | " + res.getDouble(4)+" | ");
            }
            System.out.println(line);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
