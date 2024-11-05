import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class Event {
    private Game game;
    private Cat cat;
    public Random random = new Random();

    public Event(Game game, Cat cat) {
        this.game = game;
        this.cat = cat;
    }

    public void checkhit() {
        if (cat.x + 50 > game.option1.x && cat.y >= game.option1.y && cat.y <= game.option1.y + game.option1.height) {
            checkAnswer(game.isOption1Correct ? game.answerOptions1[game.currentSetIndex] : game.answerOptions2[game.currentSetIndex]);
            game.stage++;
        } else if (cat.x + 50 > game.option2.x && cat.y >= game.option2.y && cat.y <= game.option2.y + game.option2.height) {
            checkAnswer(game.isOption1Correct ? game.answerOptions2[game.currentSetIndex] : game.answerOptions1[game.currentSetIndex]);
            game.stage++;
        }            
    }

    public void checkstage() {
        if(game.stage > 5){
            game.stage = 0;
        }
    }


    public void checkAnswer(String answer) {
        if (answer.equals(game.correctAnswer)) {
            game.score++;
        }else {
            applyRandomPenalty();    
        }
        game.currentSetIndex = (game.currentSetIndex + 1) % game.questionSetColors.length;
        game.generateQuestionSet();
        cat.x = 0;
    }

    public void applyRandomPenalty() {
        int penalty = random.nextInt(3);
        switch (penalty) {
            case 0 -> cat.health -= 1;
            case 1 -> game.score--;
            case 2 -> increaseCatSpeedTemporarily();
        }
    }

    public void increaseCatSpeedTemporarily() {
        cat.speed = 10;
        Timer speedResetTimer = new Timer(2000, (ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cat.speed = 5;
            }
        });
        speedResetTimer.setRepeats(false);
        speedResetTimer.start();
    }
}
