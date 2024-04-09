package ro.pub.cs.systems.eim.practicaltest01var03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    Button buttonPlus, buttonMinus, buttonNavigateToSecondaryActivity;
    EditText editText1, editText2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonNavigateToSecondaryActivity = findViewById(R.id.buttonSecondaryActivity);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        editText1.setText("0");
        editText2.setText("0");
        textView.setText("");

        buttonPlus.setOnClickListener(v -> {
            Integer int1, int2;
            try {
                int1 = Integer.parseInt(editText1.getText().toString());
                int2 = Integer.parseInt(editText2.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT).show();
                return;
            }
            String result = int1 + " + " + int2 + " = " + (int1 + int2);
            textView.setText(result);
        });

        buttonMinus.setOnClickListener(v -> {
            Integer int1, int2;
            try {
                int1 = Integer.parseInt(editText1.getText().toString());
                int2 = Integer.parseInt(editText2.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT).show();
                return;
            }
            String result = int1 + " - " + int2 + " = " + (int1 - int2);
            textView.setText(result);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constants.EDIT_TEXT_1, editText1.getText().toString());
        outState.putString(Constants.EDIT_TEXT_2, editText2.getText().toString());
        outState.putString(Constants.TEXT_VIEW, textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.EDIT_TEXT_1)) {
            editText1.setText(savedInstanceState.getString(Constants.EDIT_TEXT_1));
        } else {
            editText1.setText("0");
        }

        if (savedInstanceState.containsKey(Constants.EDIT_TEXT_2)) {
            editText2.setText(savedInstanceState.getString(Constants.EDIT_TEXT_2));
        } else {
            editText2.setText("0");
        }

        if (savedInstanceState.containsKey(Constants.TEXT_VIEW)) {
            textView.setText(savedInstanceState.getString(Constants.TEXT_VIEW));
        } else {
            textView.setText("");
        }
    }
}