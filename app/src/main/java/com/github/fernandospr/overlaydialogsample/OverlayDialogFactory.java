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
    public static OverlayDialog buildInfoDialog(@NonNull ViewGroup root,
                                                @Nullable CharSequence text,
                                                @NonNull MessageDialogStyle style) {
        return buildDialog(root, text, style, R.layout.message_info);
    }

    @NonNull
    public static OverlayDialog buildErrorDialog(@NonNull ViewGroup root,
                                                 @Nullable CharSequence text,
                                                 @NonNull MessageDialogStyle style) {
        return buildDialog(root, text, style, R.layout.message_error);
    }

    @NonNull
    public static OverlayDialog buildWarnDialog(@NonNull ViewGroup root,
                                                @Nullable CharSequence text,
                                                @NonNull MessageDialogStyle style) {
        return buildDialog(root, text, style, R.layout.message_warn);
    }

    @NonNull
    public static OverlayDialog buildReminderDialog(@NonNull ViewGroup root,
                                                    @Nullable CharSequence text,
                                                    @NonNull MessageDialogStyle style) {
        return buildDialog(root, text, style, R.layout.message_reminder);
    }

    @NonNull
    private static OverlayDialog buildDialog(@NonNull ViewGroup root,
                                             @Nullable CharSequence text,
                                             @NonNull MessageDialogStyle style,
                                             @LayoutRes int layoutResID) {
        View view = LayoutInflater.from(root.getContext()).inflate(layoutResID, root, false);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(text);
        OverlayDialog dialog = new OverlayDialog(root.getContext(), style.value);
        dialog.setAutoDismissDelayMillis(5000);
        dialog.setCancelable(true);
        dialog.setContentView(view);
        return dialog;
    }

    enum MessageDialogStyle {
        DEFAULT(0),
        FROM_TOP(R.style.TopMessageDialog),
        FROM_LEFT(R.style.LeftToRightMessageDialog);

        private final int value;

        MessageDialogStyle(int value) {
            this.value = value;
        }
    }
}