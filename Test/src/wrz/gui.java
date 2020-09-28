package wrz;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * This class can set the GUI interface, and use
 * 
 * @author Ruizheng Wu
 *
 */
public class gui {
		JFrame f = new JFrame("NetAnalyser V1.0");
		// ���������������������
		JPanel pLeft = new JPanel();
		JPanel pRight = new JPanel();
		JLabel tableHead = new JLabel("Histogram");
		JLabel lineOne = new JLabel();
		JLabel lineTwo = new JLabel();
		JLabel lineThree = new JLabel();
		JLabel lineOneValue = new JLabel();
		JLabel lineTwoValue = new JLabel();
		JLabel lineThreeValue = new JLabel();
		JLabel enterHint = new JLabel("Enter Test URL & no. of probes and click on Process");
		JLabel url = new JLabel("Test URL");
		JLabel probeNo = new JLabel("No. of probes");

		JTextField urlAddress = new JTextField();	

		String probes[] = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		maxProbe mp=new maxProbe();
		JComboBox probeChoice;

		// ��ʾ������Ϣ���ı���
		JTextArea ping = new JTextArea();

		// ������ť
		JButton process = new JButton("Process");
		
		String rrt[] = new String[10];
		
		public void serGui(String newProbe[]){
			f.setSize(1200, 350);
			f.setLayout(null);
			
			pLeft.setLayout(null);
			pLeft.setBounds(0, 0, 300, 300);
			
			pRight.setLayout(null);
			pRight.setBounds(780, 0, 400, 400);
			//pRight.setBackground(Color.pink);
			
			tableHead.setBounds(0, 25, 100, 20);
			
			lineOne.setBounds(0, 70, 100, 20);
			
			lineTwo.setBounds(0, 100, 100, 20);
			
			lineThree.setBounds(0, 130, 100, 20);
			
			lineOneValue.setBounds(110, 70, 200, 20);
			
			lineTwoValue.setBounds(110, 100, 200, 20);
			
			lineThreeValue.setBounds(110, 130, 200, 20);
			
			enterHint.setBounds(5, 25, 300, 10);
			
			url.setBounds(30, 65, 80, 10);
			
			probeNo.setBounds(90, 100, 100, 50);
			
			urlAddress.setBounds(85, 60, 200, 20);
			
			//System.out.println("ɵ�ư�"+newProbe);
			probeChoice= new JComboBox(mp.changeProbe(newProbe));
			probeChoice.setBounds(170, 115, 40, 20);
			
			ping.setText("Your output will appear hear.");
			ping.setLineWrap(true);
			ping.setBounds(350, 0, 400, 400);
			
			process.setBounds(115, 170, 85, 30);
			
			pLeft.add(enterHint);
			pLeft.add(url);
			pLeft.add(probeNo);
			pLeft.add(urlAddress);
			pLeft.add(probeChoice);
			pLeft.add(process);
			pRight.add(tableHead);
			pRight.add(lineOne);
			pRight.add(lineTwo);
			pRight.add(lineThree);
			pRight.add(lineOneValue);
			pRight.add(lineTwoValue);
			pRight.add(lineThreeValue);

			f.add(pLeft, BorderLayout.WEST);
			f.add(ping, BorderLayout.CENTER);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.add(pRight);
		}
		
		
		
		

		
		

		
		


		


}
