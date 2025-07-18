package com.khalil.caluculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    TextView tvSolution, tvResult;
    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSolution = findViewById(R.id.tvSolution);
        tvResult = findViewById(R.id.tvResult);
        tvSolution.setText("0");
        tvResult.setText("");

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnDot = findViewById(R.id.btnDecimal);
        Button btnPercent = findViewById(R.id.btnPercent);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnEquals = findViewById(R.id.btnEquals);

        View.OnClickListener numberClick = v -> {
            Button b = (Button) v;
            input += b.getText().toString();
            tvSolution.setText(formatInput(input));
        };

        btn0.setOnClickListener(numberClick);
        btn1.setOnClickListener(numberClick);
        btn2.setOnClickListener(numberClick);
        btn3.setOnClickListener(numberClick);
        btn4.setOnClickListener(numberClick);
        btn5.setOnClickListener(numberClick);
        btn6.setOnClickListener(numberClick);
        btn7.setOnClickListener(numberClick);
        btn8.setOnClickListener(numberClick);
        btn9.setOnClickListener(numberClick);
        btnDot.setOnClickListener(numberClick);

        btnPercent.setOnClickListener(v -> {
            input += "/100";
            tvSolution.setText(formatInput(input));
        });

        btnPlus.setOnClickListener(v -> addOperator("+"));
        btnMinus.setOnClickListener(v -> addOperator("-"));

        btnMultiply.setOnClickListener(v -> {
            input += "*";
            tvSolution.setText(formatInput(input));
        });

        btnDivide.setOnClickListener(v -> {
            input += "/";
            tvSolution.setText(formatInput(input));
        });

        btnClear.setOnClickListener(v -> {
            input = "";
            tvSolution.setText("0");
            tvResult.setText("0");
        });

        btnDelete.setOnClickListener(v -> {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
                if (input.isEmpty()) {
                    tvSolution.setText("0");
                } else {
                    tvSolution.setText(formatInput(input));
                }
            }
        });

        btnEquals.setOnClickListener(v -> {
            try {
                Expression exp = new ExpressionBuilder(input).build();
                double result = exp.evaluate();
                tvResult.setText(String.valueOf(result));
            } catch (Exception e) {
                tvResult.setText("Error");
            }
        });
    }

    void addOperator(String op) {
        if (!input.isEmpty()) {
            char lastChar = input.charAt(input.length() - 1);
            if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/')
                return;
            input += op;
            tvSolution.setText(formatInput(input));
        }
    }

    String formatInput(String input) {
        return input.replace("*", "×").replace("/", "÷");
    }
}
