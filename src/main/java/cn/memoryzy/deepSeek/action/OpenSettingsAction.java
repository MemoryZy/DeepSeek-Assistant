package cn.memoryzy.deepSeek.action;

import cn.memoryzy.deepSeek.bundle.DeepSeekAssistantBundle;
import cn.memoryzy.deepSeek.constant.DeepSeekAssistantPlugin;
import com.intellij.ide.HelpTooltip;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ex.CustomComponentAction;
import com.intellij.openapi.actionSystem.impl.ActionButton;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.util.ui.JBUI;
import icons.DeepSeekAssistantIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Memory
 * @since 2025/2/13
 */
public class OpenSettingsAction extends DumbAwareAction implements CustomComponentAction {

    public OpenSettingsAction() {
        super();
        setEnabledInModalContext(true);
        Presentation presentation = getTemplatePresentation();
        presentation.setText(DeepSeekAssistantBundle.messageOnSystem("action.open.settings.text"));
        presentation.setDescription(DeepSeekAssistantBundle.messageOnSystem("action.open.settings.description"));
        presentation.setIcon(DeepSeekAssistantIcons.ToolWindow.SETTINGS);
    }

    @Override
    public @NotNull JComponent createCustomComponent(@NotNull Presentation presentation, @NotNull String place) {
        ActionButton button = new ActionButton(this, presentation, place, ActionToolbar.DEFAULT_MINIMUM_BUTTON_SIZE) {
            @Override
            protected void updateToolTipText() {
                HelpTooltip.dispose(this);
                // noinspection DialogTitleCapitalization
                new HelpTooltip()
                        .setTitle(getTemplatePresentation().getText())
                        .setDescription(DeepSeekAssistantBundle.messageOnSystem("tooltip.open.settings.description"))
                        .installOn(this);
            }
        };

        button.setBorder(JBUI.Borders.empty(1, 2));
        return button;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ShowSettingsUtil.getInstance().showSettingsDialog(e.getProject(), DeepSeekAssistantPlugin.PLUGIN_NAME);
    }
}
