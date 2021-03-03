import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by BlueLover
 * Name:苏半夏
 * Date:2021/2/27
 * Time:21:44
 */
public class MyPanle extends JPanel implements KeyListener {
    //创建食物
    Food food = new Food();
    Snake snake = new Snake(food);//创建贪吃蛇对象
    SnakeThread snakeTread = new SnakeThread();//创建线程
    public MyPanle() {
        //设置容器坐标及大小
        this.setBounds(0, 0, 700, 440);
        //设置容器颜色
        this.setBackground(Color.GREEN);
        snakeTread.start();//启动线程

        //键盘控制方向事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                snake.keyControl(e);//调用贪吃蛇的方向控制方法
            }
        });
    }

    //设置背景图片
    public void paintComponent(Graphics g) {	//重写来达到重绘的目的
        super.paintComponent(g);
        ImageIcon ic = new ImageIcon(this.getClass().getResource("Resources/bg_game.jpg"));
        //下面这行是为了背景图片可以跟随窗口自行调整大小
        g.drawImage(ic.getImage(), 0, 0,this.getWidth(),this.getHeight(), this);
    }


    //绘制容器,一般是重新实现此方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //设置绘制的颜色
        g.setColor(Color.GRAY);
        // 画横线
        for (int i = 0; i < Config.ROWS; i++) {
            //使用当前颜色在点（x1，y1）和（x2，y2）之间画一条线
            g.drawLine(0, Config.SPAN * i, Config.COLS * Config.SPAN, Config.SPAN * i);
        }
        // 画竖线
        for (int i = 0; i < Config.COLS; i++) {
            g.drawLine(Config.SPAN * i, 0, Config.SPAN * i, Config.ROWS * Config.SPAN);
        }

        snake.move();//贪吃蛇移动
        snake.draw(g);//画蛇
        food.draw(g);//画食物

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //调用贪吃蛇的控制方向方法
        snake.keyControl(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //贪吃蛇的线程
    class SnakeThread extends Thread{
        boolean flag = true;//重新开始
        @Override
        public void run() {
            super.run();
            //当贪吃蛇没有死亡，则应该继续游戏移动
            while(Config.islive && flag){
                try {
                    Thread.sleep(300);//当前线程休息0.3秒
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Config.isPause = true是代表继续游戏
                if(Config.islive && Config.isPause){
                    //重绘的执行流程  repaint()----> 调用awt线程-----> update()方法---->paint()方法
                    repaint();//重绘,页面刷新
                }
                if(!Config.islive){
                    JOptionPane.showMessageDialog(MyPanle.this, "游戏结束");
                }
            }
        }

        //停止线程的方法
        public void stopThread(){
            flag = false;
        }
    }

}
