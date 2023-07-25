package executable;

import db.DataType.Preferences;
import db.SavedData;
import db.Task;
import executable.support.Blob;
import org.joda.time.DateTime;
import ui.TaskUI;
import ui.TrackerUI;

import java.io.*;
import java.util.ArrayList;

public class TaskTracker {

    private SavedData<Preferences> preferences;

    private ArrayList<SavedData> savedDataList;
    private TrackerUI ui;

    private Controller controller;
    public TaskTracker(Controller controller, ArrayList<Blob> savedData){
        this.controller = controller;
        getSavedData(controller.getSavedData());
        ui = new TrackerUI(this);
        if(preferences.getObject().afkWarning()) {
            AFKDetector.activate();
        }
    }

    public void setNewTasks(ArrayList<TaskUI> tasksUI){
        ArrayList<Task> newTasks = new ArrayList<>();
        ArrayList<Task> currentTasks = controller.getAllTasks();

        for(TaskUI taskUI : tasksUI){
            if(taskUI.getSavedTaskName() != null && !taskUI.getSavedTaskName().isEmpty()){
                boolean found = false;
                for(Task task : currentTasks){
                    if(taskUI.getSavedTaskName().equals(task.getName())){
                        task.setUsedCount(task.getUsedCount() + 1);
                        task.setMarked(taskUI.getMark().isSelected());
                        controller.updateTask(task);
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    controller.insertTask(new Task(taskUI.getSavedTaskName(), DateTime.now(), 1, taskUI.getMark().isSelected()));
                }
            }
        }
    }

    public ArrayList<Task> getAllTasks(){
        return controller.getAllTasks();
    }

    private void getSavedData(ArrayList<Blob> blobs){
        for(Blob blob : blobs){
            if(blob.getCls() == Preferences.class){
                preferences = new SavedData<Preferences>((Preferences) blobToData(blob).getObject());
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

        if(blob.getCls() == Preferences.class && blob.getData() == null){
            return new SavedData(new Preferences());
        }
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
