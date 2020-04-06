package com.biginsect.signinmanagement.utils;

import android.text.TextUtils;

import androidx.annotation.Nullable;

/**
 * @author biginsect
 * @date 2020/4/4
 */
public final class StringUtils {

    private StringUtils(){

    }

    public static boolean isBlank(@Nullable String str) {
        return TextUtils.isEmpty(str) || str.trim().equals("");
    }
}
