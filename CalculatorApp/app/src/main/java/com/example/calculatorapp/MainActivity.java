package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonadd, buttonsub, buttondiv, buttonmul, buttondot, buttondel, buttoneq;
    TextView input;
    String operand1 = "", operand2 = "";
    String OPERATOR = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0); //Finds a view that was identified by the android:id XML attribute that was processed in onCreate.
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonadd = (Button) findViewById(R.id.buttonadd);
        buttonsub = (Button) findViewById(R.id.buttonsub);
        buttonmul = (Button) findViewById(R.id.buttonmul);
        buttondiv = (Button) findViewById(R.id.buttondiv);
        buttondel = (Button) findViewById(R.id.buttondel);
        buttondot = (Button) findViewById(R.id.buttondot);
        buttoneq = (Button) findViewById(R.id.buttoneq);

        input = (TextView) findViewById(R.id.input);

        /*button0.setOnClickListener(new OnClickListener() { // Click listener for each button.
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });*/

        button0.setOnClickListener(this::setValue); //Instead of defining onClick Listener to each button we can refactor the code by resuing the same function.
        button1.setOnClickListener(this::setValue);
        button2.setOnClickListener(this::setValue);
        button3.setOnClickListener(this::setValue);
        button4.setOnClickListener(this::setValue);
        button5.setOnClickListener(this::setValue);
        button6.setOnClickListener(this::setValue);
        button7.setOnClickListener(this::setValue);
        button8.setOnClickListener(this::setValue);
        button9.setOnClickListener(this::setValue);
        buttondot.setOnClickListener(this::setValue);

        buttonadd.setOnClickListener(this::setOperator);
        buttonsub.setOnClickListener(this::setOperator);
        buttonmul.setOnClickListener(this::setOperator);
        buttondiv.setOnClickListener(this::setOperator);

        buttondel.setOnClickListener(this::clear);

        buttoneq.setOnClickListener(this::evaluate);
    }

    //Function to set the values when a button(numbers) is clicked.
    private void setValue(View v) {
        CharSequence text = ((Button)v).getText();  //Gets the button text that we have set.
        if (OPERATOR.equals("")) {  //Operator exists or not in the input.
            operand1 += text;   //Append op1.
        } else {
            operand2 += text;   //Append op2.
        }
        //input.setText(TextUtils.concat(input.getText(),  text));
        input.setText(input.getText() + "" +text);  //Setting the value to display on the input field.
    }

    //Function to set the operator when a button(+,-,*,/) is clicked.
    private void setOperator(View v) {
        if (input.getText().length() != 0) { //Check if input is not empty.
            CharSequence text = ((Button)v).getText(); //Gets the button text that we have set.
            if(!OPERATOR.equals("")) { //Check if operator exist, if true equate existing equation.
                evaluate(null);
            }
            OPERATOR = v.getTag().toString(); // Set respective operator clicked.
            //input.setText(TextUtils.concat(input.getText(),  text));.
            input.setText(input.getText() + "" +  text); //Add the operator to input field.
        }
        else if((((Button)v).getText()+"").equals("-"))//Check if the first input itself a negative number.
        {
            operand1 = "-";
            input.setText("-");
        }
    }

    //Function to clear the contents when button(C) is clicked.
    private void clear(View v) {
        OPERATOR = ""; //Operator will have empty string.
        operand1 = ""; //Operand1 will have empty string.
        operand2 = ""; //Operand2 will have empty string.
        input.setText(""); //Display empty string on the input text box.
    }

    //Function to evaluate when button(=) is clicked.
    private void evaluate(View v) {
        if(OPERATOR.equals("")) //Check if Operator is empty.
            return;

        double result = 0;
        boolean flag = true;
        switch (OPERATOR) {
            case "ADD":
                result = (Double.parseDouble(operand1) + Double.parseDouble(operand2));
                break;
            case "SUB":
                result = (Double.parseDouble(operand1) - Double.parseDouble(operand2));
                break;
            case "MUL":
                result = (Double.parseDouble(operand1) * Double.parseDouble(operand2));
                break;
            case "DIV":
                if(Double.parseDouble(operand2)==0.0)//Check if operand2 is 0.
                    flag = false;
                else
                    result = (Double.parseDouble(operand1) / Double.parseDouble(operand2));
                break;
        }
        operand1 = ((result % 1 != 0) ) ? String.valueOf(result) : String.valueOf(((int)result));
        //Check whether result is whole number. If true then op1 will have whole number. If false then op1 will have floating value.
        input.setText(operand1); //Display operand1 on the input text box.
        OPERATOR = ""; //Operator will have empty string.
        operand2 = ""; //Operand2 will have empty string.
        if(flag==false)
            input.setText("Error.Divide by Zero!!");
    }
}