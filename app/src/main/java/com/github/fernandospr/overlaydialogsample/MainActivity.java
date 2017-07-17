package com.github.fernandospr.overlaydialogsample;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private Dialog mDialog;
    private CheckBox mHtmlCheckBox;
    private EditText mInputEditText;
    private RadioButton mAnimationFromTopRadioButton;
    private RadioButton mAnimationFromLeftRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", getString(R.string.app_name));
        mInputEditText = (EditText) findViewById(R.id.etInput);
        mHtmlCheckBox = (CheckBox) findViewById(R.id.cbHtml);
        mAnimationFromTopRadioButton = (RadioButton) findViewById(R.id.rbAnimationFromTop);
        mAnimationFromLeftRadioButton = (RadioButton) findViewById(R.id.rbAnimationFromLeft);

        findViewById(R.id.bInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = OverlayDialogFactory.buildInfoDialog((ViewGroup) findViewById(android.R.id.content), getInput(), getStyle());
                mDialog.show();
            }
        });
        findViewById(R.id.bWarn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = OverlayDialogFactory.buildWarnDialog((ViewGroup) findViewById(android.R.id.content), getInput(), getStyle());
                mDialog.show();
            }
        });
        findViewById(R.id.bError).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = OverlayDialogFactory.buildErrorDialog((ViewGroup) findViewById(android.R.id.content), getInput(), getStyle());
                mDialog.show();
            }
        });
        findViewById(R.id.bReminder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = OverlayDialogFactory.buildReminderDialog((ViewGroup) findViewById(android.R.id.content), getInput(), getStyle());
                mDialog.show();
            }
        });
    }

    private OverlayDialogFactory.MessageDialogStyle getStyle() {
        if (mAnimationFromLeftRadioButton.isChecked()) {
            return OverlayDialogFactory.MessageDialogStyle.FROM_LEFT;
        }
        if (mAnimationFromTopRadioButton.isChecked()) {
            return OverlayDialogFactory.MessageDialogStyle.FROM_TOP;
        }
        return OverlayDialogFactory.MessageDialogStyle.DEFAULT;
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
