package wrz;

import javax.swing.JLabel;
/**
 * This is the class to create the histogram.
 * 
 * @author 	Ruizheng Wu
 * @version 2.0
 */
public class histogram {
	/**
	 * This method can create one line of the histogram through
	 * judge the number and change the JLable's text.
	 * 
	 * @param nodeNum  The number of probes in the suitable column of hitogtam/
	 * @param l		   The JLable that show the histogram's value.	 
	 */
	public void createHistogram(int nodeNum, JLabel l) {
		switch (nodeNum) {
		case 0:
			l.setText("");
			break;
		case 1:
			l.setText("X");
			break;
		case 2:
			l.setText("X X");
			break;
		case 3:
			l.setText("X X X");
			break;
		case 4:
			l.setText("X X X X");
			break;
		case 5:
			l.setText("X X X X X");
			break;
		case 6:
			l.setText("X X X X X X");
			break;
		case 7:
			l.setText("X X X X X X X");
			break;
		case 8:
			l.setText("X X X X X X X X");
			break;
		case 9:
			l.setText("X X X X X X X X X");
			break;
		case 10:
			l.setText("X X X X X X X X X X");
			break;
		case 11:
			l.setText("X X X X X X X X X X X");
			break;
		case 12:
			l.setText("X X X X X X X X X X X X");
			break;
		case 13:
			l.setText("X X X X X X X X X X X X X");
			break;
		case 14:
			l.setText("X X X X X X X X X X X X X X");
			break;
		case 15:
			l.setText("X X X X X X X X X X X X X X X");
			break;
		case 16:
			l.setText("X X X X X X X X X X X X X X X X");
			break;
		case 17:
			l.setText("X X X X X X X X X X X X X X X X X");
			break;
		case 18:
			l.setText("X X X X X X X X X X X X X X X X X X");
			break;
		case 19:
			l.setText("X X X X X X X X X X X X X X X X X X X");
			break;
		case 20:
			l.setText("X X X X X X X X X X X X X X X X X X X X");
			break;
		}
	}
}
