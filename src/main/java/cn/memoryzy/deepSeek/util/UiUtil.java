package cn.memoryzy.deepSeek.util;

import com.intellij.icons.AllIcons;
import com.intellij.ide.HelpTooltip;

import javax.swing.*;

/**
 * @author Memory
 * @since 2025/2/13
 */
public class UiUtil {

    public static void setHelpLabel(JLabel label, String description) {
        label.setIcon(AllIcons.General.ContextHelp);
        new HelpTooltip().setDescription(description).installOn(label);
    }

}
