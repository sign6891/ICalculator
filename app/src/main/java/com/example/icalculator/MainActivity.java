package com.example.icalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.icalculator.R.drawable.button_result;
import static com.example.icalculator.R.drawable.buttonshape;
import static com.example.icalculator.R.drawable.buttonshapenumber;
import static com.example.icalculator.R.drawable.dark_button_equl;
import static com.example.icalculator.R.drawable.dark_button_shape;
import static com.example.icalculator.R.drawable.dark_button_shape_number;

import com.example.icalculator.theme.ThemeChange;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;

    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;

    private Button clear;// C
    private Button plus_minus;// +/-
    private Button btDeleteCharacter;// <-
    private Button divide;// /
    private Button multiply;//x
    private Button subtract;//-
    private Button add;//-
    private Button equal;//=
    private Button dot;//.

    private TextView tvResult;
    private TextView tvFormula;
    private ImageButton ibBackground;

    private boolean flagBackground = true;
    private String a = "";

    ThemeChange themeChange = new ThemeChange();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linerLayout);

        zero = findViewById(R.id.btZero);
        one = findViewById(R.id.btOne);
        two = findViewById(R.id.btTwo);
        three = findViewById(R.id.btThree);
        four = findViewById(R.id.btFour);
        five = findViewById(R.id.btFive);
        six = findViewById(R.id.btSix);
        seven = findViewById(R.id.btSeven);
        eight = findViewById(R.id.btEight);
        nine = findViewById(R.id.btNine);

        clear = findViewById(R.id.btClear);
        plus_minus = findViewById(R.id.btPlusMinus);
        btDeleteCharacter = findViewById(R.id.btDeleteCharacter);
        divide = findViewById(R.id.btDivide);
        multiply = findViewById(R.id.btMultiply);
        add = findViewById(R.id.btAdd);
        subtract = findViewById(R.id.btSubtract);
        equal = findViewById(R.id.btEqual);
        dot = findViewById(R.id.btDot);
        tvResult = findViewById(R.id.tvResult);
        tvFormula = findViewById(R.id.tvFormula);
        ibBackground = findViewById(R.id.ibBackground);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);

        clear.setOnClickListener(this);
        plus_minus.setOnClickListener(this);
        btDeleteCharacter.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        equal.setOnClickListener(this);
        dot.setOnClickListener(this);
        ibBackground.setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btZero:
                a += "0";
                formula(a);
                break;
            case R.id.btOne:
                a += "1";
                formula(a);
                break;
            case R.id.btTwo:
                a += "2";
                formula(a);
                break;
            case R.id.btThree:
                a += "3";
                formula(a);
                break;
            case R.id.btFour:
                a += "4";
                formula(a);
                break;
            case R.id.btFive:
                a += "5";
                formula(a);
                break;
            case R.id.btSix:
                a += "6";
                formula(a);
                break;
            case R.id.btSeven:
                a += "7";
                formula(a);
                break;
            case R.id.btEight:
                a += "8";
                formula(a);
                break;
            case R.id.btNine:
                a += "9";
                formula(a);
                break;
            case R.id.btClear:
                a = "";
                tvFormula.setText("0");
                tvResult.setText("0");
                break;
            case R.id.btPlusMinus:
                a += "-";
                formula(a);
                break;
            case R.id.btDeleteCharacter:
                //Удаление последнего символа при клики на текст
                if (a.length() > 1) {
                    StringBuffer stringBuffer = new StringBuffer(a);
                    stringBuffer.deleteCharAt(a.length()-1);
                    a = stringBuffer.toString();
                    tvFormula.setText(a);
                } else {
                    formula("0");
                }
                break;
            case R.id.btDivide:
                a += " / ";
                formula(a);
                break;
            case R.id.btMultiply:
                a += " x ";
                formula(a);
                break;
            case R.id.btSubtract:
                a += " - ";
                formula(a);
                break;
            case R.id.btAdd:
                a += " + ";
                formula(a);
                break;
            case R.id.btEqual:
                double x = 0;
                try{
                    x = themeChange.calculate(a);
                } catch (Exception e) {
                    Toast.makeText(this, "Пример не корректен", Toast.LENGTH_SHORT).show();
                }
                long number = (long) x;
                if (themeChange.flagDivideZiro) {
                    Toast.makeText(this, "Разделить на 0 нельзя!!!", Toast.LENGTH_LONG).show();
                    themeChange.flagDivideZiro = false;
                    tvResult.setText("0");
                    break;
                }
                if (x - number == 0) {
                    tvResult.setText(Long.toString(number));
                } else {
                    tvResult.setText(Double.toString(x));
                }
                break;
            case R.id.btDot:
                a += ".";
                formula(a);
                break;
            case R.id.ibBackground:
                colorBackground();
                break;
        }
    }

    private void formula(String form) {
        tvFormula.setText(form);

    }





    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    private void colorBackground() {
        if (flagBackground) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            ibBackground.setImageResource(R.drawable.ic_brightness_high_white_24dp);
            tvFormula.setTextColor(Color.WHITE);
            tvResult.setTextColor(Color.WHITE);
            zero.setBackground(getDrawable(dark_button_shape_number));
            one.setBackground(getDrawable(dark_button_shape_number));
            two.setBackground(getDrawable(dark_button_shape_number));
            three.setBackground(getDrawable(dark_button_shape_number));
            four.setBackground(getDrawable(dark_button_shape_number));
            five.setBackground(getDrawable(dark_button_shape_number));
            six.setBackground(getDrawable(dark_button_shape_number));
            seven.setBackground(getDrawable(dark_button_shape_number));
            eight.setBackground(getDrawable(dark_button_shape_number));
            nine.setBackground(getDrawable(dark_button_shape_number));
            dot.setBackground(getDrawable(dark_button_shape_number));

            zero.setTextColor(Color.WHITE);
            one.setTextColor(Color.WHITE);
            two.setTextColor(Color.WHITE);
            three.setTextColor(Color.WHITE);
            four.setTextColor(Color.WHITE);
            five.setTextColor(Color.WHITE);
            six.setTextColor(Color.WHITE);
            seven.setTextColor(Color.WHITE);
            eight.setTextColor(Color.WHITE);
            nine.setTextColor(Color.WHITE);
            dot.setTextColor(Color.WHITE);

            clear.setBackground(getDrawable(dark_button_shape));
            plus_minus.setBackground(getDrawable(dark_button_shape));
            divide.setBackground(getDrawable(dark_button_shape));
            multiply.setBackground(getDrawable(dark_button_shape));
            subtract.setBackground(getDrawable(dark_button_shape));
            add.setBackground(getDrawable(dark_button_shape));
            equal.setBackground(getDrawable(dark_button_equl));
            btDeleteCharacter.setBackground(getDrawable(dark_button_shape));

            clear.setTextColor(getColor(R.color.colorAccent));
            plus_minus.setTextColor(getColor(R.color.colorAccent));
            divide.setTextColor(getColor(R.color.colorAccent));
            multiply.setTextColor(getColor(R.color.colorAccent));
            subtract.setTextColor(getColor(R.color.colorAccent));
            btDeleteCharacter.setTextColor(getColor(R.color.colorAccent));
            add.setTextColor(getColor(R.color.colorAccent));
            equal.setTextColor(getColor(R.color.colorAccent));

            flagBackground = false;

        } else {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorBackground));
            ibBackground.setImageResource(R.drawable.ic_brightness_low_black_24dp);
            tvFormula.setTextColor(Color.BLACK);
            tvResult.setTextColor(Color.BLACK);

            zero.setBackground(getDrawable(buttonshapenumber));
            one.setBackground(getDrawable(buttonshapenumber));
            two.setBackground(getDrawable(buttonshapenumber));
            three.setBackground(getDrawable(buttonshapenumber));
            four.setBackground(getDrawable(buttonshapenumber));
            five.setBackground(getDrawable(buttonshapenumber));
            six.setBackground(getDrawable(buttonshapenumber));
            seven.setBackground(getDrawable(buttonshapenumber));
            eight.setBackground(getDrawable(buttonshapenumber));
            nine.setBackground(getDrawable(buttonshapenumber));
            dot.setBackground(getDrawable(buttonshapenumber));

            zero.setTextColor(getColor(android.R.color.background_dark));
            one.setTextColor(getColor(android.R.color.background_dark));
            two.setTextColor(getColor(android.R.color.background_dark));
            three.setTextColor(getColor(android.R.color.background_dark));
            four.setTextColor(getColor(android.R.color.background_dark));
            five.setTextColor(getColor(android.R.color.background_dark));
            six.setTextColor(getColor(android.R.color.background_dark));
            seven.setTextColor(getColor(android.R.color.background_dark));
            eight.setTextColor(getColor(android.R.color.background_dark));
            nine.setTextColor(getColor(android.R.color.background_dark));
            dot.setTextColor(getColor(android.R.color.background_dark));

            clear.setBackground(getDrawable(buttonshape));
            plus_minus.setBackground(getDrawable(buttonshape));
            divide.setBackground(getDrawable(buttonshape));
            multiply.setBackground(getDrawable(buttonshape));
            subtract.setBackground(getDrawable(buttonshape));
            add.setBackground(getDrawable(buttonshape));
            equal.setBackground(getDrawable(button_result));
            btDeleteCharacter.setBackground(getDrawable(buttonshape));

            clear.setTextColor(getColor(R.color.colorButtonResult));
            plus_minus.setTextColor(getColor(R.color.colorButtonResult));
            divide.setTextColor(getColor(R.color.colorButtonResult));
            multiply.setTextColor(getColor(R.color.colorButtonResult));
            btDeleteCharacter.setTextColor(getColor(R.color.colorButtonResult));
            subtract.setTextColor(getColor(R.color.colorButtonResult));
            add.setTextColor(getColor(R.color.colorButtonResult));
            equal.setTextColor(getColor(android.R.color.background_dark));

            flagBackground = true;

        }

    }
}
