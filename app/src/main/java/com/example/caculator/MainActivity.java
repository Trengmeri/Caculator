package com.example.caculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAC, btnDEL, btnChia, btnDu, btnCong, btnTru, btnBang, btnNhan, btnPhay;
    private TextView textviewHistory, textviewResult;
    private String number = null;
    double lastnumber =0, firstnumber =0;
    boolean operator = false;
    boolean isEqual = false;
    boolean dot = true;
    boolean del = true;
    String status = null;
    String history, result;
    String pattern = "###,###.####";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn0 = this.findViewById(R.id.btn0);
        btn1 = this.findViewById(R.id.btn1);
        btn2 = this.findViewById(R.id.btn2);
        btn3 = this.findViewById(R.id.btn3);
        btn4 = this.findViewById(R.id.btn4);
        btn5 = this.findViewById(R.id.btn5);
        btn6 = this.findViewById(R.id.btn6);
        btn7 = this.findViewById(R.id.btn7);
        btn8 = this.findViewById(R.id.btn8);
        btn9 = this.findViewById(R.id.btn9);

        btnAC = this.findViewById(R.id.btnAC);
        btnDEL = this.findViewById(R.id.btnDEL);
        btnDu = this.findViewById(R.id.btnDu);
        btnCong = this.findViewById(R.id.btnCong);
        btnChia = this.findViewById(R.id.btnChia);
        btnTru = this.findViewById(R.id.btnTru);
        btnBang = this.findViewById(R.id.btnBang);
        btnNhan = this.findViewById(R.id.btnNhan);
        btnPhay = this.findViewById(R.id.btnPhay);

        textviewHistory = this.findViewById(R.id.textviewHistory);
        textviewResult = this.findViewById(R.id.textviewResult);

        btn0.setOnClickListener(view -> numberclick("0"));
        btn1.setOnClickListener(view -> numberclick("1"));
        btn2.setOnClickListener(view -> numberclick("2"));
        btn3.setOnClickListener(view -> numberclick("3"));
        btn4.setOnClickListener(view -> numberclick("4"));
        btn5.setOnClickListener(view -> numberclick("5"));
        btn6.setOnClickListener(view -> numberclick("6"));
        btn7.setOnClickListener(view -> numberclick("7"));
        btn8.setOnClickListener(view -> numberclick("8"));
        btn9.setOnClickListener(view -> numberclick("9"));

        btnCong.setOnClickListener(view -> {
            if(isEqual){
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+"+");
            }else {
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+result+"+");
            }
            if (operator) {
                if(status == "multi") Multi();
                else if (status == "div") Div();
                else if (status == "minus") Minus();
                else if (status == "mod") Mod();
                else Plus();
            }
            isEqual = false;
            operator = false;
            dot = true;
            status="plus";
            number=null;
        });
        btnTru.setOnClickListener(view -> {
            if(isEqual){
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+"-");
            }else {
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+result+"-");
            }
            if (operator) {
                if(status == "multi") Multi();
                else if (status == "div") Div();
                else if (status == "plus") Plus();
                else if (status == "mod") Mod();
                else Minus();
            }
            isEqual=false;
            operator = false;
            dot = true;
            status="minus";
            number=null;
        });
        btnChia.setOnClickListener(view -> {
            if(isEqual){
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+"÷");
            }else {
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+result+"÷");
            }
            if (operator) {
                if(status == "multi") Multi();
                else if (status == "plus") Plus();
                else if (status == "minus") Minus();
                else if (status == "mod") Mod();
                else Div();
            }
            dot = true;
            operator = false;
            status="div";
            number=null;
            isEqual = false;
        });
        btnNhan.setOnClickListener(view -> {
            if(isEqual){
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+"x");
            }else {
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+result+"x");
            }
            if (operator) {
                if(status == "plus") Plus();
                else if (status == "div") Div();
                else if (status == "minus") Minus();
                else if (status == "mod") Mod();
                else Multi();
            }
            dot = true;
            operator = false;
            status="multi";
            number=null;
            isEqual = false;
        });
        btnDu.setOnClickListener(view -> {
            if(isEqual){
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+"%");
            }else {
                history = textviewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textviewHistory.setText(history+result+"%");
            }
            if (operator) {
                if(status == "plus") Plus();
                else if (status == "div") Div();
                else if (status == "minus") Minus();
                else if (status == "multi") Multi();
                else Mod();
            }
            operator = false;
            dot = true;
            status="mod";
            number=null;
            isEqual = false;
        });
        btnBang.setOnClickListener(view -> {

            history = textviewHistory.getText().toString();
            result = textviewResult.getText().toString();
            textviewHistory.setText(history+result);

            if (operator) {
                if(status == "plus") Plus();
                else if (status == "div") Div();
                else if (status == "minus") Minus();
                else if (status == "multi") Multi();
                else if (status == "mod") Mod();
                else firstnumber = Double.parseDouble(textviewResult.getText().toString());
            }
            operator = false;
            isEqual = true;
            dot = false;
        });
        btnAC.setOnClickListener(view -> {
            number = null;
            operator = false;
            textviewResult.setText("0");
            textviewHistory.setText("");
            firstnumber = 0;
            lastnumber = 0;
            dot = true;
            del = true;
        });
        btnDEL.setOnClickListener(view -> {
            if(del){
                textviewResult.setText("0");
            }else{
                number = number.substring(0, number.length() - 1);
                if(number.length()==0) btnDEL.setClickable(false);
                else if (number.contains(".")) dot = false;
                else dot = true;
            }

            textviewResult.setText(number);
        });
        btnPhay.setOnClickListener(view -> {
            if(dot){
                if(number==null) number = "0.";
                else number = number + ".";
            }
            dot = false;
            textviewResult.setText(number);
        });
    }
    public void numberclick (String view){
        if(number == null) number = view;
        else number = number + view;

        textviewResult.setText(number);
        operator = true;
        del= false;
        btnDEL.setClickable(true);
    }
    public void Minus(){
        if(firstnumber==0) firstnumber = Double.parseDouble(textviewResult.getText().toString());
        else {
            lastnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber = firstnumber - lastnumber;
        }
        textviewResult.setText(decimalFormat.format(firstnumber));
    }
    public void Plus(){
        if(firstnumber==0) firstnumber = Double.parseDouble(textviewResult.getText().toString());
        else {
            lastnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber = firstnumber + lastnumber;
        }
        textviewResult.setText(decimalFormat.format(firstnumber));
    }
    public void Multi(){
        if(firstnumber==0) firstnumber = Double.parseDouble(textviewResult.getText().toString());
        else {
            lastnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber = firstnumber * lastnumber;
        }
        textviewResult.setText(decimalFormat.format(firstnumber));
    }
    public void Div(){
        if(firstnumber==0) firstnumber = Double.parseDouble(textviewResult.getText().toString());
        else {
            lastnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber = firstnumber / lastnumber;
        }
        textviewResult.setText(decimalFormat.format(firstnumber));
    }
    public void Mod() {
        if(firstnumber == 0) {
            firstnumber = Double.parseDouble(textviewResult.getText().toString());
        } else {
            lastnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber = firstnumber % lastnumber;
        }
        textviewResult.setText(decimalFormat.format(firstnumber));
    }
}