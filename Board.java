

package snakegame;     
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// import java.util.Timer;

public class Board extends JPanel implements ActionListener{
    private Image apple;
    private Image dot;
    private Image head;

    private Timer timer;
    private final int ALL_DOTS = 900;
    private final int DOT_SIZE = 10;
    private final int randamPosition = 29;
    private int apple_x;
    private int apple_y;
 
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private boolean leftdirection = false;
    private boolean  rightdirection = true;
    private boolean updirection = false;
    private boolean downdirection = false;
private boolean ingame = true;
    private  int dots;

    Board(){

        setBackground(Color.black);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);
       addKeyListener(new TAdapter());

      

        loadImages();
        initgame();

    }
    public  void loadImages(){
   ImageIcon l1 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));  
     apple = l1.getImage();
   ImageIcon l2 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
   dot = l2.getImage();
   ImageIcon l3 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
   head =l3.getImage();

    }
    
    public void initgame(){
        dots = 3;
     for(int i=0;i< dots;i++ ){

        y[i] = 50;
        x[i] = 50 - i * DOT_SIZE;
     } 
     locateApple();

timer = new Timer(140,this);
timer.start();
    }

    public void locateApple(){
  int r =    (int) (Math.random() * randamPosition);
  apple_x = r* DOT_SIZE;
  r =  (int) (Math.random() * randamPosition);
  apple_y = r* DOT_SIZE;
    }
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       
        drow(g);
    }

    public void drow(Graphics g){
        if(ingame){

        g.drawImage(apple, apple_x, apple_y, this);
        for(int i=0;i<dots; i++){
            if(i==0){
                g.drawImage(head, x[i], y[i], this);
            }
            else{
                g.drawImage(dot, x[i], y[i],this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }else{
        gameover(g);
    }

}
public void gameover(Graphics g){
String msg = "game over";
Font font = new Font("SAN SERIF",Font.BOLD,14);
FontMetrics metrics = getFontMetrics(font) ;
g.setColor(Color.WHITE);
g.setFont(font);
g.drawString(msg,(300 - metrics.stringWidth(msg)) / 2,300/2);

}


    public void move(){
     for(int i= dots ;i>0; i-- ){
    x[i] = x[i-1];
    y[i] = y[i-1];
}
  if(leftdirection){
     x[0] = x[0] - DOT_SIZE;
    }
  if(rightdirection){
      x[0] = x[0] + DOT_SIZE;
    }
   if(updirection){
      y[0] = y[0] - DOT_SIZE;
  }
  if(downdirection){
    y[0] = y[0] + DOT_SIZE;
  }

    }
    public void checkapple(){
        if(x[0] == apple_x && y[0] == apple_y){
            dots ++;
            locateApple();
        }
    }
    public void checkcollition(){
        for(int i = dots;i>0;i--){
            if((i>4) && (x[0] ==x[1]) && (y[0] == y[1])){
                ingame = false;
            }
        }
        if(y[0] >=300){
            ingame = false;
        }
        if(x[0] >=300){
            ingame = false;
        }
        if(x[0] < 0){
            ingame = false;
        }  
        if(y[0] < 0){
            ingame = false;
        } 
       if(!ingame){
        timer.stop();
       }

    }

    public void actionPerformed(ActionEvent ae){
   if(ingame){
    checkapple();
    checkcollition();
         move();
   }   
   repaint();
    
    }


    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && (!rightdirection)){
                leftdirection = true;
                updirection = false ;
                downdirection = false;
                }
             if(key == KeyEvent.VK_RIGHT && (!leftdirection)){
                    rightdirection = true;
                    updirection = false ;
                    downdirection = false;
                    }
                    if(key == KeyEvent.VK_UP && (!downdirection)){
                        leftdirection = false;
                        rightdirection = false;
                        updirection = true ;
                        }  
                        if(key == KeyEvent.VK_DOWN && (!updirection)){
                            leftdirection = false;
                            rightdirection = false;
                            downdirection = true ;
        }
   }
 
}
}

    


 
  
