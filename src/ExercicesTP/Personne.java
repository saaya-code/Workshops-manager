package ExercicesTP;

abstract public class Personne {
    public int Id;
    public String nom;
    public String prenom;

    public Personne(int Id, String nom, String prenom) {
        this.Id = Id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
