import java.awt.*;
import java.util.Random;

/**
 * Created by BlueLover
 * Name:苏半夏
 * Date:2021/2/27
 * Time:21:48
 */
public class Food {
    private int row;//Y坐标
    private int col;//X坐标
    //画食物
    public void draw(Graphics g) {
        g.setColor(Color.RED);//设置颜色
        g.fillRect(col*Config.SPAN,row*Config.SPAN,  Config.SPAN, Config.SPAN);//方格
        System.out.println("画食物");
    }

    //重新定义位置
    public void repair(){
        row = new Random().nextInt(Config.ROWS);//0~Config.ROWS的随机数
        col = new Random().nextInt(Config.COLS);
    }

    public Food() {
        repair();
    }

    //获取食物坐标
    public Rectangle getFoodRec(){
        return new Rectangle(col*Config.SPAN, row*Config.SPAN, Config.SPAN, Config.SPAN);
    }


}
