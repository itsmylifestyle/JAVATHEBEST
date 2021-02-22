import data.PostgresDB;
import data.inter.IDBManager;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import repositories.MedicineRep;
import repositories.inter.InMedicine;

public class MyApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(PostgresDB.class).to(IDBManager.class);
        bind(MedicineRep.class).to(InMedicine.class);
    }
}