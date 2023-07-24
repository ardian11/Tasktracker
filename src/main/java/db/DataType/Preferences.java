package db.DataType;

import java.io.Serializable;

public class Preferences implements Serializable {
    private int roundMinutesTo = 15;
    private boolean autoPause = true;
    private boolean afkWarning = true;

    private int dropboxSize;

    public Preferences(){
    }

    public int getRoundMinutesTo() {
        return roundMinutesTo;
    }

    public boolean getAutoPause() {
        return autoPause;
    }

    public boolean afkWarning() {
        return afkWarning;
    }

    public void setAfkWarning(boolean afkWarning) {
        this.afkWarning = afkWarning;
    }

    public void setAutoPause(boolean autoPause) {
        this.autoPause = autoPause;
    }

    public void setRoundMinutesTo(int roundMinutesTo) {
        this.roundMinutesTo = roundMinutesTo;
    }

    public int getDropboxSize() {
        return dropboxSize;
    }

    public void setDropboxSize(int dropboxSize) {
        this.dropboxSize = dropboxSize;
    }
}
