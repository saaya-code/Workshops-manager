package ExercicesTP;

public class Enseignant extends Personne{
    String specialite;
    String grade;
    public Enseignant(int Id, String nom, String prenom, String specialite, String grade) {
        super(Id, nom, prenom);
        this.specialite = specialite;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
