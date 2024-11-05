import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gamewin extends JPanel {
    private Display display;
    private ImageIcon bg = new ImageIcon("winner.png");

    public Gamewin(Display display) {
        try{
            this.display = display;
            this.setLayout(null);

            EleButton restart = new EleButton("Restart",15,400,370,200,50);
            restart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    display.startGame();
                }
            });
    
            EleButton home = new EleButton("Home",15,400,450,200,50);
            home.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    display.showHomeScreen();
                }
            });
            this.add(restart);
            this.add(home);

        }catch (Exception e) {
            e.printStackTrace();
        }			
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg.getImage(),0,0,1000,570,this);
    }
}
