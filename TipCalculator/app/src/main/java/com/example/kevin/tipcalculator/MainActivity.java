package com.example.kevin.tipcalculator;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.kevin.tipcalculator.R.id.btn_enter;
import static com.example.kevin.tipcalculator.R.id.tv_label;

public class MainActivity extends AppCompatActivity {
    private EditText _amount;
    private TextView _amountNotice;
    private SeekBar _rating;
    private TextView _serviceTitle;
    private TextView _tipAmount;
    private Button _button;
    private TextView _finalAmount;
//creating variables for reference from xml to java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this._amount = (EditText) findViewById(R.id.et_amount);
        this._rating = (SeekBar) findViewById(R.id.sb_rating);
        this._serviceTitle = (TextView) findViewById(R.id.tv_hint);
        this._tipAmount = (TextView) findViewById(R.id.tv_label);
        this._button = (Button) findViewById(R.id.btn_enter);
        this._finalAmount = (TextView) findViewById(R.id.tv_finalA);

//references are made and visibility of widgets are set

        this._rating.setVisibility(View.GONE);
        this._serviceTitle.setVisibility(View.GONE);
        this._tipAmount.setVisibility(View.GONE);
        this._button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//hides and shows other UI after button is clicked
                String _check = _amount.getText().toString();
                if (_check.matches("")) {//check if input is empty or not
                    Toast.makeText(MainActivity.this,"Please Enter a Number",Toast.LENGTH_SHORT).show();
                }else{//if not empty passes into the system
                    _button.setVisibility(View.GONE);
                    _rating.setVisibility(View.VISIBLE);
                    _serviceTitle.setVisibility(View.VISIBLE);
                    _tipAmount.setVisibility(View.VISIBLE);
                    _rating.setMax(10);//set seekbar size

//added a text change listener for after the edit text is changed, without this the app will crash after text is changed
                    _serviceTitle.setText("Selected Rating: " + _rating.getProgress() + "/" + _rating.getMax());
                    _amount.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

//seekbar listener will change the value of tip amount as the user slides through it based on the number rating.
                    _rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {



                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            String temp = _amount.getText().toString();
                            Toast T = Toast.makeText(MainActivity.this,"Please Enter a Number",Toast.LENGTH_SHORT);
                            _serviceTitle.setText("Selected Rating: " + progress + "/" + _rating.getMax());
                            if(temp.isEmpty()){
//checks if after edit text the textbox is empty then shows error.
                                T.show();
                            }
                            else{//if not empty then continues to calculate the amount and displays it.
                                T.cancel();
                                double tempAmount = Double.parseDouble(temp);
                                double tipAmount;
                                /*NumberFormat formatter = NumberFormat.getCurrencyInstance();
                                String roundedAmount;*/
                                if (progress <= 3) {
                                    tipAmount = tempAmount * 0.1;
                                    //roundedAmount = formatter.format(tipAmount);
                                    _tipAmount.setText("Tip Amount: " + String.format("%.2f",tipAmount));
                                    _finalAmount.setText("Total Amount: " + String.format("%.2f",tipAmount+tempAmount));
                                } else if (progress > 3 && progress <= 5) {
                                    tipAmount = tempAmount * 0.13;
                                    //roundedAmount = formatter.format(tipAmount);
                                    _tipAmount.setText("Tip Amount: " + String.format("%.2f",tipAmount));
                                    _finalAmount.setText("Total Amount: " + String.format("%.2f",tipAmount+tempAmount));
                                } else if (progress > 5 && progress <= 7) {
                                    tipAmount = tempAmount * 0.15;
                                    //roundedAmount = formatter.format(tipAmount);
                                    _tipAmount.setText("Tip Amount: " + String.format("%.2f",tipAmount));
                                    _finalAmount.setText("Total Amount: " + String.format("%.2f",tipAmount+tempAmount));
                                } else if (progress > 7 && progress <= 9) {
                                    tipAmount = tempAmount * 0.2;
                                    //roundedAmount = formatter.format(tipAmount);
                                    _tipAmount.setText("Tip Amount: " + String.format("%.2f",tipAmount));
                                    _finalAmount.setText("Total Amount: " + String.format("%.2f",tipAmount+tempAmount));
                                } else {
                                    tipAmount = tempAmount * 0.25;
                                    //roundedAmount = formatter.format(tipAmount);
                                    _tipAmount.setText("Tip Amount: " + String.format("%.2f",tipAmount));
                                    _finalAmount.setText("Total Amount: " + String.format("%.2f",tipAmount+tempAmount));
                                }
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            _serviceTitle.setText("Selected Rating: " + _rating.getProgress() + "/" + _rating.getMax());
                        }//shows rating after the user stops sliding.
                    });
                }
            }
        });

    }
}
