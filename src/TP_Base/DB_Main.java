package TP_Base;


public class DB_Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1/db_tp_java";
        String username = "root";
        String password = "";
        //EtudiantDao dao = new EtudiantDao(url, username, password);
        GestionEtudiant gestionEtudiant = new GestionEtudiant();


        //dao.afficheAll("SELECT * FROM ETUDIANT;");
        //dao.modfierEtudiant("cc","cc2",11212325, 16.00);
        //dao.afficheAll("SELECT * FROM ETUDIANT;");
    }
}