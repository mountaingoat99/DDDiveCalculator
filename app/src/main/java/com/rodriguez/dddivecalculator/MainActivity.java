package com.rodriguez.dddivecalculator;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private EditText score1, score2, score3, score4, score5, score6, score7, ddEnter;
    private Button btnClear, btnCalc, btnNext, btnBack, btnOne, btnTwo, btnThree, btnFour,
            btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnPeriod;
    private TextView totalView;
    private double sc1, sc2, sc3, sc4, sc5, sc6, sc7, dd;
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
        // portrait only in small phones
        if(getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        // this will hide the keyboard on app open
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setUpView();
        addListenerOnButton();
        disableKeyboard();
        doATextWatcher();
    }

    private void disableKeyboard() {

        ddEnter.setInputType(0);
        ddEnter.setRawInputType(InputType.TYPE_CLASS_TEXT);
        ddEnter.setTextIsSelectable(true);

        score1.setInputType(0);
        score1.setRawInputType(InputType.TYPE_CLASS_TEXT);
        score1.setTextIsSelectable(true);
        score2.setInputType(0);
        score2.setRawInputType(InputType.TYPE_CLASS_TEXT);
        score2.setTextIsSelectable(true);
        score3.setInputType(0);
        score3.setRawInputType(InputType.TYPE_CLASS_TEXT);
        score3.setTextIsSelectable(true);
        score4.setInputType(0);
        score4.setRawInputType(InputType.TYPE_CLASS_TEXT);
        score4.setTextIsSelectable(true);
        score5.setInputType(0);
        score5.setRawInputType(InputType.TYPE_CLASS_TEXT);
        score5.setTextIsSelectable(true);
        score6.setInputType(0);
        score6.setRawInputType(InputType.TYPE_CLASS_TEXT);
        score6.setTextIsSelectable(true);
        score7.setInputType(0);
        score7.setRawInputType(InputType.TYPE_CLASS_TEXT);
        score7.setTextIsSelectable(true);

    }

    private void addListenerOnButton() {

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check input
                if (checkValidation()) {

                    getText();
                    if (dd > 0) {

                        if (scores.size() >= 2) {

                            double finalScore = CalcScore();

                            if (finalScore > 0) {

                                DecimalFormat df = new DecimalFormat("#.00");
                                totalView.setVisibility(View.VISIBLE);
                                totalView.setText(df.format(finalScore));

                            } else {

                                totalView.setVisibility(View.INVISIBLE);
                                totalView.setText("");
                                Toast.makeText(getApplicationContext(),
                                        "Invalid Score, You have to have either 2, 3, 5, or 7 scores entered", Toast.LENGTH_LONG).show();
                            }
                        } else {

                            totalView.setVisibility(View.INVISIBLE);
                            totalView.setText("");
                            Toast.makeText(getApplicationContext(),
                                    "Please enter at least two scores", Toast.LENGTH_LONG).show();
                        }
                    } else {

                        totalView.setVisibility(View.INVISIBLE);
                        totalView.setText("");
                        Toast.makeText(getApplicationContext(),
                                "You have to enter a DD", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Something is wrong with the input", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ClearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToNext();
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("1");
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("2");
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("3");
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("4");
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("5");
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("6");
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("7");
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("8");
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("9");
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText("0");
            }
        });

        btnPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateText(".");
            }
        });
    }

    private void UpdateText(String digit) {

        if (ddEnter.hasFocus()) {
            ddEnter.append(digit);
            return;
        }
        if (score1.hasFocus()) {
            score1.append(digit);
            return;
        }
        if (score2.hasFocus()) {
            score2.append(digit);
            return;
        }
        if (score3.hasFocus()) {
            score3.append(digit);
            return;
        }
        if (score4.hasFocus()) {
            score4.append(digit);
            return;
        }
        if (score5.hasFocus()) {
            score5.append(digit);
            return;
        }
        if (score6.hasFocus()) {
            score6.append(digit);
            return;
        }
        if (score7.hasFocus()) {
            score7.append(digit);
        }
    }

    private void moveToNext() {

        if (ddEnter.hasFocus()) {
            score1.setFocusableInTouchMode(true);
            score1.requestFocus();
            return;
        }
        if (score1.hasFocus()) {
            score2.setFocusableInTouchMode(true);
            score2.requestFocus();
            return;
        }
        if (score2.hasFocus()) {
            score3.setFocusableInTouchMode(true);
            score3.requestFocus();
            return;
        }
        if (score3.hasFocus()) {
            score4.setFocusableInTouchMode(true);
            score4.requestFocus();
            return;
        }
        if (score4.hasFocus()) {
            score5.setFocusableInTouchMode(true);
            score5.requestFocus();
            return;
        }
        if (score5.hasFocus()) {
            score6.setFocusableInTouchMode(true);
            score6.requestFocus();
            return;
        }
        if (score6.hasFocus()) {
            score7.setFocusableInTouchMode(true);
            score7.requestFocus();
            return;
        }
        if (score7.hasFocus()) {
            ddEnter.setFocusableInTouchMode(true);
            ddEnter.requestFocus();
        }
    }

    private void clearText() {

        if (ddEnter.hasFocus()) {
            if (ddEnter.getText().length() > 0) {
                String tempStr = ddEnter.getText().toString();
                ddEnter.setText(tempStr.substring(0, tempStr.length() - 1));
                // we have to do this to put the cursor at the end of the field
                int textlength = ddEnter.getText().length();
                ddEnter.setSelection(textlength, textlength);
            }
        }
        if (score1.hasFocus()) {
            if (score1.getText().length() > 0) {
                String tempStr = score1.getText().toString();
                score1.setText(tempStr.substring(0, tempStr.length() - 1));
                int textlength = score1.getText().length();
                score1.setSelection(textlength, textlength);
            }
        }
        if (score2.hasFocus()) {
            if (score2.getText().length() > 0) {
                String tempStr = score2.getText().toString();
                score2.setText(tempStr.substring(0, tempStr.length() - 1));
                int textlength = score2.getText().length();
                score2.setSelection(textlength, textlength);
            }
        }
        if (score3.hasFocus()) {
            if (score3.getText().length() > 0) {
                String tempStr = score3.getText().toString();
                score3.setText(tempStr.substring(0, tempStr.length() - 1));
                int textlength = score3.getText().length();
                score3.setSelection(textlength, textlength);
            }
        }
        if (score4.hasFocus()) {
            if (score4.getText().length() > 0) {
                String tempStr = score4.getText().toString();
                score4.setText(tempStr.substring(0, tempStr.length() - 1));
                int textlength = score4.getText().length();
                score4.setSelection(textlength, textlength);
            }
        }
        if (score5.hasFocus()) {
            if (score5.getText().length() > 0) {
                String tempStr = score5.getText().toString();
                score5.setText(tempStr.substring(0, tempStr.length() - 1));
                int textlength = score5.getText().length();
                score5.setSelection(textlength, textlength);
            }
        }
        if (score6.hasFocus()) {
            if(score6.getText().length() > 0) {
                String tempStr = score6.getText().toString();
                score6.setText(tempStr.substring(0, tempStr.length() - 1));
                int textlength = score6.getText().length();
                score6.setSelection(textlength, textlength);
            }

        }
        if (score7.hasFocus()) {
            if (score7.getText().length() > 0) {
                String tempStr = score7.getText().toString();
                score7.setText(tempStr.substring(0, tempStr.length() - 1));
                int textlength = score7.getText().length();
                score7.setSelection(textlength, textlength);
            }
        }
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

    private void doATextWatcher() {

        ddEnter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ddEnter.getText().length() == 3) {
                    score1.setFocusableInTouchMode(true);
                    score1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isDD(ddEnter, true);
            }
        });

        score1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (score1.getText().length() == 3) {
                    score2.setFocusableInTouchMode(true);
                    score2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isScore(score1, true);
            }
        });

        score2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (score2.getText().length() == 3) {
                    score3.setFocusableInTouchMode(true);
                    score3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isScore(score2, true);
            }
        });

        score3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (score3.getText().length() == 3) {
                    score4.setFocusableInTouchMode(true);
                    score4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isScore(score3, true);
            }
        });

        score4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (score4.getText().length() == 3) {
                    score5.setFocusableInTouchMode(true);
                    score5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isScore(score4, true);
            }
        });

        score5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (score5.getText().length() == 3) {
                    score6.setFocusableInTouchMode(true);
                    score6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isScore(score5, true);
            }
        });

        score6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (score6.getText().length() == 3) {
                    score7.setFocusableInTouchMode(true);
                    score7.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isScore(score6, true);
            }
        });

        score7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (score7.getText().length() == 3) {
                    ddEnter.setFocusableInTouchMode(true);
                    ddEnter.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Validation.isScore(score7, true);
            }
        });
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.isDD(ddEnter, true)) ret = false;
        if (!Validation.isScore(score1, true)) ret = false;
        if (!Validation.isScore(score2, true)) ret = false;
        if (!Validation.isScore(score3, true)) ret = false;
        if (!Validation.isScore(score4, true)) ret = false;
        if (!Validation.isScore(score5, true)) ret = false;
        if (!Validation.isScore(score6, true)) ret = false;
        if (!Validation.isScore(score7, true)) ret = false;

        return ret;
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

            for (int i = 0; i < theScores.length; i++) {
                finalScore = finalScore + theScores[i];
            }

            finalScore = finalScore * dd;

        } else if (theScores.length == 5) {

            // converts the sorted array to a list and removes the smallest and largest scores
            List<Double> list = new ArrayList<>(Arrays.asList(theScores));
            list.remove(0);
            list.remove(3);

            for (int i = 0; i < list.size(); i++) {
                finalScore = finalScore + list.get(i);
            }

            finalScore = finalScore * dd;

        } else if (theScores.length == 7) {

            // converts the sorted array to a list and removes the smallest and largest scores
            List<Double> list = new ArrayList<>(Arrays.asList(theScores));
            list.remove(0);
            list.remove(0);
            list.remove(4);
            list.remove(3);

            for (int i = 0; i < list.size(); i++) {
                finalScore = finalScore + list.get(i);
            }

            finalScore = finalScore * dd;

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
        btnNext = (Button)findViewById(R.id.buttonNext);
        btnBack = (Button)findViewById(R.id.buttonBack);
        btnOne = (Button)findViewById(R.id.buttonOne);
        btnTwo = (Button)findViewById(R.id.buttonTwo);
        btnThree = (Button)findViewById(R.id.buttonThree);
        btnFour = (Button)findViewById(R.id.buttonFour);
        btnFive = (Button)findViewById(R.id.buttonFive);
        btnSix = (Button)findViewById(R.id.buttonSix);
        btnSeven = (Button)findViewById(R.id.buttonSeven);
        btnEight = (Button)findViewById(R.id.buttonEight);
        btnNine = (Button)findViewById(R.id.buttonNine);
        btnZero = (Button)findViewById(R.id.buttonZero);
        btnPeriod = (Button)findViewById(R.id.buttonPeriod);
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
