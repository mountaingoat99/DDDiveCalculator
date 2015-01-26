package com.rodriguez.dddivecalculator;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    private EditText score1, score2, score3, score4, score5, score6, score7, ddEnter;
    private Button btnClear, btnCalc;
    private TextView totalView;
    private double sc1, sc2, sc3, sc4, sc5, sc6, sc7, dd, total;
    private ArrayList<Double> scores = new ArrayList<>();
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        // this will hide the keyboard on app open
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setUpView();
        addListenerOnButton();
    }

    private void addListenerOnButton() {

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getText();
                if (dd > 0) {
                    if (scores.size() >= 2) {
                        double finalScore = CalcScore();
                        if (finalScore > 0) {
                            DecimalFormat df = new DecimalFormat("#.00");
                            totalView.setVisibility(View.VISIBLE);
                            totalView.setText(df.format(finalScore));

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Invalid Score, please try again", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Please enter at least two scores", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "You have to enter a DD", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ClearAll();
            }
        });
    }

    private void getText() {

        // lets clear out anything in the old array first
        scores.clear();

        if (ddEnter.getText().length() > 0) {
            dd = Double.parseDouble(ddEnter.getText().toString().trim());
        }
        if (score1.getText().length() > 0) {
            sc1 = Double.parseDouble(score1.getText().toString().trim());
            scores.add(sc1);
        }
        if (score2.getText().length() > 0) {
            sc2 = Double.parseDouble(score2.getText().toString().trim());
            scores.add(sc2);
        }
        if (score3.getText().length() > 0) {
            sc3 = Double.parseDouble(score3.getText().toString().trim());
            scores.add(sc3);
        }
        if (score4.getText().length() > 0) {
            sc4 = Double.parseDouble(score4.getText().toString().trim());
            scores.add(sc4);
        }
        if (score5.getText().length() > 0) {
            sc5 = Double.parseDouble(score5.getText().toString().trim());
            scores.add(sc5);
        }
        if (score6.getText().length() > 0) {
            sc6 = Double.parseDouble(score6.getText().toString().trim());
            scores.add(sc6);
        }
        if (score7.getText().length() > 0) {
            sc7 = Double.parseDouble(score7.getText().toString().trim());
            scores.add(sc7);
        }
    }

    private double CalcScore() {

        double finalScore = 0.0;
        //convert the ArrayList for processing
        Double[] theScores = new Double[ scores.size()];
        scores.toArray(theScores);
        Arrays.sort(theScores);

        if (theScores.length == 2) {

            for (int i = 0; i < theScores.length; i++) {
                finalScore = finalScore + theScores[i];
            }

            finalScore = (finalScore * 1.5) * dd;

        } else if (theScores.length == 3) {

        } else if (theScores.length == 5) {

        } else if (theScores.length == 7) {

        } else {
            finalScore = 0.0;
        }
        return finalScore;
    }

    private void ClearAll() {

        ddEnter.setText("");
        score1.setText("");
        score2.setText("");
        score3.setText("");
        score4.setText("");
        score5.setText("");
        score6.setText("");
        score7.setText("");
        totalView.setText("");
        totalView.setVisibility(View.INVISIBLE);
        sc1 = 0.0;
        sc2 = 0.0;
        sc3 = 0.0;
        sc4 = 0.0;
        sc5 = 0.0;
        sc6 = 0.0;
        sc7 = 0.0;
        total = 0.0;
        dd = 0.0;
        ddEnter.requestFocus();
        scores.clear();
    }

    private void showMenuAboutAlert() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_about);
        Button ok = (Button)dialog.findViewById(R.id.buttonOkay);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showMenuAboutAlert();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpView() {
        btnCalc = (Button)findViewById(R.id.buttonScore);
        btnClear = (Button)findViewById(R.id.buttonClear);
        ddEnter = (EditText)findViewById(R.id.ddEnter);
        score1 = (EditText)findViewById(R.id.editScore1);
        score2 = (EditText)findViewById(R.id.editScore2);
        score3 = (EditText)findViewById(R.id.editScore3);
        score4 = (EditText)findViewById(R.id.editScore4);
        score5 = (EditText)findViewById(R.id.editScore5);
        score6 = (EditText)findViewById(R.id.editScore6);
        score7 = (EditText)findViewById(R.id.editScore7);
        totalView = (TextView)findViewById(R.id.totalScore);
    }
}
