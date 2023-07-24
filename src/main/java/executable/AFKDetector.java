package executable;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;

import javax.swing.*;

public class AFKDetector {

    //max. afk time in minutes
    private float MAX_TIME = 1;

    private float timePassed = 0;

    private static AFKDetector afkDetector;

    private AFKDetector(){
        // Initialize the native hook.
        try {
            GlobalScreen.registerNativeHook();
            System.out.println("Registered Hook");
        } catch (NativeHookException e) {
            initErrorMessage();
            throw new RuntimeException(e);
        }

        // Add the NativeMouseInputListener to listen for mouse events.
        GlobalScreen.addNativeMouseMotionListener(new NativeMouseMotionListener() {
            @Override
            public void nativeMouseMoved(NativeMouseEvent nativeEvent) {
                System.out.println("x: " + nativeEvent.getX() + ", y: " + nativeEvent.getY());
            }
        });

        //TODO implement timer


        // Set the GlobalScreen logger to null to avoid log messages.
        //GlobalScreen.setEventDispatcher(null);
    }

    public static AFKDetector getAfkDetector(){
        if(afkDetector == null){
            afkDetector = new AFKDetector();
        }
        return afkDetector;
    }

    private void initErrorMessage(){
        JOptionPane.showMessageDialog(null, "AFK Detector cannot start!");
    }

    public void dispose(){
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
    }


}
