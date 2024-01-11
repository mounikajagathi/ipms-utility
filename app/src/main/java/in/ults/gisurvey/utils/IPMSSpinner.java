package in.ults.gisurvey.utils;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import in.ults.gisurvey.R;

public class IPMSSpinner extends AppCompatAutoCompleteTextView {
    public IPMSSpinner(Context context) {
        super(context);
        init(context);
    }

    public IPMSSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IPMSSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.setFocusable(false);
        this.setCursorVisible(false);
        this.setPadding(context.getResources().getDimensionPixelOffset(R.dimen.std_space_normal),0,context.getResources().getDimensionPixelOffset(R.dimen.std_space_normal),0);
        this.setOnClickListener(v -> ((AppCompatAutoCompleteTextView) v).showDropDown());
        this.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

    }

}
