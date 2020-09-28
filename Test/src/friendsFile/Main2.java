package friendsFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Main2 {
    public static int num;
    public static int probesCount;
    public static String url;
    public static List<Integer> RTTs=new ArrayList<>();
    public static JTextArea jta;
    public static Map<String,Integer> map=new HashMap();
    public static Map<String,String> mapData=new HashMap<>();
    public static JPanel jPanel3;
    public static JFrame jframe;
    public static String generateTime;
    public static String fileName;
    //输出文件地址
    public static String filePath="C:\\Users\\Administrator\\Desktop\\file\\";

    public static void main(String[] args) {
        JFrame frame = new JFrame("flowlayout");
        try {
            probesCount=Integer.parseInt(args[0]);
        }catch (Exception e){
            probesCount=15;
        }
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        JPanel jPanel1=new JPanel();
        JPanel jPanel2=new JPanel();
        jPanel3=new JPanel();
        jPanel1.setBackground(Color.red);
        jPanel2.setBackground(Color.orange);
        jPanel3.setBackground(Color.yellow);
        jPanel1.setLayout(null);
        jPanel2.setLayout(null);
        jPanel3.setLayout(null);

        jPanel1.setPreferredSize(new Dimension(300, 500));
        jPanel2.setPreferredSize(new Dimension(500, 500));
        jPanel3.setPreferredSize(new Dimension(400, 500));
        //---------------------------设置面板1组件
        //设置注意事项标签
        JLabel warring=new JLabel("Entry Test URL & no.of probes and click on process");
        warring.setBounds(0,0,300,30);
        //设置url标签和输入框
        JLabel urlLabel=new JLabel("Test URL");
        urlLabel.setBounds(0,50,300,30);
        JTextField urlInput=new JTextField();
        urlInput.setBounds(100,50,150,30);
        //设置下拉框和处理按钮
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if(ItemEvent.SELECTED == arg0.getStateChange()){
                    num=Integer.parseInt(arg0.getItem().toString());
                }
            }
        };
        JComboBox<String> select=new JComboBox();
        select.addItemListener(itemListener);
        select.setBounds(100,100,50,30);
       for(int i=1;i<=probesCount;i++){
           select.addItem(i+"");
       }

        JLabel indexLabel=new JLabel("No. of probes");
        indexLabel.setBounds(10,100,100,30);
        //设置处理器按钮
        JButton process=new JButton("process");
        process.setBounds(50,150,100,30);
        process.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //清空面板2和面板3
                clear();
                url=urlInput.getText();
                //打印和保存数据
                printAndSaveData(url,num);
                //把数据写入文本文件中去
                saveToTxtFile();

            }
        });
        //----------------------------设置面板2组件
        jta=new JTextArea("Your output will appear hear \n",7,30);
        jta.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK);    //设置组件的背景色
        jta.setFont(new Font("楷体",Font.CENTER_BASELINE,16));
        jta.setEnabled(false);
        jta.setSize(400,400);

        //----------------------------设置面板3组件
        JLabel histogram=new JLabel("Histogram");
        histogram.setBounds(100,0,100,30);

        //将组件添加进面板
        jPanel1.add(warring);
        jPanel1.add(urlLabel);
        jPanel1.add(urlInput);
        jPanel1.add(select);
        jPanel1.add(indexLabel);
        jPanel1.add(process);

        jPanel2.add(jta);

        jPanel3.add(histogram);

        contentPane.add(jPanel1);
        contentPane.add(jPanel2);
        contentPane.add(jPanel3);
        frame.setSize(1400,700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe=frame;
    }

    private static void saveToTxtFile() {
        generateTime=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
        fileName=url.replace(".","-")+"-"+generateTime+".txt";
        File file=new File(filePath+fileName);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(fileName.getBytes());
            fileOutputStream.write("\n\n".getBytes());
            fileOutputStream.write("RTT(ms) Histogram".getBytes());
            fileOutputStream.write("\n".getBytes());
            for(String key:mapData.keySet()){
                String val=mapData.get(key);
                Integer count=map.get(val);
                fileOutputStream.write((val+":"+count).getBytes());
                fileOutputStream.write("\n".getBytes());
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    private static void clear() {
        jPanel3.removeAll();
        jta.setText("Your output will appear hear \n");
        //设置面板3组件
        JLabel histogram=new JLabel("Histogram");
        histogram.setBounds(100,0,100,30);
        jPanel3.add(histogram);
        jframe.repaint();
        map=new HashMap<>();
        RTTs=new ArrayList<>();



    }

    private static void printAndSaveData(String url, int num) {
        try {
            Process p = Runtime.getRuntime().exec("cmd /c ping  -n 1 "+url);
            p.waitFor();
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
            reader.readLine();
            String title=reader.readLine();
            if(title==null)title="";
            jta.append(title+"\n");
            if(url==null||"".equals(url.trim()))return;
            for(int i=1;i<=num;i++){
                p = Runtime.getRuntime().exec("cmd /c ping  -n 1 "+url);
                p.waitFor();
                reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
                reader.readLine();
                reader.readLine();
                String info=reader.readLine();
                jta.append(info+"\n");
                Integer ms=ParseRTT(info);
                RTTs.add(ms);
                if(i==num){
                    jta.append(reader.readLine()+"\n");
                    jta.append(reader.readLine()+"\n");
                    jta.append(reader.readLine()+"\n");
                    jta.append(reader.readLine()+"\n");
                }
            }
            //排序
            RTTs.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(o1>o2)return -1;
                    if(o1<o2)return 1;
                    return 0;
                }
            });
            //计算
            Integer max=RTTs.get(0);
            Integer min=RTTs.get(RTTs.size()-1);
            Integer count = (max-min)/3+1;
            System.out.println("count :"+count);
            for(int i=1;i<=count;i++){
                Integer temp1=max-i*3;
                Integer temp2=max-(i-1)*3;
                if(temp2==max){
                    map.put(temp1+"<=RTT<="+temp2,0);
                    mapData.put(temp1+"-"+temp2,temp1+"<=RTT<="+temp2);
                }else{
                    map.put(temp1+"<=RTT<"+temp2,0);
                    mapData.put(temp1+"-"+temp2,temp1+"<=RTT<"+temp2);
                }
            }
            for(int i=1;i<=count;i++){
                for(Integer rtt:RTTs){
                    Integer temp1=max-i*3;
                    Integer temp2=max-(i-1)*3;
                    if(temp2==max){
                        if(temp1<=rtt&&rtt<=temp2){
                            Integer data=map.get(temp1+"<=RTT<="+temp2);
                            data++;
                            map.put(temp1+"<=RTT<="+temp2,data);
                        }
                    }else{
                        if(temp1<=rtt&&rtt<temp2){
                            Integer data=map.get(temp1+"<=RTT<"+temp2);
                            data++;
                            map.put(temp1+"<=RTT<"+temp2,data);
                        }
                    }

                }
            }
            System.out.println(map);
            System.out.println(RTTs);
            //面板三画图
            Set<String> keys=map.keySet();
            int i=0;
            for(String key:keys){
                System.out.println(key);
                i++;
                JLabel a=new JLabel(key);
                a.setBounds(20,i*50,150,30);
                JLabel b=new JLabel();
                Integer init=map.get(key);
                String bString="";
                if(init==null)init=0;
                for(int j=0;j<init;j++){
                    bString+="x    ";
                }
                b.setText(bString);
                b.setBounds(100,i*50,150,30);
                jPanel3.add(a);
                jPanel3.add(b);
            }
            jframe.repaint();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static Integer ParseRTT(String info) {



        if(info.contains("time=")){
            String rtt1=info.substring(info.indexOf("time=")+3,info.indexOf("time=")+5);
            String rtt2=info.substring(info.indexOf("time=")+3,info.indexOf("time=")+6);
            Integer rttInteger;
            try {
                rttInteger=Integer.parseInt(rtt1);
            }catch (Exception e){
                rttInteger=Integer.parseInt(rtt2);
            }
            return rttInteger;
        }
        String rtt1=info.substring(info.indexOf("时间=")+3,info.indexOf("时间=")+5);
        String rtt2=info.substring(info.indexOf("时间=")+3,info.indexOf("时间=")+6);
        Integer rttInteger;
        try {
            rttInteger=Integer.parseInt(rtt1);
        }catch (Exception e){
            rttInteger=Integer.parseInt(rtt2);
        }
        return rttInteger;
    }

}
