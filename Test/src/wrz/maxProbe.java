package wrz;

/**
 * Accepting the probe number and change it in the GUI.
 * 
 * @author Ruizheng Wu
 * @version 1.0
 *
 */
public class maxProbe {
	/**
	 * This method can create one line of the histogram through
	 * judge the number and change the JLable's text.
	 * 
	 * @param max  The number of probes in the suitable column of hitogtam/
	 * @param l		   The JLable that show the histogram's value.	 
	 */
	public String[] addProbe(int max){
		String newProbes[]=new String[max];
		for(int i=0;i<max;i++){
			newProbes[i]=String.valueOf(i+1);
		}
		return newProbes;
	}
	//!!!!!!!!!
	public String[] changeProbe(String[]max){
		if(max.length>1){
			System.out.println("Error! Please input just 1 paramater!");
			System.exit(1);
		}
		if(max[0].length()!=2){
			System.out.println("Error! Please input paramater between 11 to 20!");
			System.exit(1);
		}
		
		if(!Character.isDigit(max[0].charAt(0)) || !Character.isDigit(max[0].charAt(1))){
			System.out.println("Error! Please input paramater between 11 to 20!");
			System.exit(1);
		}
		
		if(Integer.parseInt(max[0])<11 || Integer.parseInt(max[0])>20){
			System.out.println("Error! Please input paramater between 11 to 20!");
			System.exit(1);
		}
		
		return addProbe(Integer.parseInt(max[0]));
	}
}
