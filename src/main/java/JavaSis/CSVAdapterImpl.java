package JavaSis;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
                list.add(parseLine(scan.nextLine()));
            }
        }
    }

    private Author parseLine(String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(", ");
            String name = scanner.next();
            String place = scanner.next();
            return new Author(name, place);
        }
    }

    @Override
    public void write(FileWriter writer, String stringSeparator, int index) {
        Author author = list.get(index);
        String name = author.getName();
        String place = author.getBirthPlace();
        try {
            writer.write(stringSeparator + name + ", " + place);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
