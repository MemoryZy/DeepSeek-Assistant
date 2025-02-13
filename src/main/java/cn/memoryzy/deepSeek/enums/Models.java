package cn.memoryzy.deepSeek.enums;

import cn.memoryzy.deepSeek.bundle.DeepSeekAssistantBundle;
import icons.DeepSeekAssistantIcons;

import javax.swing.*;

/**
 * @author Memory
 * @since 2025/2/13
 */
public enum Models {

    ALIYUN_BAILIAN_DEEPSEEK_R1("model.aliYun.baiLian.deepSeek.r1", "dialog.aliYun.baiLian.title", DeepSeekAssistantIcons.ALIYUN_BAILIAN, Urls.ALIYUN_BAILIAN.getUrl()),
    ALIYUN_BAILIAN_DEEPSEEK_V3("model.aliYun.baiLian.deepSeek.v3", "dialog.aliYun.baiLian.title", DeepSeekAssistantIcons.ALIYUN_BAILIAN, Urls.ALIYUN_BAILIAN.getUrl()),

    ;

    private final String key;
    private final String titleKey;
    private final Icon brandIcon;
    private final String url;

    Models(String key, String titleKey, Icon brandIcon, String url) {
        this.key = key;
        this.titleKey = titleKey;
        this.brandIcon = brandIcon;
        this.url = url;
    }

    @Override
    public String toString() {
        return DeepSeekAssistantBundle.messageOnSystem(key);
    }

    public String getTitleKey() {
        return titleKey;
    }

    public Icon getBrandIcon() {
        return brandIcon;
    }

    public String getUrl() {
        return url;
    }
}
