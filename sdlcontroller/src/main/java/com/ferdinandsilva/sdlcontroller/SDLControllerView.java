package com.ferdinandsilva.sdlcontroller;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.widget.ImageView;

public class SDLControllerView extends ConstraintLayout {
    private ViewGroup vg;
    public SDLControllerView(@NonNull Context context, ViewGroup vg) {
        super(context);
        inflate(context, R.layout.gamepad, this);
        this.vg = vg;
        this.vg.addView(this);

        ImageView leftButton = (ImageView) findViewById(R.id.left_button);
        ImageView rightButton = (ImageView) findViewById(R.id.right_button);
        ImageView upButton = (ImageView) findViewById(R.id.up_button);
        ImageView downButton = (ImageView) findViewById(R.id.down_button);
        ImageView aButton = (ImageView) findViewById(R.id.a_button);
        ImageView bButton = (ImageView) findViewById(R.id.b_button);
        ImageView cButton = (ImageView) findViewById(R.id.c_button);

        leftButton.setOnTouchListener(new ControllerTouchListener(KeyEvent.KEYCODE_DPAD_LEFT));
        rightButton.setOnTouchListener(new ControllerTouchListener(KeyEvent.KEYCODE_DPAD_RIGHT));
        upButton.setOnTouchListener(new ControllerTouchListener(KeyEvent.KEYCODE_DPAD_UP));
        downButton.setOnTouchListener(new ControllerTouchListener(KeyEvent.KEYCODE_DPAD_DOWN));
        aButton.setOnTouchListener(new ControllerTouchListener(KeyEvent.KEYCODE_SHIFT_LEFT));
        bButton.setOnTouchListener(new ControllerTouchListener(KeyEvent.KEYCODE_SPACE));
        cButton.setOnTouchListener(new ControllerTouchListener(KeyEvent.KEYCODE_ENTER));
    }

    private boolean sendKey(int action, int keycode) {
        BaseInputConnection bic = new BaseInputConnection(this.vg, true);
        return bic.sendKeyEvent(new KeyEvent(action, keycode));
    }

    class ControllerTouchListener implements OnTouchListener {

        private int thisKeyCode;

        public ControllerTouchListener(int keyCode) {
            this.thisKeyCode = keyCode;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            sendKey(motionEvent.getAction(), this.thisKeyCode);
            return true;
        }
    }
}
