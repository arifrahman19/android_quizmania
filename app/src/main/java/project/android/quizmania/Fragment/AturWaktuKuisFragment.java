package project.android.quizmania.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

import project.android.quizmania.AlarmReceiver;
import project.android.quizmania.MyConstants;
import project.android.quizmania.MyDialogFragment;
import project.android.quizmania.R;


public class AturWaktuKuisFragment extends Fragment{

    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
    int id=0;
    boolean visible=false;
    TextView alarm[]=new TextView[10];
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    LinearLayout layout1;
    Button btn1[] = new Button[10];
    Button btn2[]= new Button[10];
    Button btn3;
    private Button btnTab2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_alarm, container, false);
        btnTab2 = (Button) view.findViewById(R.id.btnTab2);

        layout1=(LinearLayout )view.findViewById(R.id.layout1_1);
        alarm[0]= (TextView)view.findViewById(R.id.alarm1);
        btn1[0] = (Button)view.findViewById(R.id.button1_1);
        btn2[0] = (Button)view.findViewById(R.id.button2_1);
        btn3=(Button)view.findViewById(R.id.button3_1);

        alarm[1]= (TextView)view.findViewById(R.id.alarm2);
        btn1[1] = (Button)view.findViewById(R.id.button1_2);
        btn2[1] = (Button)view.findViewById(R.id.button2_2);

        alarm[2]= (TextView)view.findViewById(R.id.alarm3);
        btn1[2] = (Button)view.findViewById(R.id.button1_3);
        btn2[2] = (Button)view.findViewById(R.id.button2_3);

        alarm[3]= (TextView)view.findViewById(R.id.alarm4);
        btn1[3] = (Button)view.findViewById(R.id.button1_4);
        btn2[3] = (Button)view.findViewById(R.id.button2_4);

        alarm[4]= (TextView)view.findViewById(R.id.alarm5);
        btn1[4] = (Button)view.findViewById(R.id.button1_5);
        btn2[4] = (Button)view.findViewById(R.id.button2_5);



        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        final Intent myIntent = new Intent(getActivity(), AlarmReceiver.class);
//set alarm
        btn1[0].setOnClickListener( new OnClickListener() {
            public void onClick(View view) {
                id=0;
                pendingIntent = PendingIntent.getBroadcast(getActivity(), id, myIntent, 0);
                alarmPopUp();
            }
        });

        btn1[1].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                id=1;
                pendingIntent = PendingIntent.getBroadcast(getActivity(), id, myIntent, 0);
                alarmPopUp();
            }
        });

        btn1[2].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                id=2;
                pendingIntent = PendingIntent.getBroadcast(getActivity(), id, myIntent, 0);
                alarmPopUp();
            }
        });

        btn1[3].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                id=3;
                pendingIntent = PendingIntent.getBroadcast(getActivity(), id, myIntent, 0);
                alarmPopUp();
            }
        });

        btn1[4].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                id=4;
                pendingIntent = PendingIntent.getBroadcast(getActivity(), id, myIntent, 0);
                alarmPopUp();
            }
        });

//cancel alarm
        btn2[0].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                cancelAlarm(0);
            }
        });

        btn2[1].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                cancelAlarm(1);
            }
        });

        btn2[2].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                cancelAlarm(2);
            }
        });

        btn2[3].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                cancelAlarm(3);
            }
        });

        btn2[4].setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                cancelAlarm(4);
            }
        });

        btn3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if(visible==false) {
                    layout1.getLayoutParams().height = 500;
                    visible = true;
                   // layout1.setVisibility(view.GONE);
                }
                else{
                    layout1.getLayoutParams().height = 0;
                    visible = false;
                   // layout1.setVisibility(view.VISIBLE);
                }
            }
        });


        return view;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage (Message msg){
            Bundle bundle = msg.getData();
            timeHour = bundle.getInt(MyConstants.HOUR);
            timeMinute = bundle.getInt(MyConstants.MINUTE);
            setAlarm();
        }
    }

    private void setAlarm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        alarm[id].setText(timeHour + ":" + timeMinute);

    }
    private void cancelAlarm(int i) {
        PendingIntent displayIntent = PendingIntent.getBroadcast(getActivity(), i, new Intent(getActivity(), AlarmReceiver.class), 0);
        if(displayIntent != null) {
            alarmManager.cancel(displayIntent);
            displayIntent.cancel();
            alarm[i].setText("");
        }
    }

    private  void alarmPopUp(){
        Bundle bundle = new Bundle();
        bundle.putInt(MyConstants.HOUR, Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        bundle.putInt(MyConstants.MINUTE, Calendar.getInstance().get(Calendar.MINUTE));
        MyDialogFragment fragment = new MyDialogFragment(new MyHandler());
        fragment.setArguments(bundle);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragment, MyConstants.TIME_PICKER);
        transaction.commit();
    }
}
