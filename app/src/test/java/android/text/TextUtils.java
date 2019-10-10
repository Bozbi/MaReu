package android.text;

import androidx.annotation.NonNull;

import java.util.Iterator;


/**
 * Creates by Boris SBIZZERA on 10/10/2019.
 */
public class TextUtils {

    private static String[] EMPTY_STRING_ARRAY = new String[]{};

    public static String join(@NonNull CharSequence delimiter, @NonNull Iterable tokens) {
        final Iterator<?> it = tokens.iterator();
        if (!it.hasNext()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(it.next());
        while (it.hasNext()) {
            sb.append(delimiter);
            sb.append(it.next());
        }
        return sb.toString();
    }

    public static String[] split(String text, String expression) {
        if (text.length() == 0) {
            return EMPTY_STRING_ARRAY;
        } else {
            return text.split(expression, -1);
        }
    }


}
