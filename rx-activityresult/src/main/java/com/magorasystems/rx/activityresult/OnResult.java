package com.magorasystems.rx.activityresult;

import android.content.Intent;

import java.io.Serializable;

interface OnResult extends Serializable {
    void response(int resultCode, Intent data);
}
