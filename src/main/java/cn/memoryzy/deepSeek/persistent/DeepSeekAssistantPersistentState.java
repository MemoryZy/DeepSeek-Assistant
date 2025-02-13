package cn.memoryzy.deepSeek.persistent;

import cn.memoryzy.deepSeek.enums.Models;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Memory
 * @since 2025/2/13
 */
@State(name = "DeepSeek Assistant Settings", storages = {@Storage(value = "DeepSeekAssistantPersistentState.xml")})
public class DeepSeekAssistantPersistentState implements PersistentStateComponent<DeepSeekAssistantPersistentState> {

    public static DeepSeekAssistantPersistentState getInstance() {
        return ApplicationManager.getApplication().getService(DeepSeekAssistantPersistentState.class);
    }

    /**
     * 模型
     */
    public Models model = Models.ALIYUN_BAILIAN_DEEPSEEK_R1;

    @Override
    public @Nullable DeepSeekAssistantPersistentState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull DeepSeekAssistantPersistentState state) {
        this.model = state.model;
    }
}
