import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Game extends JPanel implements Runnable, KeyListener {
    Cat cat = new Cat(50, 360, 90,3,5);    
    Signs option1 = new Signs(850, 340, 120, 120);
    Signs option2 = new Signs(850, 460, 120, 120);
    Event event = new Event(this,cat);
    Display display;

    Color LightBrown = new Color(153, 102, 0);
    Color Purple = new Color(102,0,153);   

    public Thread gameThread;
    public Color questionColor;
    public String correctAnswer;
    public String[] questionSetColors = { "red", "yellow", "pink", "green", "purple", "blue" };
    public String[] answerOptions1 = { "red", "yellow", "pink", "green", "purple", "blue" };
    public String[] answerOptions2 = { "green", "blue", "black", "red", "green", "yellow" };
    public Color[] answerOption1Colors = { Color.GREEN, Color.BLUE, Color.BLACK, Color.RED, Color.GREEN, Color.YELLOW };
    public Color[] answerOption2Colors = { Color.RED, Color.YELLOW, Color.PINK, Color.GREEN, Purple, Color.BLUE };
    public int currentSetIndex;
    public boolean isOption1Correct;
    public int score = 0;
    public Random random = new Random();
    public int stage = 0;
    private boolean isGameOver = false;  

    public Game(Display display) {
        this.display = display;
        this.setBounds(0, 0, 1000, 600);
        currentSetIndex = random.nextInt(questionSetColors.length);
        generateQuestionSet();

        gameThread = new Thread(this);
        gameThread.start();

        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);

        SwingUtilities.invokeLater(() -> this.requestFocusInWindow());
    }

    public void generateQuestionSet() {
        switch (questionSetColors[currentSetIndex]) {
            case "red":
                questionColor = Color.RED;
                correctAnswer = "red";
                break;
            case "yellow":
                questionColor = Color.YELLOW;
                correctAnswer = "yellow";
                break;
            case "pink":
                questionColor = Color.PINK;
                correctAnswer = "pink";
                break;
            case "green":
                questionColor = Color.GREEN;
                correctAnswer = "green";
                break;
            case "purple":
                questionColor = Purple;
                correctAnswer = "purple";
                break;
            case "blue":
                questionColor = Color.BLUE;
                correctAnswer = "blue";
                break;
        }        
        isOption1Correct = random.nextBoolean();
    }

    @Override
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);
        try{
            this.drawBackground(g);
            g.setColor(questionColor);
            g.fillOval(getWidth() / 2 - 100, 30, 50, 50);
            
            if(stage < 4){
                g.setColor(Color.BLACK);
                g.setFont(Element.getFont(20));
                g.drawString("What color is this?", getWidth()/2 - 40, 60);        
                g.drawString("Score: " + score, 850, 50);
            }else{
                g.setColor(Color.WHITE);
                g.setFont(Element.getFont(20));
                g.drawString("What color is this?", getWidth()/2 - 40, 60);        
                g.drawString("Score: " + score, 850, 50);
            }            
    
            g.setColor(Color.RED);
            g.drawString("HP--> " + cat.health, 50, 50);

            g.drawImage(cat.getImage(),cat.x, cat.y, cat.catSize, cat.catSize,null);
            g.drawImage(option1.getImage(),option1.x, option1.y, option1.width,option1.height,null);
            g.drawImage(option2.getImage(),option2.x, option2.y, option2.width,option1.height,null);
        
            g.setFont(Element.getFont(18));
            if (isOption1Correct) {
                g.setColor(answerOption1Colors[currentSetIndex]);
                g.drawString(answerOptions1[currentSetIndex], option1.x + 35, option1.y + 50);
                g.setColor(answerOption2Colors[currentSetIndex]);
                g.drawString(answerOptions2[currentSetIndex], option2.x + 35, option2.y + 50);
            } else {
                g.setColor(answerOption2Colors[currentSetIndex]);
                g.drawString(answerOptions2[currentSetIndex], option1.x + 35, option1.y + 50);
                g.setColor(answerOption1Colors[currentSetIndex]);
                g.drawString(answerOptions1[currentSetIndex], option2.x + 35, option2.y + 50);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    private void drawBackground(Graphics g) throws IOException {
            if(stage < 2){
                g.drawImage(ImageIO.read(new File("sky.jpg")),0,0,1000,370, null);            
			    g.drawImage(ImageIO.read(new File("dir.png")),0,370,1000,200, null);
            }else if(stage < 4){
                g.drawImage(ImageIO.read(new File("sea.jpg")),0,0,1000,370, null);            
                g.drawImage(ImageIO.read(new File("sand.jpg")),0,370,1000,200, null);
            }else{
                g.drawImage(ImageIO.read(new File("gal.png")),0,0,1000,370, null);            
                g.drawImage(ImageIO.read(new File("moon.png")),0,370,1000,200, null);
            }
			
	}

    @Override
    public void run() {
        while (true) {
            if (!isGameOver) {
                cat.move();
                event.checkhit();
                event.checkstage();
                checkHealth();
                checkWin();
                repaint();                
            };

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkHealth() {
        if (cat.health <= 0) {
            isGameOver = true;
            display.getContentPane().removeAll();
            display.getContentPane().add(new Gameover(display, score));
            display.revalidate();
            display.repaint();            
            cat.health = 3;
            score = 0;
        }
    }

    public void checkWin() {
        if (score >= 6) {
            isGameOver = true;
            display.getContentPane().removeAll();
            display.getContentPane().add(new Gamewin(display));
            display.revalidate();
            display.repaint();            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            cat.upperLane();
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            cat.lowerLane();
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
