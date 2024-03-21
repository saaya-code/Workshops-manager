package ExercicesTP.interfaces;
import ExercicesTP.Formation;

import java.sql.ResultSet;

public interface FormationDaoCRUD {
    public void addFormation(Formation formation);
    public void deleteFormation(int id);
    public void updateFormation(Formation formation);
    public Formation getFormation(int id);
    public Formation[] getAllFormations();

    public ResultSet selection(String rq);
}
