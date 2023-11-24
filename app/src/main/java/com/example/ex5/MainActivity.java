package com.example.ex5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AlertDialog.Builder adb;
    LinearLayout activity_dialog;
    EditText first, diff;
    Switch seq;
    String[] items = new String[20];
    int firstItem, difference;
    ListView list;
    TextView x, d, n, sn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_dialog, null);
        first = activity_dialog.findViewById(R.id.first);
        diff = activity_dialog.findViewById(R.id.diff);
        seq = activity_dialog.findViewById(R.id.seq);
        list = findViewById(R.id.list);
        x = findViewById(R.id.x);
        d = findViewById(R.id.d);
        n = findViewById(R.id.n);
        sn = findViewById(R.id.sn);

        reset(items);

        list.setOnItemClickListener(this);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        adb = new AlertDialog.Builder(this);

        adb.setView(activity_dialog);
        adb.setTitle("Data");
        adb.setMessage("Enter the sequence's data: ");
        adb.setPositiveButton("Enter", clickListener);
        adb.setNeutralButton("Cancel", clickListener);
        adb.setNegativeButton("Reset", clickListener);

        adb.show();
    }

    DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                String str1 = first.getText().toString();
                firstItem = Integer.parseInt(str1);
                x.setText("x1 = " + str1);
                items[0] = String.valueOf(firstItem);
                String str2 = diff.getText().toString();
                difference = Integer.parseInt(str2);
                d.setText("d = " + str2);
                if (!(seq.isChecked()))
                    for (int i = 1; i < items.length; i++)
                        items[i] = String.valueOf(firstItem + i * difference);
                else
                    for (int i = 1; i < items.length; i++)
                        items[i] = String.valueOf(( firstItem * (int) Math.pow(difference, i)));
                ArrayAdapter<String> adp = new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items);
                list.setAdapter(adp);
            }
            if (which == DialogInterface.BUTTON_NEUTRAL)
                dialog.cancel();
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                firstItem = 0;
                difference = 0;
                x.setText("");
                d.setText("");
                n.setText("");
                sn.setText("");
                reset(items);
                ArrayAdapter<String> adp = new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items);
                list.setAdapter(adp);
            }
        }
    };

    public void onClick(View view) {
        activity_dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_dialog, null);
        first = activity_dialog.findViewById(R.id.first);
        diff = activity_dialog.findViewById(R.id.diff);
        seq = activity_dialog.findViewById(R.id.seq);

        adb = new AlertDialog.Builder(this);

        adb.setView(activity_dialog);
        adb.setTitle("Data");
        adb.setMessage("Enter the sequence's data: ");
        adb.setPositiveButton("Enter", clickListener);
        adb.setNeutralButton("Cancel", clickListener);
        adb.setNegativeButton("Reset", clickListener);

        adb.show();
    }

    public void reset(String[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = String.valueOf(0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        n.setText("n = " + (position + 1));
        int sum = 0;
        for (int i = 0; i <= position; i++)
            sum += Integer.parseInt(items[i]);
        sn.setText("Sn = " + sum);
    }
}