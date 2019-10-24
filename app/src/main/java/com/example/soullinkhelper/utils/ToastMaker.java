package com.example.soullinkhelper.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastMaker {

    public static void makeToast(Context ctx, String content, int length){
        Toast.makeText(ctx, content, length).show();
    }
}
