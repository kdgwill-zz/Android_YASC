package com.math.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalcActivity extends Activity {
    private float total = 0;
    private Operator op;
    private TextView tv;
    private TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        op = Operator.ADD;
        tv = (TextView) findViewById(R.id.textView);
        sign = (TextView) findViewById(R.id.textViewSign);
    }

    public void onButtonClick(View v) {
        Button button = (Button) v;
        String bText = (String) button.getText();

        String s = (String) tv.getText();
        s = s.equals("0") ? bText : s + bText;
        tv.setText(s);
    }

    public void onChangeOperator(View v) {

        switch (v.getId()) {
            case R.id.button_delete:
                String s = (String) tv.getText();
                if (s != null && !s.equals("")) {
                    s = (String) s.subSequence(0, s.length() - 1);
                } else {
                    s = "0";
                }
                tv.setText(s);
                break;
            case R.id.button_clear:
                total = 0;
                tv.setText("0");
                sign.setText("");
                break;
            case R.id.button_equals:
                update();
                break;
            case R.id.button_add:
                update();
                op = Operator.ADD;
                sign.setText(tv.getText() + "\n+\t\t\t");
                tv.setText("0");
                break;
            case R.id.button_subtract:
                update();
                op = Operator.SUBTRACT;
                sign.setText(tv.getText() + "\n-\t\t\t");
                tv.setText("0");
                break;
            case R.id.button_multiply:
                update();
                op = Operator.MULTIPLY;
                sign.setText(tv.getText() + "\n*\t\t\t");
                tv.setText("0");
                break;
            case R.id.button_divide:
                update();
                op = Operator.DIVIDE;
                sign.setText(tv.getText() + "\n/\t\t\t");
                tv.setText("0");
                break;
            case R.id.button_exp:
                update();
                op = Operator.EXPONENT;
                sign.setText(tv.getText() + "^\t\t");
                tv.setText("0");
                break;
            default:
                tv.setText("ERROR");
                break;
        }
    }

    private void update() {

        float value = Float.parseFloat((String) tv.getText());
        total = op.execute(total, value);
        tv.setText(Float.toString(total));
        String s = (String) sign.getText();
        s = s.substring(0, s.length() - 2);//remove third \t
        s = s + value;
        sign.setText(s);
        op = Operator.EMPTY;//SET TO THIS TO PREVENT MULTIPLE UPDATE

    }

    private enum Operator {
        EMPTY(-1), ADD(0), SUBTRACT(1), MULTIPLY(2), DIVIDE(3), EXPONENT(4);
        private final int i;

        Operator(int i) {
            this.i = i;
        }

        public float execute(float total, float num) {
            switch (i) {
                case 0:
                    total += num;
                    break;
                case 1:
                    total -= num;
                    break;
                case 2:
                    total *= num;
                    break;
                case 3:
                    total /= num;
                    break;
                case 4:
                    total = (float) Math.pow((double) total, (double) num);
                    break;
                default:
                    break;
            }
            return total;
        }
    }

}
