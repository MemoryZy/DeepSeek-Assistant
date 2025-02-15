package cn.memoryzy.deepSeek.ui.components;

import cn.memoryzy.deepSeek.ui.listener.HyperLinkListenerImpl;
import com.intellij.ui.components.JBLabel;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkListener;

/**
 * @author Memory
 * @since 2024/7/29
 */
public class HyperLinkJBLabel extends JBLabel {
    @Override
    protected @NotNull HyperlinkListener createHyperlinkListener() {
        return new HyperLinkListenerImpl();
    }
}