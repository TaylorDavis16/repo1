package wrz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
/**
 *  The class is to create the file that include the
 *  information of the URL address and it's speed historgram.
 * 
 *  @author  Ruizheng WU
 *	@version 1.0
 */
public class urlFile {
	/**
	 * The method can get the current time, change the data of hitogram
	 * into words and record them in Question1_b file by Java IO.
	 * 
	 * @param x 		The number of probes in the first section of histogram.
	 * @param y 		The number of probes in the second section of histogram.
	 * @param z 		The number of probes in the third section of histogram.
	 * @param min 		The minimal value of all the probes,used to calculate the seciton of histogram.
	 * @param max 		The maximal value of all the probes,used to calculate the seciton of histogram.
	 * @param size 		The binSize of the histogram.
	 * @param address 	The URL address that the user ch
	 */
	public void createUrlFile(int x, int y, int z, double min, double max, double size, JTextField address) {
		String url = address.getText();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date d = new Date();
		String processTime = sdf.format(d);
		
		String line1 = new String(min + "-" + (min + size) + " : " + x);
		String line2 = new String((min + size) + "-" + (max - size) + " : " + y);
		String line3 = new String((max - size) + "-" + max + " : " + z);
		
		File f = new File("d:/ppt/" + url + processTime + ".txt");

		try (FileWriter fw = new FileWriter(f); PrintWriter pw = new PrintWriter(fw);) {
			pw.println(url+"-"+processTime+".txt");
			pw.println();
			pw.println("RRT(ms) histogram");
			pw.println(line1);
			pw.println(line2);
			pw.println(line3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
