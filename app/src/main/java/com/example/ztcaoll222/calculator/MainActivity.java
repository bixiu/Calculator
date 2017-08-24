package com.example.ztcaoll222.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button_0;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_dot;
    Button button_c;
    Button button_del;
    Button button_division;
    Button button_multiplication;
    Button button_less;
    Button button_add;
    Button button_equal;
    Button button_leftBracket;
    Button button_rightBracket;

    TextView textView;

    boolean clear_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_0 = (Button)findViewById(R.id.button_0);
        button_1 = (Button)findViewById(R.id.button_1);
        button_2 = (Button)findViewById(R.id.button_2);
        button_3 = (Button)findViewById(R.id.button_3);
        button_4 = (Button)findViewById(R.id.button_4);
        button_5 = (Button)findViewById(R.id.button_5);
        button_6 = (Button)findViewById(R.id.button_6);
        button_7 = (Button)findViewById(R.id.button_7);
        button_8 = (Button)findViewById(R.id.button_8);
        button_9 = (Button)findViewById(R.id.button_9);
        button_dot = (Button)findViewById(R.id.button_dot);

        button_c = (Button)findViewById(R.id.button_c);
        button_del = (Button)findViewById(R.id.button_del);
        button_division = (Button)findViewById(R.id.button_division);
        button_multiplication = (Button)findViewById(R.id.button_multiplication);
        button_less = (Button)findViewById(R.id.button_less);
        button_add = (Button)findViewById(R.id.button_add);
        button_equal = (Button)findViewById(R.id.button_equal);

        button_leftBracket = (Button)findViewById(R.id.button_leftBracket);
        button_rightBracket = (Button)findViewById(R.id.button_rightBracket);

        textView = (TextView)findViewById(R.id.textView);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_dot.setOnClickListener(this);
        button_c.setOnClickListener(this);
        button_del.setOnClickListener(this);
        button_division.setOnClickListener(this);
        button_multiplication.setOnClickListener(this);
        button_less.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_equal.setOnClickListener(this);
        button_leftBracket.setOnClickListener(this);
        button_rightBracket.setOnClickListener(this);

        clear_flag = false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_leftBracket:
            case R.id.button_rightBracket:
            case R.id.button_dot:
            case R.id.button_division:
            case R.id.button_multiplication:
            case R.id.button_less:
            case R.id.button_add:
                if (clear_flag) {
                    textView.setText("");
                }
                clear_flag = false;

                textView.setText(String.format("%s%s", textView.getText(), ((Button)view).getText()));
                break;

            case R.id.button_c:
                textView.setText("");
                clear_flag = false;
                break;

            case R.id.button_del:
                if (textView.length() > 0) {
                    textView.setText(textView.getText().subSequence(0, textView.length()-1));
                }
                clear_flag = false;
                break;

            case R.id.button_equal:
                Double ret = 0.0;
                Cal cal = new Cal();
                ret = cal.start(textView.getText().toString());
                textView.setText(ret.toString());
                clear_flag = true;
                break;

            default:
                break;
        }
    }
}
