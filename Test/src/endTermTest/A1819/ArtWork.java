package endTermTest.A1819;
public class ArtWork{
    private String name;
    private Integer creation_year;

    public ArtWork(String name, Integer creation_year) {
        this.name = name;
        this.creation_year = creation_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreation_year() {
        return creation_year;
    }

    public void setCreation_year(Integer creation_year) {
        this.creation_year = creation_year;
    }
    
    public int age(){
        return 2019-this.creation_year;
    }
}