package friendsFile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//主窗口
public class DialogDemon  {
    public DialogDemon(){
        JFrame frame=new JFrame("nb");
        frame.setVisible(true);
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // JFrame 放东西 容器
        Container container=frame.getContentPane();
        //绝对布局
        container.setLayout(null);
        //按钮
        JButton button=new JButton("点击弹出一个对话框");//创建
        button.setBounds(30,20,200,50);
        //点击这个按钮时弹出一个弹窗
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹窗
                new MyDialogDemon();
            }
        });

        container.add(button);
    }

    public static void main(String[] args){
        new DialogDemon();
    }
}

//弹窗窗口
class MyDialogDemon {
    public MyDialogDemon(){
        JDialog dialog=new JDialog();
        dialog.setVisible(true);
        dialog.setBounds(100,100,500,500);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);弹窗自带 不用写 写了就是代码重复
        Container container=dialog.getContentPane();
        container.setLayout(null);

        container.setBackground(Color.PINK);
        JLabel label = new JLabel("这是一个弹窗");
        label.setBounds(10,10,200,20);
        container.add(label);
    }
}