package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, button_enter, button_less, button_delete;

    TextView display_question;
    TextView display_answer;
    TextView display_counter;

    Integer correct_answer;
    String randomExpression;

    Integer success;
    Integer fail;

    TextView display_time;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display_time = findViewById(R.id.display_time);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        button_enter = (Button) findViewById(R.id.button_enter);
        button_less = (Button) findViewById(R.id.button_less);
        button_delete = (Button) findViewById(R.id.button_delete);

        display_question = (TextView) findViewById(R.id.display_question);
        display_answer = (TextView) findViewById(R.id.display_answer);
        display_counter = (TextView) findViewById(R.id.display_counter);

        success = 0;
        fail = 0;

        this.printResult();

        this.newGame();

        resetTime();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "0");
            }
        });

        button_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_answer.setText(display_answer.getText() + "-");
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = display_answer.getText().toString();
                if ((result != null) && (result.length() > 0)) {
                    result = result.substring(0, result.length() - 1);
                }

                display_answer.setText(result);
            }
        });

        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Integer given_answer = Integer.valueOf(display_answer.getText().toString());

                    if(given_answer.equals(correct_answer)) {
                        success = success + 1;
                    } else {
                        fail = fail + 1;
                    }

                    printResult();
                    newGame();

                    //countDownTimer.start();

                }catch(Exception e){

                    display_answer.setText("");

                }

            }
        });

    }

    public void resetTime() {
        countDownTimer = new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {

                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                display_time.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }

            public void onFinish() {
                display_time.setText("00:00:00");

                try {

                    Integer given_answer = Integer.valueOf(display_answer.getText().toString());

                    if(given_answer.equals(correct_answer)) {
                        success = success + 1;
                    } else {
                        fail = fail + 1;
                    }

                }catch(Exception e){

                    display_answer.setText("");

                }

                resetTime();
                newGame();
            }
        }.start();
    }

    public void newGame() {

        try {
            this.randomExpression = this.generateRandomExpression();
            display_question.setText(this.randomExpression);
            this.correct_answer = this.evaluate(randomExpression).intValue();
            display_answer.setText("");
        } catch (ScriptException e) {
            this.correct_answer = 0;
            e.printStackTrace();
        }
    }

    public String generateRandomExpression() {
        String res = "";

        String [] operators = new String[]{"+","-","*"};

        Random rand = new Random();
        int upperbound = 10;
        int operator_uperbound = 3;

        // first number
        Integer first_int_random = new Integer(rand.nextInt(upperbound));
        res += first_int_random.toString();

        // generate first operator expression
        Integer int_operator_index = new Integer(rand.nextInt(operator_uperbound));
        String first_operator = operators[int_operator_index];
        res += first_operator;

        // second number
        Integer second_int_random = new Integer(rand.nextInt(upperbound));
        second_int_random += first_int_random;
        res += second_int_random.toString();

        // generate second operator expression
        int_operator_index = new Integer(rand.nextInt(operator_uperbound));
        String second_operator = operators[int_operator_index];
        res += second_operator;

        // third number
        Integer third_int_random = new Integer(rand.nextInt(upperbound));
        res += third_int_random.toString();

        return res;
    }

    public Double evaluate (String expression) throws ScriptException {

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        Double answer = (Double) engine.eval(expression);

        return answer;
    }

    public void printResult() {


        String result = "Aciertos = "+ this.success+ " | Fallos = " + this.fail;

        display_counter.setText(result);
    }
}