package repositories.inter;

import entities.medicine;
import java.util.ArrayList;

public interface InMedicine {
    public ArrayList<medicine> medicinefinder(String Name);

    public medicine getId(int id);

    public boolean setMedicine(medicine medicines);

    public boolean nosetMedicine(int id);
}
