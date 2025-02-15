package cn.memoryzy.deepSeek.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatApplication2 extends JPanel {
    private final JPanel chatPanel;
    private final JTextArea inputArea;
    private final JScrollPane scrollPane;
    private final MarkdownParser2 markdownParser = new MarkdownParser2();

    public ChatApplication2() {
        setSize(800, 600);
        setLayout(new BorderLayout());

        // 聊天内容面板
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(new Color(245, 245, 245));

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // 输入面板
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputArea = new JTextArea(3, 20);
        inputArea.setLineWrap(true);
        JButton sendButton = new JButton("Send");
        
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(this::handleSend);
    }

    private void handleSend(ActionEvent e) {
        String text = inputArea.getText().trim();
        if (!text.isEmpty()) {
            addUserMessage(text);
            inputArea.setText("");
            simulateAIResponse();
        }
    }

    private void addUserMessage(String text) {
        ChatBubble2 userBubble = new ChatBubble2(text, true);
        chatPanel.add(userBubble);
        updateChatPanel();
    }

    private void addAIMessage(String text) {
        ChatBubble2 aiBubble = new ChatBubble2(text, false);
        chatPanel.add(aiBubble);
        updateChatPanel();
    }
    
    private void updateChatPanel() {
        chatPanel.revalidate();
        chatPanel.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

    private void simulateAIResponse() {
        new Thread(() -> {
            String[] responseParts = {
                "好的，以下是一个Java示例代码：\n\n```java\npublic class HelloWorld {\n",
                "    public static void main(String[] args) {\n",
                "        System.out.println(\"Hello World!\");\n    }\n}\n```\n\n",
                "这段代码会输出**Hello World**。你可以使用`System.out.println`方法进行输出。\n",
                "另外，这里有一些格式示例：\n* 列表项1\n* 列表项2\n\n*斜体* **粗体** `代码`"
            };

            JTextPane streamingPane = new JTextPane();
            streamingPane.setContentType("text/html");
            streamingPane.setEditable(false);
            ChatBubble2 aiBubble = new ChatBubble2("", false);
            aiBubble.add(streamingPane);
            chatPanel.add(aiBubble);

            StringBuilder accumulatedResponse = new StringBuilder();
            for (String part : responseParts) {
                try {
                    Thread.sleep(500); // 模拟流式延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                accumulatedResponse.append(part);
                String processed = markdownParser.parse(accumulatedResponse.toString());
                SwingUtilities.invokeLater(() -> {
                    streamingPane.setText(processed);
                    updateChatPanel();
                });
            }
        }).start();
    }
}

class ChatBubble2 extends JPanel {
    public ChatBubble2(String text, boolean isUser) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JTextPane content = new JTextPane();
        content.setContentType("text/html");
        content.setEditable(false);
        content.setText(text);

        JPanel bubble = new JPanel(new BorderLayout());
        bubble.setBackground(isUser ? new Color(220, 248, 198) : Color.WHITE);
        bubble.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        bubble.add(content);

        if (isUser) {
            setAlignmentX(RIGHT_ALIGNMENT);
            add(bubble, BorderLayout.EAST);
        } else {
            setAlignmentX(LEFT_ALIGNMENT);
            add(bubble, BorderLayout.WEST);
        }
    }
}

class MarkdownParser2 {
    private boolean inCodeBlock = false;
    private final StringBuilder buffer = new StringBuilder();

    public String parse(String markdown) {
        buffer.append(markdown);
        String content = buffer.toString();
        buffer.setLength(0);

        StringBuilder html = new StringBuilder();
        int lastPos = 0;
        
        while (true) {
            if (inCodeBlock) {
                int endIndex = content.indexOf("```", lastPos);
                if (endIndex == -1) {
                    html.append(escape(content.substring(lastPos)));
                    buffer.append(content.substring(lastPos));
                    break;
                }
                
                html.append(escape(content.substring(lastPos, endIndex)));
                html.append("</code></pre>");
                lastPos = endIndex + 3;
                inCodeBlock = false;
            } else {
                int codeStart = content.indexOf("```", lastPos);
                int boldStart = content.indexOf("**", lastPos);
                int italicStart = content.indexOf("*", lastPos);
                int codeInlineStart = content.indexOf("`", lastPos);

                int nextEvent = findNextEvent(codeStart, boldStart, italicStart, codeInlineStart);
                
                if (nextEvent == -1) {
                    html.append(processInline(content.substring(lastPos)));
                    break;
                }
                
                html.append(processInline(content.substring(lastPos, nextEvent)));
                
                if (nextEvent == codeStart) {
                    String lang = content.substring(codeStart + 3)
                            .split("\n", 2)[0].trim();
                    html.append("<pre style='background:#f5f5f5;padding:10px;border-radius:5px;'><code>");
                    lastPos = codeStart + 3 + lang.length();
                    inCodeBlock = true;
                } else if (nextEvent == boldStart) {
                    html.append("<strong>");
                    int end = content.indexOf("**", boldStart + 2);
                    if (end == -1) break;
                    html.append(processInline(content.substring(boldStart + 2, end)));
                    html.append("</strong>");
                    lastPos = end + 2;
                } else if (nextEvent == italicStart) {
                    html.append("<em>");
                    int end = content.indexOf("*", italicStart + 1);
                    if (end == -1) break;
                    html.append(processInline(content.substring(italicStart + 1, end)));
                    html.append("</em>");
                    lastPos = end + 1;
                } else if (nextEvent == codeInlineStart) {
                    html.append("<code style='background:#f0f0f0;padding:2px 4px;border-radius:3px;'>");
                    int end = content.indexOf("`", codeInlineStart + 1);
                    if (end == -1) break;
                    html.append(escape(content.substring(codeInlineStart + 1, end)));
                    html.append("</code>");
                    lastPos = end + 1;
                }
            }
        }
        
        return html.toString().replace("\n", "<br/>");
    }
    
    private int findNextEvent(int code, int bold, int italic, int codeInline) {
        int min = Integer.MAX_VALUE;
        if (code != -1) min = Math.min(min, code);
        if (bold != -1) min = Math.min(min, bold);
        if (italic != -1) min = Math.min(min, italic);
        if (codeInline != -1) min = Math.min(min, codeInline);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private String processInline(String text) {
        return text.replace("**", "<strong>")
                   .replace("*", "<em>")
                   .replace("`", "<code>")
                   .replaceAll("<strong>(.*?)</strong>", "<strong>$1</strong>")
                   .replaceAll("<em>(.*?)</em>", "<em>$1</em>")
                   .replaceAll("<code>(.*?)</code>", "<code style='background:#f0f0f0;padding:2px 4px;'>$1</code>");
    }

    private String escape(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;");
    }
}