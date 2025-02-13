package cn.memoryzy.deepSeek.ui;

import cn.memoryzy.deepSeek.bundle.DeepSeekAssistantBundle;
import cn.memoryzy.deepSeek.enums.Models;
import cn.memoryzy.deepSeek.persistent.DeepSeekAssistantPersistentState;
import cn.memoryzy.deepSeek.ui.dialog.ApiKeyConfigureDialog;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.TitledSeparator;
import com.intellij.ui.components.ActionLink;
import com.intellij.ui.components.JBLabel;

import javax.swing.*;
import java.util.Arrays;

/**
 * @author Memory
 * @since 2025/2/13
 */
public class MainConfigurableComponentProvider {
    private JPanel rootPanel;
    private TitledSeparator generalTitle;
    private JBLabel modelName;
    private ComboBox<Models> modelBox;
    private ActionLink assignKeyLink;
    private final DeepSeekAssistantPersistentState persistentState = DeepSeekAssistantPersistentState.getInstance();

    public JPanel createComponent() {
        configureGeneralComponents();
        reset();
        return rootPanel;
    }

    /**
     * 常规
     */
    private void configureGeneralComponents() {
        generalTitle.setText(DeepSeekAssistantBundle.messageOnSystem("setting.component.general.text"));
        modelName.setText(DeepSeekAssistantBundle.messageOnSystem("setting.component.model.text"));
        Arrays.stream(Models.values()).forEach(el -> modelBox.addItem(el));
        assignKeyLink.setText(DeepSeekAssistantBundle.messageOnSystem("setting.component.assignKey.text"));
        assignKeyLink.addActionListener(e -> new ApiKeyConfigureDialog(modelBox.getItem()).show());
    }

    public void reset() {
        modelBox.setItem(persistentState.model);
    }

    public boolean isModified() {
        Models oldModel = persistentState.model;
        Models newModel = modelBox.getItem();
        return oldModel != newModel;
    }

    public void apply() {
        persistentState.model = modelBox.getItem();
    }
}
