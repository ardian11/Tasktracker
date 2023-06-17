package db.DataType;

import java.io.Serializable;

public class Preferences implements Serializable {
    private int roundMinutesTo;
    private boolean autoPause;
    private boolean afkWarning;

    private int dropboxSize;

    public void Preferences(int roundMinutesTo, boolean autoPause, boolean afkWarning){
        this.roundMinutesTo = roundMinutesTo;
        this.autoPause = autoPause;
        this.afkWarning = afkWarning;
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
