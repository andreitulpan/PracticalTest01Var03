package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    Button buttonCorrect, buttonIncorrect;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        buttonCorrect = findViewById(R.id.buttonCorrect);
        buttonIncorrect = findViewById(R.id.buttonIncorrect);
        textView = findViewById(R.id.textViewResult);

        Intent intent = getIntent();
        String result = intent.getStringExtra(Constants.OPERATION);
        textView.setText("Result: " + result);

        buttonCorrect.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent());
            finish();
        });

        buttonIncorrect.setOnClickListener(v -> {
            setResult(RESULT_CANCELED, new Intent());
            finish();
        });
    }
}