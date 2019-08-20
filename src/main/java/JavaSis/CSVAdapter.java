package JavaSis;

import java.io.FileWriter;
import java.util.List;

public abstract class CSVAdapter<T extends Writeable> {
    protected List<T> list;
    protected T t;
    protected FileWriter writer;
    protected String stringSeparator;


    public T read(int i) {
        t = list.get(i);
        return t;
    }

    public int append(T newT) {
        list.add(newT);
        int addedRowIndex = list.size() - 1;
        t.write(writer, stringSeparator, addedRowIndex, list);
        return addedRowIndex;
    }

    /*public abstract void write(FileWriter writer, String stringSeparator, int index);

    public abstract T parseLine(String line);*/

}
