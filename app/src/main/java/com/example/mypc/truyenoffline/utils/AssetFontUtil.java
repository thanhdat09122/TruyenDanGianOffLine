package com.example.mypc.truyenoffline.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.example.mypc.truyenoffline.common.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssetFontUtil {

    public static List<String> getFontsNameAssets(Context context) {
        List<String> fontNames = new ArrayList<>();
        String[] listFile;
        AssetManager assetManager = context.getAssets();
        try {
            listFile = assetManager.list(Constant.PATH_FONTS);
            Collections.addAll(fontNames, listFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontNames;
    }

    public static Typeface getTyface(Context context, String fontName) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), Constant.PATH_FONTS +"/" + fontName);
        return typeface;
    }
}
