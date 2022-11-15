package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCopy;
    private EditText editTextPaste;

    private Button buttonCopy;
    private Button buttonPaste;

    private ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        this.clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);

        this.editTextCopy = (EditText) findViewById(R.id.editText_copy);
        this.editTextPaste = (EditText) findViewById(R.id.editText_paste);

        this.buttonCopy = (Button) findViewById(R.id.button_copy);
        this.buttonPaste = (Button) findViewById(R.id.button_paste);

        this.buttonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCopy();
            }
        });
        this.buttonPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPaste();
            }
        });
    }

    // User click on "Copy" button.
    private void doCopy() {
        String txtCopy = this.editTextCopy.getText().toString();
        ClipData clipData = ClipData.newPlainText("text", txtCopy);

        // Copy ClipData to Clipboard.
        this.clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(getApplicationContext(), "Data Copied to Clipboard", Toast.LENGTH_SHORT).show();
    }

    // User click on "Poste" button.
    private void doPaste() {
        // Get ClipData from Clipboard.
        ClipData primaryClipData = this.clipboardManager.getPrimaryClip();
        ClipData.Item item = primaryClipData.getItemAt(0);

        String txtPaste = item.getText().toString();

        this.editTextPaste.setText(txtPaste);

        Toast.makeText(getApplicationContext(), "Data Pasted from Clipboard", Toast.LENGTH_SHORT).show();
    }
}

