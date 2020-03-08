package uni.tbd.openday;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TRAC_NGHIEM extends AppCompatActivity {
    private TextView countLabel;
    private ImageView questionImage;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String[][] quizData = {
            // {"Question", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"What interests you most?", "Different cultures to my own", "How people express themselves", "The economy", "How things work"},
            {"image_triangle", "triangle", "circle", "square", "pentagon"},
            {"image_square", "square", "triangle", "pentagon", "hexagon"},
            {"image_pentagon", "pentagon", "circle", "triangle", "hexagon"},
            {"image_hexagon", "hexagon", "triangle", "pentagon", "circle"},
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trac__nghiem);
        this.overridePendingTransition(R.anim.activity_open_enter,
                R.anim.activity_open_exit);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        // Create quizArray from quizData.
        for (String[] quizDatum : quizData) {
            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizDatum[0]); // Image Name
            tmpArray.add(quizDatum[1]); // Right Answer
            tmpArray.add(quizDatum[2]); // Choice1
            tmpArray.add(quizDatum[3]); // Choice2
            tmpArray.add(quizDatum[4]); // Choice3

            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {

        // Update quizCountLabel.
        countLabel.setText("Q" + quizCount);

        // Generate random number between 0 and 4 (quizArray's size -1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set Image and Right Answer.
        // Array format: {"Image Name", "Right Answer", "Choice1", "Choice2", "Choice3"}
//        questionImage.setImageResource(
//                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName()));
        rightAnswer = quiz.get(1);

        // Remove "Image Name" from quiz and shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set choices.
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            // Correct!!
            //alertTitle = "Correct!";
            rightAnswerCount++;

        } else {
            // Wrong
            //alertTitle = "Wrong...";
        }

        // Create Dialog.
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(alertTitle);
//        builder.setMessage("Answer : " + rightAnswer);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizArray.size() < 1) {
                    // quizArray is empty.
                    showResult();

                } else {
                    quizCount++;
                    showNextQuiz();
                }
//            }
//        });
//        builder.setCancelable(false);
//        builder.show();
    }

    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(rightAnswerCount + " / 5");
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }
}
