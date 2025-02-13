package cn.memoryzy.deepSeek.ui.dialog;

import cn.memoryzy.deepSeek.bundle.DeepSeekAssistantBundle;
import cn.memoryzy.deepSeek.enums.Models;
import cn.memoryzy.deepSeek.enums.Urls;
import cn.memoryzy.deepSeek.persistent.PasswordHandler;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.ActionLink;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.util.ui.JBUI;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Memory
 * @since 2025/2/13
 */
public class ApiKeyConfigureDialog extends DialogWrapper {
    private JPanel rootPanel;
    private JBLabel brandIcon;
    private JBLabel apiKeyName;
    private JBPasswordField apiKeyField;
    private ActionLink registerLink;
    private final Models model;

    public static final String PASSWORD_KEY = "fe89b11ab9544352a8511a52d719374b";

    public ApiKeyConfigureDialog(Models model) {
        super((Project) null);
        this.model = model;
        setTitle(DeepSeekAssistantBundle.messageOnSystem(model.getTitleKey()));
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return rootPanel;
    }

    @Override
    protected void init() {
        brandIcon.setIcon(model.getBrandIcon());
        apiKeyName.setText(DeepSeekAssistantBundle.messageOnSystem("dialog.api.key.text"));
        registerLink.setText(DeepSeekAssistantBundle.messageOnSystem("dialog.register.link.text"));
        registerLink.setExternalLinkIcon();
        registerLink.addActionListener(e -> BrowserUtil.browse(model.getUrl()));
        rootPanel.setPreferredSize(JBUI.size(360, 140));
        // 加载初始密码
        String password = PasswordHandler.getPassword(PASSWORD_KEY);
        if (StringUtils.isNotBlank(password)) {
            apiKeyField.setText(password);
        }

        super.init();
    }

    @Override
    protected void doOKAction() {
        String password = String.valueOf(apiKeyField.getPassword());
        PasswordHandler.setPassword(PASSWORD_KEY, null, password);
        super.doOKAction();
    }

    @Override
    public void show() {
        ApplicationManager.getApplication().invokeLater(super::show);
    }

    @Override
    protected @NonNls @Nullable String getHelpId() {
        return Urls.ALIYUN_BAILIAN.getId();
    }

}
