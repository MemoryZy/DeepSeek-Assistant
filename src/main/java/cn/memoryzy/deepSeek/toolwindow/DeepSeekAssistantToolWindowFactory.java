package cn.memoryzy.deepSeek.toolwindow;

import cn.memoryzy.deepSeek.action.DonateAction;
import cn.memoryzy.deepSeek.action.NewTabAction;
import cn.memoryzy.deepSeek.action.OpenSettingsAction;
import cn.memoryzy.deepSeek.constant.DeepSeekAssistantPlugin;
import cn.memoryzy.deepSeek.ui.ChatApplication2;
import cn.memoryzy.deepSeek.ui.TestBallon;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ex.ToolWindowEx;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Memory
 * @since 2025/2/13
 */
public class DeepSeekAssistantToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void init(@NotNull ToolWindow toolWindow) {
        toolWindow.setTitle(DeepSeekAssistantPlugin.PLUGIN_NAME);
        toolWindow.setStripeTitle(DeepSeekAssistantPlugin.PLUGIN_NAME);
        // toolWindow.setIcon();
        // toolWindow.setHelpId();
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        ContentManager contentManager = toolWindow.getContentManager();
        ToolWindowEx toolWindowEx = (ToolWindowEx) toolWindow;

        AnAction[] tabActions = {new NewTabAction(contentFactory, toolWindowEx)};
        List<AnAction> titleActions = List.of(new OpenSettingsAction(), new DonateAction());
        toolWindowEx.setTitleActions(titleActions);
        toolWindowEx.setTabActions(tabActions);

        Content content = contentFactory.createContent(new TestBallon(), "Chat", false);
        content.setCloseable(false);
        // content.setDisposer(window);
        contentManager.addContent(content, 0);



    }
}
