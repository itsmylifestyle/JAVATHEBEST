package repositories;

import data.inter.IDBManager;
import repositories.inter.InMedicine;
import entities.medicine;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class MedicineRep implements InMedicine {

        public final IDBManager DBManager;

        public MedicineRep (IDBManager DBManager) {
            this.DBManager = DBManager;
        }

        @Override
        public ArrayList<medicine> medicinefinder(String name) {
            Connection connection = null;

            try {
                connection = DBManager.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medicine WHERE name LIKE'%"+ name + "%'");

                ResultSet resultSet = preparedStatement.executeQuery();

                ArrayList <medicine> medicines = new ArrayList<>();

                while (resultSet.next()) {
                    medicine medicine1;
                    medicine1 = new medicine(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getDate("expirationDate").toLocalDate(),
                            resultSet.getString("manufacturer"));

                    medicines.add(medicine1);
                }
                return medicines;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public medicine getId(int id) {

            Connection connection = null;

            try {
                connection = DBManager.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medicine WHERE id=?");

                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                medicine medicine = new medicine ();

                if (resultSet.next()) {
                    medicine.setId(resultSet.getInt("id"));
                    medicine.setName(resultSet.getString("name"));
                    medicine.setPrice(resultSet.getDouble("price"));
                    medicine.setexpirationDate(resultSet.getDate("expirationDate").toLocalDate());
                    medicine.setManufacturer(resultSet.getString("manufacturer"));
                }

                return medicine;
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public boolean setMedicine(medicine medicine) {
            Connection connection = null;

            try {
                connection = DBManager.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medicine (name, price, expirationDate, manufacturer), VALUES (?,?,?,?)");

                preparedStatement.setString(1, medicine.getName());
                preparedStatement.setDouble(2, medicine.getPrice());
                preparedStatement.setDate(3, Date.valueOf(medicine.getexpirationDate()));
                preparedStatement.setString(4, medicine.getManufacturer());

                preparedStatement.execute();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        public boolean nosetMedicine(int id) {
            Connection connection = null;

            try {
                connection = DBManager.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM medicine WHERE id=? ");

                preparedStatement.setInt(1, id);

                preparedStatement.execute();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

    public abstract List<medicine> medicinefinder();
}
