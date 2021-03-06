package com.github.fernandospr.overlaydialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class OverlayDialog extends Dialog {
    private long mAutoDismissDelayMillis;
    private Handler mAutoDismissHandler;
    private final Runnable mAutoDismissRunnable = new Runnable() {
        @Override
        public void run() {
            dismiss();
        }
    };

    public OverlayDialog(Context context) {
        super(context);
        init();
    }

    public OverlayDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected OverlayDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        mAutoDismissDelayMillis = -1;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.TOP | Gravity.CENTER;
        }
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setupLayout();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupLayout();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setupLayout();
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
        setupLayout();
    }

    private void setupLayout() {
        Window window = getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    public void setAutoDismissDelayMillis(long delayMillis) {
        mAutoDismissDelayMillis = delayMillis;
    }

    @Override
    public void show() {
        super.show();

        if (mAutoDismissDelayMillis > -1) {
            scheduleAutoDismiss();
        }
    }

    private void scheduleAutoDismiss() {
        mAutoDismissHandler = new Handler();
        mAutoDismissHandler.postDelayed(mAutoDismissRunnable, mAutoDismissDelayMillis);
    }

    @Override
    public void setOnDismissListener(final OnDismissListener listener) {
        super.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (listener != null) {
                    listener.onDismiss(dialogInterface);
                }
                if (mAutoDismissHandler != null) {
                    mAutoDismissHandler.removeCallbacks(mAutoDismissRunnable);
                }
            }
        });
    }
}
