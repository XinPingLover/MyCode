import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by BlueLover
 * Name:苏半夏
 * Date:2021/2/27
 * Time:21:47
 */
public class Button extends JPanel implements ActionListener{
    MyPanle myPanle;
    JButton pause ;         //暂停
    JButton continu;        //继续
    JButton restart;        //重新开始
    JButton exit;           //退出
    public Button(MyPanle myPanle) {
        this.myPanle = myPanle;
        this.setBounds(0, 440, 706, 60);
        pause = new JButton("暂停游戏");
        continu = new JButton("继续游戏");
        restart = new JButton("重新开始");
        exit = new JButton("退出游戏");

        this.add(pause);
        this.add(continu);
        this.add(restart);
        this.add(exit);

        //注册按钮监听
        pause.addActionListener(this);
        continu.addActionListener(this);
        restart.addActionListener(this);
        exit.addActionListener(this);
    }

    //ActionEvent:获取事件作用的对象
    @Override
    public void actionPerformed(ActionEvent e){
        //监听对象是暂停游戏
        if (e.getSource()==pause){
            System.out.println("暂停游戏");
            Config.isPause = false;
        }

        //监听对象是继续游戏
        if (e.getSource()==continu){
            System.out.println("继续游戏");
            Config.isPause = true;
            //设置键盘监听焦点
            myPanle.setFocusable(true);
            myPanle.requestFocus();
        }

        //监听对象是重新开始游戏
        if (e.getSource()==restart){
            System.out.println("重新游戏");
            //1.停掉当前线程
            myPanle.snakeTread.stopThread();
            //2.重新生成蛇和食物
            Food food = new Food();
            myPanle.food = food;
            myPanle.snake = new Snake(food);
            //将控制条件还原到初始状态
            Config.isPause = true;
            Config.islive = true;
            //3.创建新的线程对象(内部类对象)
            MyPanle.SnakeThread snakeThread = myPanle.new SnakeThread();
            //4.启动线程
            snakeThread.start();
            myPanle.snakeTread = snakeThread;
            //设置键盘监听焦点
            myPanle.setFocusable(true);
            myPanle.requestFocus();
        }

        //监听对象是退出游戏
        if (e.getSource()==exit){
            System.out.println("退出游戏");
            System.exit(0);
        }
    }

}
