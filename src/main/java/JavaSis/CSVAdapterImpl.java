package JavaSis;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVAdapterImpl<T extends Writeable> extends CSVAdapter<T> {

    public CSVAdapterImpl(Class<T> authorClass, FileReader fileReader, FileWriter fileWriter) throws IllegalAccessException, InstantiationException {
        super.list = new ArrayList<>();
        super.writer = fileWriter;
        super.stringSeparator = System.getProperty("line.separator");
        super.t = authorClass.newInstance();

        try (Scanner scan = new Scanner(fileReader)) {
            scan.useDelimiter(stringSeparator);
            while (scan.hasNext()) {
                list.add((T) t.parseLine(scan.nextLine()));
            }
        }
    }
}
