package TP_Base;

public interface EtudiantDaoCRUD {
    public int insertEtudiant(String nom, String prenom, int cin, double moyenne);
    public int supprimerEtudiant(int cin);
    public int modfierEtudiant(String nom, String prenom, int cin, double moyenne);
    public void afficheAll(String req);
}
