package ui.support;

import ui.TaskUI;

import java.util.ArrayList;

public class TaskLoadingThread extends Thread{

    TaskUI taskUI;
    String inputBuffer;
    ArrayList taskNames;

    public TaskLoadingThread(TaskUI taskUI, String inputBuffer, ArrayList<String> taskNames){
        this.inputBuffer = inputBuffer;
        this.taskNames = taskNames;
        this.taskUI = taskUI;
    }

    @Override
    public void run() {
        //load Task if name exists
        if(taskNames.contains(inputBuffer)){
            taskUI.loadTask(inputBuffer);
        }
    }
}
