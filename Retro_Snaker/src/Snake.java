import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by BlueLover
 * Name:苏半夏
 * Date:2021/2/27
 * Time:21:55
 */
public class Snake {
    Node head;//头
    Node body;//身体
    Node tail;//尾巴
    Food food;//食物

    //构造方法
    public Snake(Food food) {
        head = new Node(7, 13, Config.R);//头的初始坐标
        body = new Node(7, 12, Config.R);//身体初始坐标
        tail = new Node(7, 11, Config.R);//尾巴初始坐标
        //节点之间的关系
        head.next = body;
        body.pre = head;
        body.next = tail;
        tail.pre = body;
        this.food = food;
    }

    //画蛇方法
    public void draw(Graphics g) {
        //蛇有多个节点组成，需要遍历每个节点，然后把每个节点画图出来
        for(Node n = head; n!=null ; n = n.next){
            //调用节点的画图方法
            n.draw(g);
        }
    }

    //贪吃蛇移动
    public void move(){
        System.err.println("贪吃蛇在移动.....");
        //怎么移动？ 1.添加蛇头 2.去蛇尾 3.吃食物 4.死亡检测
        addHead();//添加蛇头
        removeTail();//去蛇尾
        eat();//吃食物
        deadCheck();//死亡检测
    }

    /**
     * 吃食物
     * 1.判断贪吃蛇坐标和食物坐标是否重合
     * 2.重新生成一条新的贪吃蛇（填头不去尾）
     * 3.重新随机生成食物
     */
    public void eat() {
        System.out.println("碰到食物了吗？"+getHeadRec().intersects(food.getFoodRec()));
        System.out.println("贪吃蛇的坐标？"+getHeadRec());
        System.out.println("食物的坐标？"+food.getFoodRec());
        if(getHeadRec().intersects(food.getFoodRec())){
            addHead();//添加蛇头
            food.repair();//重新布局食物
        }
    }


    //根据前进方向添加蛇头
    public void addHead(){
        Node node = null;//临时变量
        switch (head.dir) {//根据前进方向判断
            case Config.R: //如果方向是右，那么应该在右边添加蛇头。也就意味着把head的坐标右移一位。 如何右移，可以通过一个临时变量来实现
                node = new Node(head.row, head.col+1, head.dir);
                break;
            case Config.L:
                node = new Node(head.row, head.col-1, head.dir);
                break;
            case Config.U:
                node = new Node(head.row-1, head.col, head.dir);
                break;
            case Config.D:
                node = new Node(head.row+1, head.col, head.dir);
                break;
        }
//        assert node != null;
        node.next = head;
        head.pre = node;
        head = node;//把临时变量 赋值给蛇头
    }

    //去除蛇尾
    public void removeTail(){
        tail.pre.next = null;//把蛇尾的前一个节点，的后指针设为空
        tail = tail.pre; //把蛇尾的前一个节点 赋值为 蛇尾
    }


    //键盘控制
    public void keyControl(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W://W键
                //如果蛇头方向不朝下，才可以向上移动
                if(!head.dir.equals(Config.D)){
                    head.dir = Config.U;
                }
                break;
            case KeyEvent.VK_A://A键
                if(!head.dir.equals(Config.R)){
                    head.dir = Config.L;
                }
                break;
            case KeyEvent.VK_S://S键
                //如果蛇头方向不朝上，才可以向上移动
                if(!head.dir.equals(Config.U)){
                    head.dir = Config.D;
                }
                break;
            case KeyEvent.VK_D://W键
                if(!head.dir.equals(Config.L)){
                    head.dir = Config.R;
                }
                break;
        }
    }

    //获取蛇头坐标
    public Rectangle getHeadRec(){
        return new Rectangle(head.col*Config.SPAN, head.row*Config.SPAN, Config.SPAN, Config.SPAN);//获取蛇头坐标矩形空间
//        return new Rectangle(head.row*Config.SPAN,head.col*Config.SPAN,  Config.SPAN, Config.SPAN);//获取蛇头坐标矩形空间
    }

    //死亡检测
    public void deadCheck(){
        //1.不能出界
        if(head.row<0 || head.col<0 || head.row>Config.ROWS-1 || head.col>Config.COLS-1
        ){
            Config.islive = false;
            System.out.println("贪吃蛇死亡");
        }
        //2.头 不能碰到身体 遍历身体,然后判断尾不能和头位置重复
        for(Node n = head.next ; n!=null ; n = n.next){
            if(head.row == n.row && head.col == n.col){
                Config.islive = false;
                break;
            }
        }
    }


}
