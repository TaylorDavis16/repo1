package Endterm.Question1_b;

public class Manual extends Book{

    public Manual(String title, boolean hasElectronic) {
        super(title, hasElectronic);
    }

    @Override
    public String toString() {
        return "Manual{"+
                "title='" + super.getTitle() + '\'' +
                ", hasElectronic=" + super.isHasElectronic() +
                "}";
    }
    
    public void info(){
        System.out.println(super.toString());
    }
}
