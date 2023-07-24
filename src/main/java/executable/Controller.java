package executable;

import db.DataType.Preferences;
import db.SavedData;
import db.Task;
import org.joda.time.DateTime;

import executable.support.Blob;
import db.DataType.*;

import java.sql.*;
import java.util.ArrayList;


public class Controller {
    private static final String DBNAME = "dbTasktracker.db";
    private Connection conn;

    public Controller(){

    }

    private ResultSet getData(String sql){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+ DBNAME);
            Statement stat = conn.createStatement();
            return stat.executeQuery(sql);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Couldn't connect to database");
            e.printStackTrace();
            return null;
        }
    }

    private int setData(String sql){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+ DBNAME);
            Statement stat = conn.createStatement();
            return stat.executeUpdate(sql);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Couldn't connect to database");
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<Blob> getSavedData() {
        ArrayList<Blob> savedDataList = new ArrayList<>();

        ResultSet resultSet = getData("SELECT * from SavedData");

        try {
            while (resultSet.next()) {
                try {
                    String classname = resultSet.getString("classname");
                    Class cls = Class.forName(classname);
                    byte[] data = resultSet.getBytes("object");

                    savedDataList.add(new Blob(cls, data));
                }catch(ClassNotFoundException | SQLException e){
                    e.printStackTrace();
                }
            }

            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return savedDataList;
    }

    public void insertTask(Task task){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+ DBNAME);
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Task (name, lastUsed, useCount, marked) VALUES (?, ?, ?, ?)");
            stm.setString(1, task.getName());
            stm.setDate(2, new Date(task.getLastUsed().getMillis()));
            stm.setInt(3, task.getUsedCount());
            stm.setBoolean(4, task.isMarked());

            stm.execute();
            stm.close();
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Couldn't connect to database");
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void insertPreferences(Blob blob){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+ DBNAME);
            PreparedStatement stm = conn.prepareStatement("Update Table SavedData SET object = ? WHERE classname like 'db.DataType.Preferences'");
            stm.setBytes(1, blob.getData());

            stm.execute();
            stm.close();
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Couldn't connect to database");
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> allTasks = new ArrayList<>();

        ResultSet resultSet = getData("SELECT * from Task");

        try {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                DateTime lastUsed = new DateTime(resultSet.getDate("lastUsed"));
                int useCount = resultSet.getInt("usedCount");
                boolean marked = resultSet.getBoolean("marked");

                allTasks.add(new Task(name, lastUsed, useCount, marked));
            }

            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return  allTasks;
    }
}
