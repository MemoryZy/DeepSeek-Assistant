package cn.memoryzy.deepSeek.provider;

import cn.memoryzy.deepSeek.enums.Urls;
import com.intellij.openapi.help.WebHelpProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Memory
 * @since 2025/2/13
 */
public class HelpProvider extends WebHelpProvider {
    @Override
    public @Nullable String getHelpPageUrl(@NotNull String helpTopicId) {
        return Urls.of(helpTopicId);
    }
}
