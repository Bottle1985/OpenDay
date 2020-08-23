package uni.tbd.openday;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import uni.tbd.openday.Activity.BuildingInfo;

public class TRAC_NGHIEM extends AppCompatActivity {
    private TextView countLabel;
//    private ImageView questionImage;
    static int idkhoa, mode= 0;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;
    //private Button answerBtn5;

    private String[] Answers;
    private int[] AnswerCount;
    //private int quizCount = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String[][] quizData = {
            // {"Question", "Choice 1", "Choice 2", "Choice 3", "Choice 4", "Choice 5"}
            {"What interests you most?", "Different cultures to my own", "How people express themselves", "The economy", "How things work", "The news"},
            {"Do you work better as a team or as an individual?", "As an individual", "As a team", "A bit of both", "One on one with a person"},
            {"Which would you say is your best skill", "My ability to reason and aruge", "I can figure out the solution to most problems", "I'm excellent at planning and organising", "I'm a quick learner"},
            {"What salary range would you be happy with?", "+30,000", "+50,000", "+70,000", "+100,000", "Whatever allows me to live comfortably"},
            {"Are you interested in travelling with work?", "Definitely! It's a free plane ticket", "I'm not fussed", "I'd rather not", "No"},
            {"What are your values?", "I'm ambitious and will work hard to succeed", "I want a good work/life balance", "I want to work at something I'm passionate about", "I want to develop something that will help others"},
            {"Are there jobs available in your area?", "Yes. Lots.", "Some but it's competative", "No but I'm willing to move", "Hardly any in my field"},
            {"Do you tend to be more introverted or extroverted?", "Completely introverted", "Absolutely extroverted", "More extroverted, but a little introverted", "More introverted, but a little extroverted"},
            {"How do you feel about growth opportunities within work?", "I don't mind reaching a high plateau", "I'm comfortable at any level", "I want to win at everything I do", "I must succeed everytime"},
            {"Which best describes your personality?", "High functioning, friendly", "Logical and reasonable", "Free thinking, quirky", "Smart, thinking ahead of everyone else", "Innovative, ruthless"},
    };
    String[][] quizDataVN = {
            // {"Question", "Choice 1", "Choice 2", "Choice 3", "Choice 4", "Choice 5"}
            {"Bạn quan tâm điều gì nhất?", "Sự đa dạng của các nền văn hóa ",  "Mọi thứ hoạt động như thế nào", "Nền kinh tế", "Cách mọi người thể hiện bản thân", "Tin tức"},
            {"Bạn thích làm việc nhóm hay cá nhân?",  "Như một đội","Như một cá nhân", "Cả 2 kiểu", "Một đối một với người khác"},
            {"Kĩ năng nào là tốt nhất của bạn",  "Tiếp thu nhanh", "Tìm ra giải pháp cho các vấn đề", "Lập kế hoạch và tổ chức","Khả năng giải thích và lý luận"},
            {"Mức lương nào sẽ làm bạn hài lòng?", "+5,000,000đ", "+10,000,000đ", "+15,0000,000đ", "+20,000,000đ", "Bao nhiêu cũng được miễn là sống thoải mái"},
            {"Bạn có thích du lịch kết hợp công việc hay không?", "Chắc chắn rồi, đó là vé máy bay miễn phí", "Không đời nào", "Sao cũng được", "Thỉnh thoảng thôi"},
            {"Điều gì quan trọng đối với bạn?", "Tôi muốn làm điều mình đam mê", "Tôi có quyết tâm và sẽ làm việc chăm chỉ để thành công", "Tôi muốn cân bằng công việc và cuộc sống",  "Tôi muốn làm gì đó giúp người khác"},
            {"Có nhu cầu việc làm trong khu vực của bạn hay không?", "Không nhưng tôi sẵn sàng di chuyển chỗ ở",  "Một số và có cạnh tranh mới được nhận", "Có, rất nhiều", "Hầu như không có lĩnh vực của tôi"},
            {"Bạn sống nội tâm hay hướng ngoại?", "Hoàn toàn hướng ngoại", "Hoàn toàn hướng nội", "Hướng ngoại hơn, hướng nội một chút", "Hướng nội hơn, hướng ngoại một chút"},
            {"Bạn cảm thấy thế nào về cơ hội phát triển trong công việc?","Tôi phải thành công mọi lúc","Tôi thoải mái ở mọi vị trí",  "Tôi sẵn sàng đạt vị trí cao nhất",  "Tôi muốn giành chiến thắng ở mọi thứ tôi làm"},
            {"Tính cách nổi trội của bạn?", "Hoạt bát, thân thiện", "Suy nghĩ có logic và hợp lý", "Thông minh, đi trước mọi người", "Sáng tạo, không khoan nhượng", "Tự do và cá tính"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trac_nghiem);
        this.overridePendingTransition(R.anim.activity_open_enter,
                R.anim.activity_open_exit);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
        }
        countLabel = findViewById(R.id.countLabel);
        //questionLabel = findViewById(R.id.countLabel);
//        questionImage = findViewById(R.id.questionImage);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);
        //answerBtn5 = findViewById(R.id.answerBtn5);
        Answers = new String[5];
        AnswerCount = new int[4];
        // Create quizArray from quizData.
        for (String[] quizDatum : quizDataVN) {
            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizDatum[0]); // Image Name
            tmpArray.add(quizDatum[1]); // Choice 1
            tmpArray.add(quizDatum[2]); // Choice 2
            tmpArray.add(quizDatum[3]); // Choice 3
            tmpArray.add(quizDatum[4]); // Choice 4
            //tmpArray.add(quizDatum[5]); // Choice 5
            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {
        // Update quizCountLabel.
        //countLabel.setText("Q" + quizCount);
        // Generate random number between 0 and 4 (quizArray's size -1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set Image and Right Answer.
        // Array format: {"Image Name", "Right Answer", "Choice1", "Choice2", "Choice3"}
//        questionImage.setImageResource(
//                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName()));
        //questionLabel.setText(quiz.get(0));
        // Update question.
        countLabel.setText(quiz.get(0));
        Answers[0] = quiz.get(1);
        Answers[1] = quiz.get(2);
        Answers[2] = quiz.get(3);
        Answers[3] = quiz.get(4);
        //Answers[4] = quiz.get(5);

        // Remove "Question" from quiz and shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set choices.
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));
        //answerBtn5.setText(quiz.get(4));
        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        //String alertTitle;
        if (btnText.equals(Answers[0])) {
            AnswerCount[0]++;
        } else if (btnText.equals(Answers[1]))
        {
            AnswerCount[1]++;
        } else if (btnText.equals(Answers[2]))
        {
            AnswerCount[2]++;
        } else if (btnText.equals(Answers[3]))
        {
            AnswerCount[3]++;
        } else
        {
            AnswerCount[4]++;
        }

        if (quizArray.size() < 1) {
                // quizArray is empty.
                showResult();
            } else {
                //quizCount++;
                showNextQuiz();
        }
    }

    public void showResult() {
        String thongbao;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kết quả");
        builder.setCancelable(false);
        int max = 0;
        for (int i : AnswerCount) {
            if (i > max) {
                max = i;
            }
        }
        if (max == AnswerCount[0]) {
            thongbao = "Chúc mừng bạn phù hợp với Ngôn ngữ học";
            builder.setMessage(thongbao);
        } else if (max == AnswerCount[1])
        {
            thongbao = "Chúc mừng bạn phù hợp với Công nghệ thông tin";
            builder.setMessage(thongbao);
        } else if (max == AnswerCount[2])
        {
            thongbao = "Chúc mừng bạn phù hợp với Kinh tế";
            builder.setMessage(thongbao);
        }
        else
        {
            thongbao = "Chúc mừng bạn phù hợp với Luật";
            builder.setMessage(thongbao);
        }
        builder.setNeutralButton("Ngành phù hợp", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check1 = thongbao.contains("Công nghệ thông tin");
                boolean check2 = thongbao.contains("Ngôn ngữ học");
//                boolean check3 = thongbao.contains("Kinh tế");
                if (check1){
                    mode = 1;
                    idkhoa = 1;
                }else if(check2){
                    mode = 1;
                    idkhoa = 2;
                }else {
                    mode = 1;
                    idkhoa = 3;
                }
                startActivity(new Intent (TRAC_NGHIEM.this,GT_KHOA.class));
            }
        });
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
