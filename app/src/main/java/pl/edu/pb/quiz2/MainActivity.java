package pl.edu.pb.quiz2;


import static pl.edu.pb.quiz2.R.id.question_points;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private TextView pointsTextView;
    private int currentIndex = 0;
    private int points = 0;
    private String debug_tag;
    private String debug_onStart;
    private String debug_onStop;
    private String debug_onDestroy;
    private String debug_onPause;
    private String debug_onResume;
    private String debug_onCreate;
    private String debug_onSaveInstanceState;

    private Question[] questions = new Question[]{
            new Question(R.string.q_projects, true),
            new Question(R.string.q_earth, false),
            new Question(R.string.q_atom, true),
            new Question(R.string.q_mountain, true),
            new Question(R.string.q_flat_earth, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        pointsTextView = findViewById(R.id.question_points);
        updatePointsDisplay();

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });

        setNextQuestion();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        if (userAnswer == correctAnswer) {
            points++; // Zwiększ liczbę punktów za poprawną odpowiedź
            Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrect_answer, Toast.LENGTH_SHORT).show();
        }

        // Przechodź do kolejnego pytania
        currentIndex = (currentIndex + 1) % questions.length;
        setNextQuestion();
        updatePointsDisplay();
    }
    private void updatePointsDisplay() {
        pointsTextView.setText("Punkty: " + points);
    }
}
