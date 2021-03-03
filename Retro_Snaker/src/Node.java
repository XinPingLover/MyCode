import java.awt.*;

/**
 * Created by BlueLover
 * Name:苏半夏
 * Date:2021/2/27
 * Time:21:55
 */
public class Node {
    int row;//行
    int col;//列
    Node next;//下个节点
    Node pre;//上个节点
    String dir;//前进方向
    //构造方法  初始化贪吃蛇的位置信息及制定贪吃蛇前进的方向
    public Node(int row, int col, String dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }
    //画图方法
    public void draw(Graphics g) {
        if(this.pre==null){//蛇头黄色
            g.setColor(Color.YELLOW);
        }else{
            g.setColor(Color.BLUE);
        }
        g.fillOval(col*Config.SPAN, row*Config.SPAN, Config.SPAN, Config.SPAN);
    }
}
