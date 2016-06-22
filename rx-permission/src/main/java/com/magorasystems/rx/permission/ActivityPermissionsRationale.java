package com.magorasystems.rx.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author S.A.Bobrischev
 *         Developed by Magora Team (magora-systems.com). 2016.
 */
@SuppressWarnings("ConstantConditions")
public class ActivityPermissionsRationale extends AppCompatActivity {
    private static final String EXTRA_CONTENT = "EXTRA_CONTENT";

    public static Intent createIntent(Context context, PermissionInfo data) {
        Intent intent = new Intent(context, ActivityPermissionsRationale.class);
        intent.putExtra(EXTRA_CONTENT, data);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_rationale);
        TextView textTitle = (TextView) findViewById(R.id.rationale_title);
        TextView textDescription = (TextView) findViewById(R.id.rationale_message);
        Button buttonDecline = (Button) findViewById(R.id.button_decline);
        Button buttonGrant = (Button) findViewById(R.id.button_grant);

        PermissionInfo data = getIntent().getParcelableExtra(EXTRA_CONTENT);
        if (data != null) {
            textTitle.setText(data.rationaleTitle);
            textDescription.setText(data.rationaleMessage);

            if (!TextUtils.isEmpty(data.rationaleNegativeButton)) {
                buttonDecline.setText(data.rationaleNegativeButton);
            }

            if (!TextUtils.isEmpty(data.rationalePositiveButton)) {
                buttonGrant.setText(data.rationalePositiveButton);
            }
        }

        buttonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        buttonGrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}
