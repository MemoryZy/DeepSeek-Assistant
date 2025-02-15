package cn.memoryzy.deepSeek.ui.dialog;

import cn.memoryzy.deepSeek.bundle.DeepSeekAssistantBundle;
import cn.memoryzy.deepSeek.constant.UrlConstant;
import cn.memoryzy.deepSeek.enums.Urls;
import cn.memoryzy.deepSeek.ui.components.HyperLinkJBLabel;
import cn.memoryzy.deepSeek.ui.listener.HyperLinkListenerImpl;
import cn.memoryzy.deepSeek.util.HtmlUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.ActionLink;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.JBUI;
import icons.DeepSeekAssistantIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Memory
 * @since 2024/7/29
 */
public class SupportDialog extends DialogWrapper {

    private JPanel rootPanel;
    private JBLabel supportHeader;
    private JBLabel supportContent;
    private JBLabel donateHeader;
    private JBLabel donateContent;
    private JLabel wechatLabel;
    private JLabel alipayLabel;
    private ActionLink donateNote;

    public SupportDialog() {
        super((Project) null);
        setModal(false);
        setTitle(DeepSeekAssistantBundle.messageOnSystem("dialog.support.support.header"));
        setOKButtonText(DeepSeekAssistantBundle.messageOnSystem("dialog.support.ok"));
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        initCopyable();
        initLinkListener();

        supportHeader.setIcon(DeepSeekAssistantIcons.DONATE);
        supportHeader.setText(HtmlUtil.wrapBoldHtml(DeepSeekAssistantBundle.messageOnSystem("dialog.support.support.header")));
        supportContent.setText(HtmlUtil.wrapHtml(DeepSeekAssistantBundle.messageOnSystem("dialog.support.support.content",
                UrlConstant.GITHUB_LINK, UrlConstant.MARKETPLACE_REVIEWS_LINK, Urls.SHARE.getId())));

        donateHeader.setIcon(DeepSeekAssistantIcons.HEART);
        donateHeader.setText(HtmlUtil.wrapBoldHtml(DeepSeekAssistantBundle.messageOnSystem("dialog.support.donate.header")));
        donateContent.setText(HtmlUtil.wrapHtml(DeepSeekAssistantBundle.messageOnSystem("dialog.support.donate.content")));

        wechatLabel.setIcon(DeepSeekAssistantIcons.WECHAT_PAY);
        wechatLabel.setText(HtmlUtil.wrapBoldHtml(DeepSeekAssistantBundle.messageOnSystem("dialog.support.wechat")));

        alipayLabel.setIcon(DeepSeekAssistantIcons.ALIPAY);
        alipayLabel.setText(HtmlUtil.wrapBoldHtml(DeepSeekAssistantBundle.messageOnSystem("dialog.support.alipay")));

        donateNote.setIcon(DeepSeekAssistantIcons.LABEL);
        donateNote.setText(DeepSeekAssistantBundle.messageOnSystem("dialog.support.donate.link"));

        return rootPanel;
    }

    private void initLinkListener() {
        donateNote.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component source = (Component) e.getSource();
                RelativePoint relativePoint = new RelativePoint(source, new Point(source.getWidth() / 2, source.getHeight() - 25));

                JBPopupFactory.getInstance()
                        .createHtmlTextBalloonBuilder(
                                DeepSeekAssistantBundle.messageOnSystem("dialog.support.donate.note",
                                        // TODO 这里捐赠者列表弄一个弹窗，用于从Github或哪里获取捐赠者列表名单
                                        /*Urls.DONORS_LIST_LINK*/"", Urls.MAIL.getId(), UrlConstant.EMAIL_LINK),
                                null,
                                JBUI.CurrentTheme.NotificationInfo.backgroundColor(),
                                new HyperLinkListenerImpl())
                        .setShadow(true)
                        .setHideOnAction(true)
                        .setHideOnClickOutside(true)
                        .setHideOnFrameResize(true)
                        .setHideOnKeyOutside(true)
                        .setHideOnLinkClick(true)
                        .setContentInsets(JBUI.insets(10))
                        .createBalloon()
                        .show(relativePoint, Balloon.Position.above);
            }
        });
    }

    private void createUIComponents() {
        supportContent = new HyperLinkJBLabel();
    }

    private void initCopyable() {
        supportHeader.setCopyable(true);
        supportContent.setCopyable(true);
        donateHeader.setCopyable(true);
        donateContent.setCopyable(true);
    }

    @Override
    protected Action @NotNull [] createActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(getOKAction());
        return actions.toArray(new Action[0]);
    }

    @Override
    public void show() {
        ApplicationManager.getApplication().invokeLater(super::show);
    }
}
