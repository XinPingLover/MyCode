import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * Created by BlueLover
 * Name:苏半夏
 * Date:2021/2/27
 * Time:21:41
 */
public class MyFrame extends JFrame {
    MyPanle myPanle = new MyPanle();
    Button button = new Button(myPanle);
    public MyFrame() {
        //设置窗体  logo
        this.setIconImage(new ImageIcon(getClass().getResource("Resources/logo.png")).getImage());
        //设置窗体标题
        this.setTitle("贪吃蛇小游戏");
        //设置窗体初始位置及大小
        this.setBounds(300, 50, 706, 520);
        //设置背景颜色
        this.setBackground(Color.RED);
        //设置窗体的默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置布局管理器为null，清空布局
        this.setLayout(null);
        //设置此窗体是否可由用户调整
        this.setResizable(false);
        //添加控件
        this.add(myPanle);
        //设置键盘监听焦点
        myPanle.setFocusable(true);
        myPanle.requestFocus();
        //添加按钮
        this.add(button);
        //显示
        this.setVisible(true);
    }
    public static void main(String[] args){
        new MyFrame();
    }
}

