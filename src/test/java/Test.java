import db.DataType.HelpWarning;
import db.DataType.Preferences;
import ui.TrackerUI;

public class Test {
    public static void main(String[] args) {
        testUI();
    }

    public static void testUI(){
        new TrackerUI();
    }

    public static void fullQualifiedNames(){
        System.out.println(Preferences.class.getName());
        System.out.println(HelpWarning.class.getName());
    }
}
