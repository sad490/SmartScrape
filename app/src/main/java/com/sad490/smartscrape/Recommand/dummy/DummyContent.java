package com.sad490.smartscrape.Recommand.dummy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.sad490.smartscrape.MainActivity;
import com.sad490.smartscrape.R;
import com.sad490.smartscrape.Recommand.RecommandFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static int COUNT = 25;

    private static Context context;
    public final static Integer[] imageResIds = new Integer[] {
            R.drawable.com_facebook_button_send_icon_blue,
            R.drawable.com_facebook_button_like_background,
            R.drawable.com_facebook_auth_dialog_header_background,
    };

    public static void setContext( Context _context ){
        context = _context;
    }

    static {
        // Add some sample items.
        // TODO : How to get bitmap from internet ......
        /// Bitmap newb = Bitmap.createBitmap( 300, 300, Bitmap.Config.ARGB_8888 );
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i, "Sample"));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String content) {
        return new DummyItem(String.valueOf(position), content, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
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
