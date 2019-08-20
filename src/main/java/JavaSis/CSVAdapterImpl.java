package JavaSis;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVAdapterImpl<T> extends CSVAdapter<Author> {

    public CSVAdapterImpl(Class<T> authorClass, FileReader fileReader, FileWriter fileWriter) {
        super.list = new ArrayList<>();
        super.writer = fileWriter;
        super.stringSeparator = System.getProperty("line.separator");

        try (Scanner scan = new Scanner(fileReader)) {
            scan.useDelimiter(stringSeparator);
            while (scan.hasNext()) {
                //list.add(parseLine(scan.nextLine()));
                list.add(t.parseLine(scan.nextLine()));
            }
        }
    }
}
