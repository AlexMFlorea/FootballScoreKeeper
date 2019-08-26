package com.alex.footballscorekeeper;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int goalsTeamOne = 0;
    private int goalsTeamTwo = 0;
    private int foulsTeamOne = 0;
    private int foulsTeamTwo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fixEditViews();
    }

    public void onTeamOneGoal(View view){
        updateViewWithValue(R.id.goalsTeam1, ++goalsTeamOne);
    }

    public void onTeamOneCancelGoal(View view){
        if(goalsTeamOne > 0)
            updateViewWithValue(R.id.goalsTeam1, --goalsTeamOne);
        else
            alertMinVal();
    }

    public void onTeamTwoGoal(View view){
        updateViewWithValue(R.id.goalsTeam2, ++goalsTeamTwo);
    }

    public void onTeamTwoCancelGoal(View view){
        if(goalsTeamTwo > 0)
            updateViewWithValue(R.id.goalsTeam2, --goalsTeamTwo);
        else
            alertMinVal();
    }

    public void onTeamOneFoul(View view){
        updateViewWithValue(R.id.foulsTeam1, ++foulsTeamOne);
    }

    public void onTeamOneCancelFoul(View view){
        if(foulsTeamOne > 0)
            updateViewWithValue(R.id.foulsTeam1, --foulsTeamOne);
        else
            alertMinVal();
    }

    public void onTeamTwoFoul(View view){
        updateViewWithValue(R.id.foulsTeam2, ++foulsTeamTwo);
    }

    public void onTeamTwoCancelFoul(View view){
        if(foulsTeamTwo > 0)
            updateViewWithValue(R.id.foulsTeam2, --foulsTeamTwo);
        else
            alertMinVal();
    }

    public void resetAll(View view){
        if(goalsTeamOne == 0 && goalsTeamTwo == 0 && foulsTeamOne == 0 && foulsTeamTwo == 0){
            Toast.makeText(this, "All values zero!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(goalsTeamOne != 0){
            goalsTeamOne = 0;
            updateViewWithValue(R.id.goalsTeam1, goalsTeamOne);
        }
        if(goalsTeamTwo != 0){
            goalsTeamTwo = 0;
            updateViewWithValue(R.id.goalsTeam2, goalsTeamTwo);
        }
        if(foulsTeamOne != 0){
            foulsTeamOne = 0;
            updateViewWithValue(R.id.foulsTeam1, foulsTeamOne);
        }
        if(foulsTeamTwo != 0){
            foulsTeamTwo = 0;
            updateViewWithValue(R.id.foulsTeam2, foulsTeamTwo);
        }
    }

    private void updateViewWithValue(int id, int value){
        final TextView textView = findViewById(id);
        textView.setText(String.valueOf(value));
    }

    private void alertMinVal(){
        Toast.makeText(this, "Min value is 0!", Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void fixEditViews(){
        hideKeyboardWhenUnfocused((EditText) findViewById(R.id.teamOneName));
        hideKeyboardWhenUnfocused((EditText) findViewById(R.id.teamTwoName));
    }

    /**
     * Small trick to make the keyboard disappear when the user clicks away from the edit text
     * Source: https://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext
     */
    private void hideKeyboardWhenUnfocused(EditText editText){
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    hideKeyboard(v);
            }
        });
    }
}
