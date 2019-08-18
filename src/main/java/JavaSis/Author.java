package JavaSis;

public class Author {

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

}
