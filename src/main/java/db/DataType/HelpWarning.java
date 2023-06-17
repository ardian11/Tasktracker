package db.DataType;

import java.io.Serializable;

public class HelpWarning implements Serializable {
    private boolean showWarning;

    public HelpWarning(boolean warning){
        this.showWarning = warning;
    }

    public boolean getShowWarning(){
        return showWarning;
    }
}