package cn.memoryzy.deepSeek.configurable;

import cn.memoryzy.deepSeek.ui.MainConfigurableComponentProvider;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Memory
 * @since 2025/2/13
 */
public class DeepSeekAssistantMainConfigurable implements Configurable {

    private MainConfigurableComponentProvider componentProvider;

    @Override
    public String getDisplayName() {
        return "DeepSeek Assistant";
    }

    @Override
    public @Nullable JComponent createComponent() {
        if (componentProvider == null) componentProvider = new MainConfigurableComponentProvider();
        return componentProvider.createComponent();
    }

    @Override
    public void reset() {
        if (componentProvider != null) componentProvider.reset();
    }

    @Override
    public boolean isModified() {
        return componentProvider != null && componentProvider.isModified();
    }

    @Override
    public void apply() {
        if (componentProvider != null) componentProvider.apply();
    }

    @Override
    public void disposeUIResources() {
        componentProvider = null;
    }
}
