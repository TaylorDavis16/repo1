package Endterm.Question1_b;

public class StoryBook extends Book{
    private boolean isTrueStory;

    public StoryBook(String title, boolean hasElectronic, boolean isTrueStory) {
        super(title, hasElectronic);
        this.isTrueStory=isTrueStory;
    }

    @Override
    public String toString() {
        return "StoryBook{" +
                "title='" + super.getTitle() + '\'' +
                ", hasElectronic=" + super.isHasElectronic() +
                "isTrueStory=" + isTrueStory +
                '}';
    }
}
