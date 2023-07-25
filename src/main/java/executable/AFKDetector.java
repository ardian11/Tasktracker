package executable;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;

import javax.swing.*;
import java.util.TimerTask;
import java.util.Timer;

public class AFKDetector {

    //max. afk time in minutes
    private float MAX_MINUTES = 1.f;

    //time passed since last input in seconds
    private float secondsPassed = 0.f;

    private static AFKDetector afkDetector;
    private Timer timer;

    private AFKDetector(){
        // Initialize the native hook.
        try {
            GlobalScreen.registerNativeHook();
            System.out.println("Registered Hook");
        } catch (NativeHookException e) {
            initErrorMessage();
            throw new RuntimeException(e);
        }

        GlobalScreen.addNativeMouseMotionListener(new NativeMouseMotionListener() {
            @Override
            public void nativeMouseMoved(NativeMouseEvent nativeEvent) {
               secondsPassed = 0.f;
            }
        });

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
                secondsPassed = 0.f;
            }
        });

        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
                if(secondsPassed/60 > MAX_MINUTES){
                    System.out.println("You're AFK!");
                    timer.cancel();
                }
            }
        };

        timer.schedule(task, 0, 1000);


        // Set the GlobalScreen logger to null to avoid log messages.
        //GlobalScreen.setEventDispatcher(null);
    }

    public static AFKDetector activate(){
        if(afkDetector == null){
            afkDetector = new AFKDetector();
        }
        return afkDetector;
    }

    private void initErrorMessage(){
        JOptionPane.showMessageDialog(null, "AFK Detector cannot start!");
    }

    public void deactivate(){
        try {
            GlobalScreen.unregisterNativeHook();
            timer.cancel();
            afkDetector = null;
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
    }


}
