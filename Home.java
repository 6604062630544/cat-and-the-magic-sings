import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel {
    private Display display;
    private ImageIcon bg = new ImageIcon("Home.png");
    private EleButton start;
    private EleButton exit;
    


    public Home(Display display) {
        this.display = display;
        setLayout(null);

        start = new EleButton("Start",30,400,240,200,75);
		start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.startGame();
            }
        });
        exit = new EleButton("Exit",30,400,340,200,75);
		exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.exit();
            }
        });
        this.add(start);
        this.add(exit);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg.getImage(),0,0,1000,570,this);
     }
}
