package db;

import org.joda.time.DateTime;

public class Task {

    private String name;
    private DateTime lastUsed;

    //This will decide if and where it will appear in the dropbox when choosing tasks.
    private int usedCount;

    //Tasks are considered marked if the user saved the task himself. Marked tasks will be shown first in the dropbox.
    private boolean marked;

    public Task(String name, DateTime lastUsed, int usedCount, boolean marked){
        this.name = name;
        this.lastUsed = lastUsed;
        this.usedCount = usedCount;
        this.marked = marked;
    }

    public DateTime getLastUsed() {
        return lastUsed;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public String getName() {
        return name;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
