package ExercicesTP.interfaces;

import ExercicesTP.Etudiant;

public interface EtudiantDaoCRUD {
    public void addEtudiant(Etudiant etudiant);
    public void deleteEtudiant(int id);
    public void updateEtudiant(Etudiant etudiant);
    public Etudiant getEtudiant(int id);
    public Etudiant[] getAllEtudiants();
}
