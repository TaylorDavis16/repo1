package Endterm.Question1_b;

public class Book {
    private String title;
    private boolean hasElectronic;

    public Book(String title, boolean hasElectronic) {
        this.title = title;
        this.hasElectronic = hasElectronic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasElectronic() {
        return hasElectronic;
    }

    public void setHasElectronic(boolean hasElectronic) {
        this.hasElectronic = hasElectronic;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", hasElectronic=" + hasElectronic +
                '}';
    }
    
    private void aVoid(){
        
    }
}
