package wrz;

import javax.swing.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Title:		NetAnalyser.java
 * Copyright: 	Copyright (c) 2001
 * @author 		Ruizheng Wu
 * @version 	1.0 
 * 
 */
public class NetAnalyser implements ActionListener{
	public void fuck(String newProbe[]){
		// ��������
		DecimalFormat df = new DecimalFormat("#0.0");
		// ����
		gui ui=new gui();
		ui.serGui(newProbe);
		maxProbe mp=new maxProbe();
		ui.probeChoice = new JComboBox(mp.changeProbe(newProbe));
		ui.f.add(ui.probeChoice);
		
		ui.process.addActionListener(this);
		ui.process.addActionListener(new ActionListener() {

			@Override
			// ����Ҫ��Test url��һ���ǿ��ж�~
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String emptyCheck = ui.urlAddress.getText();
				for (int i = 0; i < ui.rrt.length; i++) {
					ui.rrt[i] = "";
				}
				if (emptyCheck.length() == 0) {
					JOptionPane.showMessageDialog(ui.f, "ERROR! Please input the Test URL!");
				} else {
					// !@@@@@@@@@@@!!!!
					ui.ping.setText("");
					Process p = null;
					try {
						int x = Integer.parseInt(ui.probeChoice.getSelectedItem().toString());
						String address = ui.urlAddress.getText();
						p= Runtime.getRuntime().exec("cmd chcp 437");
						p = Runtime.getRuntime().exec("cmd /c ping -n " + x + " " + address);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						p.waitFor();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
					} catch (UnsupportedEncodingException ex) {
						ex.printStackTrace();
					}
					try {
						int rrtCount = 0;
						while (true) {
							String line = reader.readLine();
							if (null == line)
								break;
							ui.ping.append(line + "\n");
							System.out.println(line);

							// ��λ��������ȡ���ַ���
							int start = line.indexOf("时间");
							int end = line.indexOf("ms");

							// ���-1�������ж϶�������һ�У��ǲ���������Ҫ�Ĵ���ʱ��ms����
							if (start != -1 && end != -1) {
								ui.rrt[rrtCount] = line.substring(start + 3, end);
								System.out.println("RRT:" + ui.rrt[rrtCount]);
								rrtCount++;
							}

						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// Build the histogram.
				int rrtValue[] = new int[ui.rrt.length];
				for (int i = 0; i < ui.rrt.length; i++) {
					// System.out.println(rrt[i]);
					try {
						rrtValue[i] = Integer.parseInt(ui.rrt[i]);
						System.out.print(rrtValue[i]);
					} catch (Exception e3) {
						break;
					}
				}
				double max = rrtValue[0];
				double min = 100;
				for (int i = 0; i < rrtValue.length; i++) {
					if (max < rrtValue[i])
						max = rrtValue[i];
					if (min > rrtValue[i] && rrtValue[i] > 0)
						min = rrtValue[i];
				}
				System.out.println("max:" + max + "min:" + min);
				double binSize = Double.parseDouble(df.format((max - min) / 3));
				df.format(binSize);
				int count1 = 0, count2 = 0, count3 = 0;
				System.out.println("��" + binSize);
				for (int i = 0; i < rrtValue.length; i++) {
					if (rrtValue[i] >= min && rrtValue[i] < (min + binSize))
						count1++;
					else if (rrtValue[i] <= max && rrtValue[i] >= (max - binSize))
						count3++;
					else if (rrtValue[i] >= (min + binSize) && rrtValue[i] < (max - binSize))
						count2++;
				}
				System.out.println(count1 + "," + count2 + "," + count3);
				ui.lineOne.setText(min + "<=RRT<" + (min + binSize));
				ui.lineTwo.setText((min + binSize) + "<=RRT<" + (max - binSize));
				ui.lineThree.setText((max - binSize) + "<=RRT<" + max);
				histogram h=new histogram();
				h.createHistogram(count1, ui.lineOneValue);
				h.createHistogram(count2, ui.lineTwoValue);
				h.createHistogram(count3, ui.lineThreeValue);
				
//				urlFile saveFile=new urlFile();
//				saveFile.createUrlFile(count1, count2, count3, min, max, binSize, ui.urlAddress);
			}

		});
	}
	public void actionPerformed(ActionEvent event){
		gui ui=new gui();
		ui.process.setText("sb");
	}
	/**
	 * This is the main method of NetAnalyser.It quotes
	 * other classes,and include the listener of JButton,
	 * which can output the information of ping and the histogram.
	 * 
	 * @param args 	The number the number of probes in Task2
	 * 	 	
	 */
	public static void main(String[] args) {
		NetAnalyser na=new NetAnalyser();
		
		na.fuck(args);
		
		

	}
}
