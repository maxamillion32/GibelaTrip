package com.gibelatrip.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.digits.sdk.android.DigitsAuthButton;
import com.gibelatrip.AppController;
import com.gibelatrip.R;

public class GibelaTripAuthButton extends DigitsAuthButton {
    public GibelaTripAuthButton(Context context) {
        super(context);
        init();
    }

    public GibelaTripAuthButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    public GibelaTripAuthButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init() {
        if (isInEditMode()){
            return;
        }
        final Drawable phone = getResources().getDrawable(R.drawable.ic_signin_phone);
        phone.setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
        setCompoundDrawablesWithIntrinsicBounds(phone, null, null, null);
        setBackgroundResource(R.drawable.digits_button);
        setTextSize(20);
        setTextColor(getResources().getColor(R.color.green));
        setTypeface(AppController.getInstance().getTypeface());
    }
}