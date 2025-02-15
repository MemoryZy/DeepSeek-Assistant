package cn.memoryzy.deepSeek.util;

import com.intellij.openapi.util.text.HtmlChunk;

/**
 * @author Memory
 * @since 2025/2/14
 */
public class HtmlUtil {

    public static String wrapHtml(String text) {
        return HtmlChunk.raw(text)
                .wrapWith(HtmlChunk.body())
                .wrapWith(HtmlChunk.html())
                .toString();
    }

    public static String wrapBoldHtml(String text) {
        return HtmlChunk.raw(text)
                .bold()
                .wrapWith(HtmlChunk.body())
                .wrapWith(HtmlChunk.html())
                .toString();
    }

}
