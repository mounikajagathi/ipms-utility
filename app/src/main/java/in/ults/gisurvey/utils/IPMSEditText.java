package in.ults.gisurvey.utils;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputEditText;

public class IPMSEditText extends TextInputEditText {
    public IPMSEditText(Context context) {
        super(context);
        init();
    }

    public IPMSEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IPMSEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        this.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

    }
}
