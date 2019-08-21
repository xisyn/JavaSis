package JavaSis;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVAdapterImpl<T extends Writeable> extends CSVAdapter<T> {

    public CSVAdapterImpl(Class<T> authorClass, FileReader fileReader, FileWriter fileWriter) {
        super.list = new ArrayList<>();
        super.writer = fileWriter;
        super.stringSeparator = System.getProperty("line.separator");
        try {
            super.t = authorClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try (Scanner scan = new Scanner(fileReader)) {
            scan.useDelimiter(stringSeparator);
            while (scan.hasNext()) {
                list.add((T) t.parseLine(scan.nextLine()));
            }
        }
    }
}
