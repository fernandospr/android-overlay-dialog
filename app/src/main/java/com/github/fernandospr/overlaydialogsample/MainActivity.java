package com.github.fernandospr.overlaydialogsample;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Dialog mDialog;
    private CheckBox mHtmlCheckBox;
    private EditText mInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputEditText = (EditText) findViewById(R.id.etInput);
        mHtmlCheckBox = (CheckBox) findViewById(R.id.cbHtml);

        findViewById(R.id.bInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = OverlayDialogFactory.buildInfoDialog(findViewById(android.R.id.content), getInput());
                mDialog.show();
            }
        });
        findViewById(R.id.bWarn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = OverlayDialogFactory.buildWarnDialog(findViewById(android.R.id.content), getInput());
                mDialog.show();
            }
        });
        findViewById(R.id.bError).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = OverlayDialogFactory.buildErrorDialog(findViewById(android.R.id.content), getInput());
                mDialog.show();
            }
        });
        findViewById(R.id.bReminder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = OverlayDialogFactory.buildReminderDialog(findViewById(android.R.id.content), getInput());
                mDialog.show();
            }
        });
    }

    private CharSequence getInput() {
        return mHtmlCheckBox.isChecked() ? Html.fromHtml(mInputEditText.getText().toString()) : mInputEditText.getText();
    }

    @Override
    protected void onDestroy() {
        safeDismiss();
        super.onDestroy();
    }

    private void safeDismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
