package com.github.fernandospr.overlaydialogsample;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.fernandospr.overlaydialog.OverlayDialog;

public class OverlayDialogFactory {

    private OverlayDialogFactory() {
        throw new AssertionError(getClass().toString() + " cannot be instantiated.");
    }

    @NonNull
    public static OverlayDialog buildInfoDialog(@NonNull View root, @Nullable CharSequence text) {
        return buildDialog(root, text, R.layout.message_info);
    }

    @NonNull
    public static OverlayDialog buildErrorDialog(@NonNull View root, @Nullable CharSequence text) {
        return buildDialog(root, text, R.layout.message_error);
    }

    @NonNull
    public static OverlayDialog buildWarnDialog(@NonNull View root, @Nullable CharSequence text) {
        return buildDialog(root, text, R.layout.message_error);
    }

    @NonNull
    public static OverlayDialog buildReminderDialog(@NonNull View root, @Nullable CharSequence text) {
        return buildDialog(root, text, R.layout.message_reminder);
    }

    @NonNull
    private static OverlayDialog buildDialog(@NonNull View root, @Nullable CharSequence text, @LayoutRes int layoutResID) {
        View view = LayoutInflater.from(root.getContext()).inflate(layoutResID, (ViewGroup) root, false);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(text);
        OverlayDialog dialog = new OverlayDialog(root.getContext(), R.style.TopMessageDialog);
        dialog.setAutoDismissDelayMillis(5000);
        dialog.setCancelable(true);
        dialog.setContentView(view);
        return dialog;
    }

}