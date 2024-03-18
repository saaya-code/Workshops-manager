package ExercicesTP;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Formation {
    public int Id;
    public String Title;
    public Date DateF;
    public String lieu;
    public Boolean certification;
    public Formation(int Id, String Title, Date DateF, String lieu, Boolean certification){
        this.Id = Id;
        this.Title = Title;
        this.DateF = DateF;
        this.lieu = lieu;
        this.certification = certification;
    }

    public Boolean getCertification() {
        return certification;
    }

    public Date getDateF() {
        return DateF;
    }

    public int getId() {
        return Id;
    }

    public String getLieu() {
        return lieu;
    }

    public String getTitle() {
        return Title;
    }

    public void setCertification(Boolean certification) {
        this.certification = certification;
    }

    public void setDateF(Date dateF) {
        DateF = dateF;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public static java.sql.Date parseDateToSqlDate(String date) {
        java.util.Date dateUtil = null;
        try {
            dateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new java.sql.Date(dateUtil.getTime());
    }
}
