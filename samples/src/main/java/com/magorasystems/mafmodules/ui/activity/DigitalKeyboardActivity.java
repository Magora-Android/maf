package com.magorasystems.mafmodules.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.magorasystems.digitalkeyboardwidget.DefaultItemsGenerator;
import com.magorasystems.digitalkeyboardwidget.DefaultKey;
import com.magorasystems.digitalkeyboardwidget.DefaultKeyboardAdapter;
import com.magorasystems.digitalkeyboardwidget.DigitalKeyboard;
import com.magorasystems.mafmodules.R;
import com.magorasystems.mafmodules.common.utils.SchedulersUtils;
import com.magorasystems.mafmodules.common.utils.rx.ApplicationScheduler;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class DigitalKeyboardActivity extends AppCompatActivity {

    private DigitalKeyboard keyboard;
    private EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_keyboard);
        keyboard = (DigitalKeyboard) findViewById(R.id.keyboard);
        ((DefaultKeyboardAdapter) keyboard.getAdapter()).setUseRipple(true);
        textInput = (EditText) findViewById(R.id.text_input);
        /*
            OR:
            keyboard.setAdapter(...Some custom adapter extend BaseAdapter...)
         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        keyboard.keyClicks()
                .compose(SchedulersUtils.applySchedulers(new ApplicationScheduler()))
                .subscribe(this::onKeyClick);
    }

    @Override
    protected void onStop() {
        super.onStop();
        keyboard.unsubscribe();
    }

    private void onKeyClick(DefaultKey key) {
        switch (key.getActualKeyCode()) {
            case DefaultItemsGenerator.KEY_CHANGE_LAYOUT:
                keyboard.swapLayout();
                break;

            case DefaultItemsGenerator.KEY_BACKSPACE:
                CharSequence text = textInput.getText();
                if (text != null && text.length() > 0) {
                    textInput.setText(text.subSequence(0, text.length() - 1));
                }
                Toast.makeText(this, "Click BACKSPACE", Toast.LENGTH_SHORT).show();
                break;

            case DefaultItemsGenerator.KEY_PAUSE:
                Toast.makeText(this, "Click PAUSE", Toast.LENGTH_SHORT).show();
                break;

            case DefaultItemsGenerator.KEY_WAIT:
                Toast.makeText(this, "Click WAIT", Toast.LENGTH_SHORT).show();
                break;

            case DefaultItemsGenerator.KEY_STAR:
                Toast.makeText(this, "Click *", Toast.LENGTH_SHORT).show();
                break;

            case DefaultItemsGenerator.KEY_DASH:
                Toast.makeText(this, "Click #", Toast.LENGTH_SHORT).show();
                break;

            case DefaultItemsGenerator.KEY_PLUS:
                Toast.makeText(this, "Click +", Toast.LENGTH_SHORT).show();
                break;

            default:
                textInput.append(key.getActualSymbol());
                Toast.makeText(this, "Click " + key.getActualSymbol(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
