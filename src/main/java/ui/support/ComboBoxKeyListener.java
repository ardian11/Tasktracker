package ui.support;

import ui.TaskUI;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ComboBoxKeyListener extends KeyAdapter {

    TaskUI taskUI;
    ArrayList<String> taskNames;

    Thread taskLoading;

    public ComboBoxKeyListener(TaskUI taskUI, ArrayList<String> taskNames){
        this.taskUI = taskUI;
        this.taskNames = taskNames;
        this.box = taskUI.getTaskNameBox();
        taskLoading = new Thread();
    }

    String inputBuffer = "";
    JComboBox<String> box;

    @Override
    public void keyTyped(KeyEvent e) {
        taskLoading.interrupt();

        if(!box.isPopupVisible()){
            box.showPopup();
        }

        boolean removed = false;

        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
            if(inputBuffer.length() > 0) {
                inputBuffer = inputBuffer.substring(0, inputBuffer.length() - 1);
                removed = true;
            }

            if(inputBuffer.isEmpty()){
                box.removeAllItems();
                taskNames.forEach(name -> box.addItem(name));
            }
        }else{
            inputBuffer += e.getKeyChar();
        }

        box.removeAllItems();
        box.hidePopup();
        if(!inputBuffer.isEmpty()) {
            if(removed) {
                box.setSelectedItem(inputBuffer);
            }else{
                box.setSelectedItem(inputBuffer.substring(0, inputBuffer.length() - 1));
            }
        }
        for(String name : taskNames){
            if(name.startsWith(inputBuffer)){
                box.addItem(name);
            }
        }
        box.showPopup();

        taskLoading = new TaskLoadingThread(taskUI, inputBuffer, taskNames);
        taskLoading.start();


    }
}
