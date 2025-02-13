package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * @author Memory
 * @since 2025/2/13
 */
public interface DeepSeekAssistantIcons {

    Icon DEEPSEEK = load("/images/deepSeek.png");
    Icon ALIYUN_BAILIAN = load("/images/aliyun_bailian.png");
    Icon DONATE = load("/icons/donate.svg");

    interface ToolWindow {
        Icon SETTINGS = load("/icons/toolwindow/settings.svg");
    }

    static Icon load(String iconPath) {
        return IconLoader.getIcon(iconPath, DeepSeekAssistantIcons.class);
    }

}
