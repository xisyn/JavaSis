package JavaSis;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestUnit {
    @Test
    public void test() {

        try (FileReader fileReader = new FileReader("authors.csv");
             FileWriter fileWriter = new FileWriter("authors.csv", true)) {

            CSVAdapter<Author> authorCsvAdapter =
                    new CSVAdapterImpl<Author>(Author.class, fileReader, fileWriter);

            Author author = authorCsvAdapter.read(0);
            assertEquals("Лев Николаевич Толстой", author.getName());
            assertEquals("Ясная Поляна", author.getBirthPlace());

            Author authorNew = new Author();
            authorNew.setName("Некоторый Автор");
            authorNew.setBirthPlace("Некоторый Город");

            int rowIndex = authorCsvAdapter.append(authorNew);

            Author authorNewOpened = authorCsvAdapter.read(rowIndex);

            assertEquals("Некоторый Автор", authorNewOpened.getName());
            assertEquals("Некоторый Город", authorNewOpened.getBirthPlace());

        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
