package db;

public class SavedData<T> {

    private T object;

    public SavedData(T object){
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
