import db.DataType.HelpWarning;
import db.DataType.Preferences;
import executable.Controller;
import executable.TaskTracker;
import ui.TrackerUI;

public class Test {
    public static void main(String[] args) {
        testUI();
    }

    public static void testUI(){
        Controller c = new Controller();
        new TrackerUI(new TaskTracker(c, c.getSavedData()));
    }

    public static void fullQualifiedNames(){
        System.out.println(Preferences.class.getName());
        System.out.println(HelpWarning.class.getName());
    }
}
