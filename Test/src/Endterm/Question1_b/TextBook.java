package Endterm.Question1_b;

public class TextBook extends Book{
    private String subject;

    public TextBook(String title, boolean hasElectronic, String subject) {
        super(title, hasElectronic);
        this.subject=subject;
    }

    @Override
    public String toString() {
        return "TextBook{" +
                "title='" + super.getTitle() + '\'' +
                ", hasElectronic=" + super.isHasElectronic() +
                "subject='" + subject + '\'' +
                '}';
    }
}
