package snakegame;

import javax.swing.*;
public class snakegame extends JFrame {
snakegame()
{
    super("Snake Game");

     add(new Board());
        pack();
   // setLocation(500, 500);

   setResizable(false);    
    setLocationRelativeTo(null);

}
public static void main(String[] args) {
    new snakegame().setVisible(true);
}
}



