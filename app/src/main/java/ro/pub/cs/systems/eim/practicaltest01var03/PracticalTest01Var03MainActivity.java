package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    Button buttonPlus, buttonMinus, buttonNavigateToSecondaryActivity;
    EditText editText1, editText2;
    TextView textView;

    boolean serviceStatus = false;

    private ActivityResultLauncher<Intent> activityResultLauncher;
    private ServiceBroadcastReceive messageBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);
        messageBroadcastReceiver = new ServiceBroadcastReceive();

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
            verifyAndStartForegroundService();
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
            verifyAndStartForegroundService();
            int int1, int2;
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

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "The activity returned with correct result!", Toast.LENGTH_LONG).show();
            } else if (result.getResultCode() == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "The activity returned with incorrect result!", Toast.LENGTH_LONG).show();
            }
        });

        buttonNavigateToSecondaryActivity.setOnClickListener(v -> {
            String operation = textView.getText().toString();
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
            intent.putExtra(Constants.OPERATION, operation);
            activityResultLauncher.launch(intent);
        });
    }

    private void verifyAndStartForegroundService() {
        int num1 = Integer.parseInt(editText1.getText().toString());
        int num2 = Integer.parseInt(editText2.getText().toString());

        if (!serviceStatus) { // && !serviceStatus
            serviceStatus = true;
            Intent intent = new Intent(this, PracticalTest01Var03Service.class);
            intent.putExtra(Constants.NUM1, num1);
            intent.putExtra(Constants.NUM2, num2);
            startForegroundService(intent);
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_STRING);
        registerReceiver(messageBroadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }
}