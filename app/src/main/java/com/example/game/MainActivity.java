package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonenter;

    TextView display_question;
    TextView display_answer;

    Double answer;
    String randomExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        buttonenter = (Button) findViewById(R.id.buttonenter);

        display_question = (TextView) findViewById(R.id.display_question);
        display_answer = (TextView) findViewById(R.id.display_answer);

        try {
            this.randomExpression = this.generateRandomExpression();
            this.answer = this.evaluate(randomExpression);
        } catch (ScriptException e) {
            this.answer = 0.0;
            e.printStackTrace();
        }

        String stringdouble= Double.toString(this.answer);
        //display.setText(stringdouble);

        display_question.setText(this.randomExpression);
        display_answer.setText(stringdouble);


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
}