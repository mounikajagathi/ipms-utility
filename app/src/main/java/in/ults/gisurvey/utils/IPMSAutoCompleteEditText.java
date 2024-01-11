package in.ults.gisurvey.utils;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

import com.google.android.material.textfield.TextInputEditText;

public class IPMSAutoCompleteEditText extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {
    public IPMSAutoCompleteEditText(Context context) {
        super(context);
        init();
    }

    public IPMSAutoCompleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IPMSAutoCompleteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        this.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

    }
}
