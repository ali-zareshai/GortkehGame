package CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import Utils.FormatHelper;

public class CustomButton extends Button {
    public CustomButton(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        Typeface type2 = Typeface.createFromAsset(context.getAssets(),"fonts/Caramel-Color.otf");
        this.setTypeface(type2);

    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(FormatHelper.toPersianNumber((String) text), type);
    }
}