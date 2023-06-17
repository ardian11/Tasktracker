package executable;

import db.DataType.Preferences;
import db.SavedData;
import db.Task;
import executable.support.Blob;
import org.joda.time.DateTime;

import java.io.*;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskTracker {

    private SavedData<Preferences> preferences;

    private ArrayList<SavedData> savedDataList;

    private Controller controller;
    public TaskTracker(Controller controller, ArrayList<Blob> savedData){
        this.controller = controller;
        getSavedData(controller.getSavedData());
    }


    public void newTask(String name, DateTime lastUsed, int useCount, boolean marked){
        controller.insertTask(new Task(name, lastUsed, useCount, marked));
    }

    public ArrayList<Task> getAllTasks(){
        return controller.getAllTasks();
    }

    private void getSavedData(ArrayList<Blob> blobs){
        for(Blob blob : blobs){
            if(blob.getCls() == Preferences.class){
                preferences = (SavedData<Preferences>) blobToData(blob).getObject();
            }else{
                savedDataList.add(blobToData(blob));
            }
        }
    }

    private void savePreferences(){
        controller.insertPreferences(dataToBlob(preferences, Preferences.class));
    }


    //SUPPORT METHODS

    private <T> Blob dataToBlob(SavedData<T> savedData, Class<T> cls){
        Blob b = new Blob(cls, null);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(savedData.getObject());
            oos.close();
            b.setData(baos.toByteArray());
        }catch (IOException e){
            e.printStackTrace();
        }
        return b;
    }

    private <T> SavedData<T> blobToData(Blob blob){
        SavedData<T> savedData = new SavedData<T>(null);
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(blob.getData());
            ObjectInput ois = new ObjectInputStream(bais);
            savedData.setObject((T) ois.readObject());
            ois.close();
        }catch (ClassNotFoundException |IOException e){
            e.printStackTrace();
        }
        return savedData;
    }


}
