package cn.memoryzy.deepSeek.enums;

import cn.memoryzy.deepSeek.constant.DeepSeekAssistantPlugin;
import cn.memoryzy.deepSeek.constant.UrlConstant;

import java.util.Objects;

/**
 * @author Memory
 * @since 2025/2/13
 */
public enum Urls {

    ALIYUN_BAILIAN(DeepSeekAssistantPlugin.PLUGIN_ID_NAME + ".#ALIYUN_BAILIAN", UrlConstant.ALIYUN_BAILIAN_LINK),

    /**
     * 分享链接（插件市场）
     */
    SHARE(DeepSeekAssistantPlugin.PLUGIN_ID_NAME + ".#SHARE", UrlConstant.MARKETPLACE_REVIEWS_LINK),

    /**
     * 邮件链接
     */
    MAIL(DeepSeekAssistantPlugin.PLUGIN_ID_NAME + ".#MAIL", "mailto:" + UrlConstant.EMAIL_LINK),

    ;

    private final String id;
    private final String url;

    Urls(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public static String of(String id) {
        for (Urls value : values()) {
            if (Objects.equals(value.id, id)) {
                return value.url;
            }
        }

        return null;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}
