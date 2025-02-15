package cn.memoryzy.deepSeek.ui;

import cn.memoryzy.deepSeek.ui.components.ChatBubble;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author Memory
 * @since 2025/2/14
 */
public class TestBallon extends JPanel {

    public TestBallon() {
        super(new BorderLayout());

        ChatBubble chatBubble = new ChatBubble("你好。");
        chatBubble.setBackground(JBUI.CurrentTheme.NotificationInfo.backgroundColor());

        add(chatBubble, BorderLayout.NORTH);

    }
}
