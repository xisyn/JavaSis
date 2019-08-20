package JavaSis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class Author implements Writeable {

    private String authorFullName;
    private String birthPlace;

    public Author(String authorFullName, String birthPlace) {
        this.authorFullName = authorFullName;
        this.birthPlace = birthPlace;
    }

    public Author() {

    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getName() {
        return authorFullName;
    }

    public void setName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    @Override
    public Author parseLine(String line) {
        Pattern pattern = Pattern.compile(", ");
        String[] element = pattern.split(line);
        String name = element[0];
        String place = element[1];
        return new Author(name, place);
    }

    @Override
    public void write(FileWriter writer, String stringSeparator, int index, List list) {
        Author author = (Author) list.get(index);
        String name = author.getName();
        String place = author.getBirthPlace();
        try {
            writer.write(stringSeparator + name + ", " + place);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
