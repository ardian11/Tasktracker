package ui.support;

import ui.TaskUI;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ComboBoxKeyListener extends KeyAdapter {

    TaskUI taskUI;
    ArrayList<String> taskNames;

    public ComboBoxKeyListener(TaskUI taskUI, ArrayList<String> taskNames){
        this.taskUI = taskUI;
        this.taskNames = taskNames;
        this.box = taskUI.getTaskNameBox();
    }

    String inputBuffer = "";
    JComboBox<String> box;

    @Override
    public void keyTyped(KeyEvent e) {
        if(!box.isPopupVisible()){
            box.showPopup();
        }

        boolean removed = false;

        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
            if(inputBuffer.length() > 0) {
                inputBuffer = inputBuffer.substring(0, inputBuffer.length() - 1);
                System.out.println("Remove");
                removed = true;
            }

            if(inputBuffer.isEmpty()){
                box.removeAllItems();
                taskNames.forEach(name -> box.addItem(name));
            }
        }else{
            inputBuffer += e.getKeyChar();
            System.out.println("Add");
        }

        box.removeAllItems();
        if(!inputBuffer.isEmpty()) {
            if(removed) {
                box.setSelectedItem(inputBuffer);
            }else{
                box.setSelectedItem(inputBuffer.substring(0, inputBuffer.length() - 1));
            }
        }
        System.out.println("Inputbuffer: " + inputBuffer);
        for(String name : taskNames){
            if(name.startsWith(inputBuffer)){
                box.addItem(name);
            }
        }

    }
}
