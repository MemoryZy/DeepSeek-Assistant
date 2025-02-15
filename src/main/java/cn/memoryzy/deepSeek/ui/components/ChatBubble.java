package cn.memoryzy.deepSeek.ui.components;

import javax.swing.*;
import java.awt.*;

public class ChatBubble extends JTextArea {
    private final int ARC_WIDTH = 20; // 圆角宽度  
    private final int ARC_HEIGHT = 20; // 圆角高度

    public ChatBubble(String text) {  
        super(text);  
        setEditable(false);  
        setLineWrap(true);  
        setWrapStyleWord(true);  
        setOpaque(false);  
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // 内边距  
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制圆角背景
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);

        // 绘制文本
        super.paintComponent(g2d);
        g2d.dispose();
    }
}