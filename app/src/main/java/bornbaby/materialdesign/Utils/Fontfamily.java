package bornbaby.materialdesign.Utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by madhu on 12-Nov-17.
 */

public class Fontfamily {

    public static Typeface setFontAwesomeIcons(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
    }


    public static DrawableAwesome getDrawableForButton(Context context, int id) {
        return new DrawableAwesome.DrawableAwesomeBuilder(context, id).build();
    }
}
