package JavaSis;

import java.io.FileWriter;
import java.util.List;

public interface Writeable<T> {
    T parseLine(String line);
    void write(FileWriter writer, String stringSeparator, int index, List<T> list);
}
