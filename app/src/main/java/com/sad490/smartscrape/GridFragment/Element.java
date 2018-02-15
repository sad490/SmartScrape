package com.sad490.smartscrape.GridFragment;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 2/15/18.
 */

public class Element {

    public static List<Element.ElementItem> getData(String Id) {
        List<ElementItem> ret = new ArrayList<>();

        // todo: Here I sim the data .
        for ( int i = 0; i < 5; ++i ) {
            ret.add(new ElementItem(
                    "" + i,
                    Bitmap.createBitmap( 300, 300, Bitmap.Config.ARGB_8888 ),
                    "" + i));
        }
        return ret;
    }

    public static class ElementItem {
        public final String id;
        public final Bitmap content;
        public final String details;

        public ElementItem(String id, Bitmap content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return "Image";
        }
    }
}
