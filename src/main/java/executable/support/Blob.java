package executable.support;

public class Blob<T> {
    private Class<T> cls;
    private byte[] data;

    public Blob(Class<T> cls, byte[] data){
        this.cls = cls;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public Class<T> getCls() {
        return cls;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
