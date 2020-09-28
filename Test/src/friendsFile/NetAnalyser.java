package friendsFile;

/**
 * @author Yulei Wang
 * @studentnumber 2018212980
 * 
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class NetAnalyser {
	public static void main(String[] args) {
		if (args.length==0){
			new MyFrame("Net Analyser V1.0");

		}
		else {
			if (args.length==1){
				int number = Integer.parseInt(args[0]);
				if (number<10 || number>20){
					System.out.println("Number should between 10 and 20!");
				}
				else {
					new MyFrame("Net Analyser V1.0", number);
				}
			}
			else System.out.println("Cannot pass more than one parameter!");
		}
	}
}

class MyFrame extends Frame implements ActionListener{
	Frame myframe;
	JTextPane mypanel;
	TextField textfield;
	JComboBox<Integer> probesnum;
	Button mybutton;
	public MyFrame(String name) {
		super(name);
		launch(10);
	}

	public MyFrame(String name,int number) {
		super(name);
		launch(number);
	}


	private void launch(int number){
		/*
		 *  Question1_a, create Question1_b window, adjust its size, color etc.
		 *  Second, add panels to the window, adjust panels' color, size as well.
		 *  Third, put buttons on, set listener to the button and get the url, probes number.
		 */
		myframe= new Frame();
//		Set layout of the window
		myframe.setLayout(null);
//		decide the size of the window
		myframe.setSize(1000, 450);
//		set background color of the window
		myframe.setBackground(new Color(216, 218, 218));
//		set the initial position that pops up
		myframe.setLocation(200, 150);
//		set fixed size
		myframe.setResizable(false);

//		Add labels
		Label mylabel1 = new Label();
		Label mylabel2 = new Label();
		Label mylabel3 = new Label();
		mylabel1.setText("Enter Test URL & no. of probes and click on Process");
		mylabel1.setBounds(7, 20, 300, 50);
		mylabel2.setText("Test URL");
		mylabel2.setBounds(20, 76, 50, 50);
		mylabel3.setText("No. of probes");
		mylabel3.setBounds(50, 195, 80, 20);
		myframe.add(mylabel1);
		myframe.add(mylabel2);
		myframe.add(mylabel3);

//		Add panels to the window
		mypanel = new JTextPane();
//		Panel set coordinates relative to frame
		mypanel.setBounds(310, 40, 350, 400);
//		set background color of the panel
		mypanel.setBackground(new Color(255, 255, 255));
		myframe.add(mypanel);

		/*
		 *  add listener to exit the window
		 *  what to do when the window is closed
		 */
		myframe.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

//		Add text to the window
		textfield = new TextField();
		myframe.add(textfield);
		textfield.setBounds(80, 90, 150, 20);
		textfield.setBackground(new Color(255, 255, 255));

//		change the combo box to Question1_b text field
		Integer[] integers=new Integer[number];
		for (int i = 0; i < number; i++) {
			integers[i]=i+1;
		}
		probesnum = new JComboBox<>(integers);
		myframe.add(probesnum);
		probesnum.setBounds(140, 195, 50, 20);


//		put the process button on
		mybutton = new Button();
		myframe.add(mybutton);
		mybutton.setLabel("Process");
		mybutton.setBounds(80, 300, 100, 30);
		mybutton.setBackground(new Color(240, 248, 255));
//		make everything visible
		myframe.setVisible(true);

		/*
		 * Add function to the button
		 * listen to the action of button.
		 * then get the url and probes number.
		 * Monitor the text entered in the text box
		 * check it is non-empty and contains at least one dot
		 * gracefully terminate the program if the url is invalid
		 */
		mybutton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mybutton.setBackground(new Color(198, 198, 198));
//				get user's input
		String ip = textfield.getText();
		/*
		 * get the number of probes that user input if the input is not between 10
		 * and 20 the system end at 2 seconds
		 */
		int num = (Integer) probesnum.getSelectedItem();
//				reset the text field, button's color, combo box and the histogram.
		clearHistogram();
//				call for the ping function
		try {
			if( ip.contains("."))
				ping(ip, num);
			else
				mypanel.setText("invaild url");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * ping() input the url and probes number.
	 * show the output on the textpane.
	 * @param event the "click" action received from the button
	 */

	private String ping(String ip, int num) {
		String line = null;
		String output = "";
		int count = 0, k = 0;
		int[] RTT = new int[num];

		try {
			Process p = Runtime.getRuntime().exec(String.format("cmd /c ping -n %d %s", num, ip));
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//					Read line by line and stitch the contents of each line together
			while ((line = reader.readLine()) != null) {
				if (0 < count && count <= num + 1) {
//		                   Since there may be Question1_b language difference between platforms,
//		                	use the unit millisecond as string index
					int ms = line.indexOf("ms");
//		                    time is the previous value of "ms"
					int index = ms - 1;
					int temp = index;
//		                    if start>0,continue
					if (index > 0) {
						char timenum = line.charAt(index);
//		                        time is Question1_b digit,
//		                        keep scanning if the previous one is digit and get the full time
						while (Character.isDigit(timenum) && index > 0) {
							index--;
							timenum = line.charAt(index);
						}
						if (index != temp) {
							String timeString = line.substring(index + 1, ms);
							int time = Integer.parseInt(timeString);
//		                            Fill numbers into the array bit by bit
							RTT[k++] = time;
						}
					}
				}
				count++;
				output = output + line + "\n";
			}
//					The initialized values in the RTT array are all 0
//					need to delete those 0 in the result
			if (k != 0) {
				int m = 0;
				int[] temp = new int[k];
				for (int n = 0; n < RTT.length; n++) {
					if (RTT[n] != 0)
						temp[m++] = RTT[n];
				}
				RTT = temp;
			} else {
				RTT = null;
				return output;
			}
			mypanel.setText(output);
			Histogram(count,RTT,ip);
		} catch (Exception ex) {
			ex.printStackTrace();
			mypanel.setText(ex.getMessage());
		}
		return "";
	}

	//			add labels of the histogram
	Label label1 = new Label();
	Label xlabel1 = new Label();
	Label label2 = new Label();
	Label xlabel2 = new Label();
	Label label3 = new Label();
	Label xlabel3 = new Label();

	/*
	 *  histogram() get RTT and probes num from ping()
	 *  generate Question1_b histogram and add these labels to the frame.
	 */
	private void Histogram(int count, int[] RTTvalue, String url) throws IOException {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		String filename = createFile(url);
		ArrayList<Integer> frequency = new ArrayList<>();
		int f1=0, f2=0, f3=0;

		if (RTTvalue != null) {
			Arrays.sort(RTTvalue);
//					 find the min and max RTT value
			int minRTT = RTTvalue[0];
			int maxRTT = RTTvalue[RTTvalue.length - 1];

			for (int i : RTTvalue) {
				if (minRTT > i)
					minRTT = i;
				if (maxRTT < i)
					maxRTT = i;
			}

//                 When min = max, histogram only have one row
			if (minRTT == maxRTT) {
				label2.setText(String.format("RTT=%d: ", minRTT));
				StringBuilder sb = new StringBuilder();
				for (int p = 0; p < count; p++) {
					sb.append("*   ");
				}
				xlabel2.setText(sb.toString());
				writeFile( maxRTT,filename,count);
			}

//					  whether (max-min) can be divided by 3 or not
			else {
				if ((maxRTT - minRTT) % 3 == 0) {
					int binSize = (maxRTT - minRTT) / 3;
					label1.setText(String.format("%d<=RTT<%d: ", minRTT, minRTT + binSize));
					label2.setText(String.format("%d<=RTT<%d: ", minRTT + binSize, minRTT + 2 * binSize));
					label3.setText(String.format("%d<=RTT<=%d: ", minRTT + 2 * binSize, maxRTT));

					for (int time : RTTvalue) {
						if (time < minRTT + binSize)
						{sb1.append("* ");
							f1++;
						}

						else if (time < minRTT + 2 * binSize) {
							sb2.append("* ");
							f2++;}
						else if (time <= maxRTT) {
							sb3.append("* ");
							f3++;}
					}

				} else {
					float binSize = (float) (maxRTT - minRTT) / 3.f;
					label1.setText(String.format("%.2f<=RTT<%.2f: ", (float) minRTT, minRTT + binSize));
					label2.setText(String.format("%.2f<=RTT<%.2f: ", minRTT + binSize, minRTT + 2 * binSize));
					label3.setText(String.format("%.2f<=RTT<=%.2f: ", minRTT + 2 * binSize, (float) maxRTT));

					for (int time : RTTvalue) {
						if (time < minRTT + binSize) {
							sb1.append("* ");
							f1++;}
						else if (time < minRTT + 2 * binSize) {
							sb2.append("* ");
							f2++;}
						else if (time <= maxRTT) {
							sb3.append("* ");
							f3++;}
					}
					frequency.add(f1);
					frequency.add(f2);
					frequency.add(f3);
					writeFile(binSize,minRTT,maxRTT,filename,frequency);
				}

//						convert stringbuilder to string
				xlabel1.setText(sb1.toString());
				xlabel2.setText(sb2.toString());
				xlabel3.setText(sb3.toString());
			}
		}

//				add labels to show the histogram
		Label hglabel = new Label();
		hglabel.setText("Histogram");
		myframe.add(hglabel);
		hglabel.setBounds(660, 80, 100, 20);

		myframe.add(label1);
		label1.setBounds(660, 200, 150, 20);
		myframe.add(xlabel1);
		xlabel1.setBounds(810, 200, 100, 20);
		xlabel1.setForeground(new Color(65, 105, 225));

		myframe.add(label2);
		label2.setBounds(660, 300, 150, 20);
		myframe.add(xlabel2);
		xlabel2.setBounds(810, 300, 100, 20);
		xlabel2.setForeground(new Color(65, 105, 225));

		myframe.add(label3);
		label3.setBounds(660, 400, 150, 20);
		myframe.add(xlabel3);
		xlabel3.setBounds(810, 400, 100, 20);
		xlabel3.setForeground(new Color(65, 105, 225));

		Font font = new Font("SansSerif", Font.PLAIN, 25);
		xlabel1.setFont(font);
		xlabel2.setFont(font);
		xlabel3.setFont(font);
		hglabel.setFont(new Font("SansSerif", Font.PLAIN, 15));

	}

	/*
	 * Clear the histogram the second time you press the process butto.
	 * Enter the next program loop
	 */
	private void clearHistogram() {
		label1.setText(null);
		xlabel1.setText(null);
		label2.setText(null);
		xlabel2.setText(null);
		label3.setText(null);
		xlabel3.setText(null);
	}



/*	 *
     * Save data in file
     * write URL and current time into the file and make up the file name.
     * @param url used to write file and filename
     * @throws IOException If file are created failed.
     */
    public String createFile (String url) throws IOException {
       LocalDateTime timenow =LocalDateTime.now();
       DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
       String timeString = formatter.format(timenow);
        File createfile=new File("file//");
        if (!createfile.exists())
            createfile.mkdir();
        String filename="file//"+url+timeString+".txt";
        File file=new File(filename);
        file.createNewFile();
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(file));
            writer.write(url.replace(".", "-") + "-" + timeString + ".txt"+"\n\n"+"RTT(ms)  histogram\r\n");
            writer.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
		return filename;
    }
    
	/*
	 * when (min=max) write RTT to the file.
	 * 
	 * @param min is the min value of RTT
	 * 
	 * @param max is the max value of RTT
	 * 
	 * @param count is the nmber of RTT
	 */
    public void writeFile(int RTT, String filename,int count) 
    		throws IOException{
//    	Continue writing after the content already in the file
        FileWriter inFile= new FileWriter(filename,true);
        try {
             inFile.write(String.format("%d: %d\r\n",RTT, count));
             inFile.close();
        }catch (IOException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }

    
	/*
	 * when (min-max) can be divided by 3 write RTT to the file.
	 * 
	 * @param min is the min value of RTT
	 * 
	 * @param max is the max value of RTT
	 * 
	 * @param frequency count times of each RTT
	 */
    public void writeFile(int binSize, int min, int max,String filename, ArrayList<Integer> frequency) 
    		throws IOException{
//    	Continue writing after the content already in the file
        FileWriter inFile= new FileWriter(filename,true);
        try {
            for(int i=0;i<3;i++){
                if(i==2) inFile.write(String.format("%d-%d: %d",min+i*binSize, max, frequency.get(i)));
                else inFile.write(String.format("%d-%d: %d\r\n",min+i*binSize,min+(i+1)*binSize,frequency.get(i)));
            }
            inFile.close();
        }catch (IOException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }


	/*
	 * when (min-max) can NOT be divided by 3 write RTT to the file.
	 * 
	 * @param min is the min value of RTT
	 * 
	 * @param max is the max value of RTT
	 * 
	 * @param frequency count times of each RTT
	 */
    public void writeFile(float binSize, int min, int max,String filename, ArrayList<Integer> frequency) 
    		throws IOException{
        FileWriter inFile= new FileWriter(filename,true);
    	try {
            for(int i=0;i<3;i++){
                if(i==2) inFile.write(String.format("%.2f-%d: %d",min+i*binSize, max, frequency.get(i)));
                else inFile.write(String.format("%.2f-%.2f: %d\r\n",min+i*binSize,min+(i+1)*binSize,frequency.get(i)));
            }
            inFile.close();
        }catch (IOException ex){
            ex.printStackTrace();
            System.exit(0);
        }

    }


}
