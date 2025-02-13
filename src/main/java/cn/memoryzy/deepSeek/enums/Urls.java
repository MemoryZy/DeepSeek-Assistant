package cn.memoryzy.deepSeek.enums;

import cn.memoryzy.deepSeek.constant.DeepSeekAssistantPlugin;

import java.util.Objects;

/**
 * @author Memory
 * @since 2025/2/13
 */
public enum Urls {

    ALIYUN_BAILIAN(DeepSeekAssistantPlugin.PLUGIN_ID_NAME + ".#ALIYUN_BAILIAN", "https://bailian.console.aliyun.com/#/home");

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
