package qhj;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;


/**
 * This program is used to collect RTTs of input URL
 *
 */
public class NetAnalyser extends JFrame implements ActionListener{
    private JComboBox<Integer> comboBox;
    private JTextArea textArea;
    private JLabel slabel1;
    private JLabel numLabel1;
    private JLabel slabel2;
    private JLabel numLabel2;
    private JLabel slabel3;
    private JLabel numLabel3;
    private JTextField textField;
    /**
     * This method initialize an instance of NetAnalyser
     * @param args unused in this task
     */
    public static void main(String[] args){
        NetAnalyser frame = new NetAnalyser();
        frame.setSize(1200,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This constructor activate the main frame and also set the items of comboBox.
     */
    private NetAnalyser(){
        mainFrame();
        for(int i=0;i<10;i++) comboBox.addItem(i+1);
    }

    /**
     * This method reset all text of histogram and the textArea
     * before next input.
     *
     */
    private void setDefault(){
        textArea.setText("");
        slabel1.setText("");
        slabel2.setText("");
        slabel3.setText("");
        numLabel1.setText("");
        numLabel2.setText("");
        numLabel3.setText("");
    }
    /**
     * This method receive the  action and combine the URL
     * in the text field and the number of probes and then display
     * them in the textArea and histogram
     * @param event the "click" action received from the button
     */
    public void actionPerformed(ActionEvent event) {
        setDefault();
        String url = textField.getText();
        int num = (int) (comboBox.getSelectedItem());

        Process p;
        String pattern = "time=(\\d+)ms";
        Pattern r=Pattern.compile(pattern);
        ArrayList<Integer>  RTTs = new ArrayList<>();
        int counts=0; /*record the number of RTT*/
        try{
            p = Runtime.getRuntime().exec(String.format("cmd /c chcp 437 && cmd /c ping -n %d  %s", num,url));
            p.waitFor();
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
            reader.readLine(); reader.readLine();
            String line;
            while((line=reader.readLine())!=null){
                Matcher m = r.matcher(line);
                textArea.append(line+"\r\n");
                if(m.find()){
                    RTTs.add(Integer.parseInt(m.group(1)));
                    counts++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        if(RTTs.isEmpty()) return;  // if the list is empty, return to the main frame.


        int min=RTTs.get(0),max=RTTs.get(0);
        for(int i:RTTs){
            if(min>i) min=i;
            if(max<i) max=i;
        }
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        StringBuilder s3 = new StringBuilder();

        /* When min equals max, histogram only display one row.*/
        if(min==max){
            slabel2.setText(String.format("RTTs=%d: ", min));
            StringBuilder s = new StringBuilder();
            for(int i=0;i<counts;i++){
                s.append("X   ");
            }
            numLabel2.setText(s.toString());
        }

        /* There are two cases: the difference of max and min is divisible by 3 or not */
        else {
            if((max-min)%3==0){
                int binSize = (max-min)/3;
                slabel1.setText(String.format("%d<=RTT<%d: ",min, min+binSize));
                slabel2.setText(String.format("%d<=RTT<%d: ",min+binSize, min+2*binSize));
                slabel3.setText(String.format("%d<=RTT<=%d: ",min+2*binSize, max));

                for(int time: RTTs){
                    if(time<min+binSize) s1.append("X   ");
                    else if(time<min+2*binSize) s2.append("X   ");
                    else if(time<=max) s3.append("X   ");
                }

            }else{
                float binSize = (float) (max-min)/3.f;
                slabel1.setText(String.format("%.2f<=RTT<%.2f: ",(float)min, min+binSize));
                slabel2.setText(String.format("%.2f<=RTT<%.2f: ",min+binSize, min+2*binSize));
                slabel3.setText(String.format("%.2f<=RTT<=%.2f: ",min+2*binSize, (float)max));

                for(int time: RTTs){
                    if(time<min+binSize) s1.append("X   ");
                    else if(time<min+2*binSize) s2.append("X   ");
                    else if(time<=max) s3.append("X   ");
                }
            }
            numLabel1.setText(s1.toString());
            numLabel2.setText(s2.toString());
            numLabel3.setText(s3.toString());
        }


    }
    private void functionPart(Container contentPane){
         comboBox = new JComboBox<>();

        JLabel tip = new JLabel("Enter Test URL & no. of probes and click on Process");
        tip.setBounds(20,30,400,30);
        contentPane.add(tip);

        JLabel tip2 = new JLabel("Test URL");
        tip2.setBounds(50,100,70,20);
        contentPane.add(tip2);
        textField = new JTextField();
        textField.setBounds(120,100,180,20);
        contentPane.add(textField);

        JLabel tip3 = new JLabel("No.of probes"); // Initialize the text label
        tip3.setBounds(80, 150, 90, 20);
        contentPane.add(tip3);
        comboBox.setBounds(180,150,50,20);
        contentPane.add(comboBox);

        /*Add action listener to the button*/
        JButton button = new JButton();
        button.setText("Process");
        button.setBounds(120,200,80,30);
        contentPane.add(button);
        button.addActionListener(this);


    }

    /**
     * This method builds up the display interface.
     * @param contentPane the container to add components
     */
    private void displayPart(Container contentPane){

         textArea = new JTextArea();
         textArea.setText("");
        JScrollPane scrollPane1 = new JScrollPane(textArea);
        scrollPane1.setBounds(400,0,400,400);
         contentPane.add(scrollPane1);
    }

    private void histogramPart(Container contentPane){

        JLabel histogramLabel = new JLabel("Histogram");
        histogramLabel.setBounds(820,70,60,20);
        contentPane.add(histogramLabel);

        slabel1 = new JLabel();
        numLabel1 = new JLabel();
        slabel2 = new JLabel();
        numLabel2 = new JLabel();
        slabel3 = new JLabel();
        numLabel3 = new JLabel();

        slabel1.setBounds(820,150,120,20);
        slabel1.setText("");
        contentPane.add(slabel1);
        numLabel1.setBounds(940,150,200,20);
        numLabel1.setText("");
        contentPane.add(numLabel1);

        slabel2.setBounds(820,190,120,20);
        slabel2.setText("");
        contentPane.add(slabel2);
        numLabel2.setBounds(940,190,200,20);
        numLabel2.setText("");
        contentPane.add(numLabel2);

        slabel3.setBounds(820,230,120,20);
        slabel3.setText("");
        contentPane.add(slabel3);
        numLabel3.setBounds(940,230,200,20);
        numLabel3.setText("");
        contentPane.add(numLabel3);


    }
    /**
     * This method initialize the whole frame of the program.
     * */
    private void mainFrame(){

        setTitle("NetAnalyser V1.0");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setSize(1200,400);


        functionPart(contentPane);
        displayPart(contentPane);
        histogramPart(contentPane);


        pack();
        setLocationRelativeTo(getOwner());

    }
}
