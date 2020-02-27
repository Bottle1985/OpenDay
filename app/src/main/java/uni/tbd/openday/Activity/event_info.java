package uni.tbd.openday.Activity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import uni.tbd.openday.R;
import uni.tbd.openday.SUKIEN;

public class event_info extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_event);
        this.overridePendingTransition(R.anim.animation_enter,
                R.anim.animation_leave);
        TextView eventname = findViewById(R.id.event_name);
        TextView eventsummary = findViewById(R.id.event_summary);
        TextView eventplace = findViewById(R.id.event_place);
        TextView eventtime = findViewById(R.id.event_time);
        TextView eventlecturer = findViewById(R.id.event_lecturer);
        ImageView eventimg = findViewById(R.id.img_event);
        switch (SUKIEN.id_sukien){
            case 0:{
                eventname.setText(R.string.event_name_1);
                eventsummary.setText(R.string.event_summary_1);
                eventplace.setText(R.string.event_place_1);
                eventtime.setText(R.string.event_time_1);
                eventlecturer.setText(R.string.event_lecturer_1);
                eventimg.setImageResource(R.drawable.sukien0);
                break;
            }
            case 1:{
                eventname.setText(R.string.event_name_1);
                eventsummary.setText(R.string.event_summary_1);
                eventplace.setText(R.string.event_place_1);
                eventtime.setText(R.string.event_time_1);
                eventlecturer.setText(R.string.event_lecturer_1);
                eventimg.setImageResource(R.drawable.sukien1);
                break;
            }
            case 2:{
                eventname.setText(R.string.event_name_1);
                eventsummary.setText(R.string.event_summary_1);
                eventplace.setText(R.string.event_place_1);
                eventtime.setText(R.string.event_time_1);
                eventlecturer.setText(R.string.event_lecturer_1);
                eventimg.setImageResource(R.drawable.sukien2);
                break;
            }
        }
    }
}
