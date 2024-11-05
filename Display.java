import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends JFrame{
    private Dimension size = new Dimension(1000, 608);

    public Display() {
        setting();
        showHomeScreen();
    }

    private void setting() {
        this.setTitle("Cat and the Magic Signs");
        this.setSize(size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setVisible(true);
    }

    public void showHomeScreen() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new Home(this));
        this.revalidate();
        this.repaint();
    }

    public void exit(){
        System.exit(0);
    }

    public void startGame() {
        this.getContentPane().removeAll();
        this.getContentPane().add(new Game(this));
        this.revalidate();
        this.repaint();
    }
    public static void main(String[] args) {
        Display display = new Display();
    }

}
