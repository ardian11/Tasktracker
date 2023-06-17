package executable;

public class Launcher {
    public static void main(String[] args) {
        Controller controller = new Controller();
        TaskTracker t = new TaskTracker(controller, controller.getSavedData());
    }
}