package top.a5ish.nbdatastatistics.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import top.a5ish.nbdatastatistics.R;

public class MainActivity extends Activity implements View.OnClickListener{

    private TextView one;
    private TextView two;
    private Button three;
    private TextView four;
    private TextView five;
    private Button six;
    private TextView seven;
    private TextView eight;
    private Button nine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                break;
            case R.id.two:
                break;
            case R.id.three:
                Toast.makeText(this, "222", Toast.LENGTH_SHORT).show();
                break;
            case R.id.four:
                break;
            case R.id.five:

                break;
            case R.id.nine:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
        }
    }

    private void initView() {
        one = (TextView) findViewById(R.id.one);
        one.setOnClickListener(this);
        two = (TextView) findViewById(R.id.two);
        two.setOnClickListener(this);
        two = (TextView) findViewById(R.id.two);
        two.setOnClickListener(this);
        three = (Button) findViewById(R.id.three);
        three.setOnClickListener(this);
        four = (TextView) findViewById(R.id.four);
        four.setOnClickListener(this);
        five = (TextView) findViewById(R.id.five);
        five.setOnClickListener(this);
        six = (Button) findViewById(R.id.six);
        six.setOnClickListener(this);
        seven = (TextView) findViewById(R.id.seven);
        seven.setOnClickListener(this);
        eight = (TextView) findViewById(R.id.eight);
        eight.setOnClickListener(this);
        nine = (Button) findViewById(R.id.nine);
        nine.setOnClickListener(this);
    }




}
