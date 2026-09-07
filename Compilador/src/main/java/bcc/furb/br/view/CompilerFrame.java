package bcc.furb.br.view;

import bcc.furb.br.controller.CompilerController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class CompilerFrame extends JFrame {

    private final JTextArea editorTextArea;
    private final JTextArea messageTextArea;
    private final JLabel statusLabel;

    private CompilerController controller;

    public CompilerFrame() {
        super("Compilador - BCC FURB");


        setSize(1500, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());


        editorTextArea = new JTextArea();
        messageTextArea = new JTextArea();


        Font codeFont = new Font("Consolas", Font.PLAIN, 14);
        editorTextArea.setFont(codeFont);
        messageTextArea.setFont(codeFont);


        editorTextArea.setMargin(new Insets(5, 5, 5, 5));
        editorTextArea.setLineWrap(false);


        editorTextArea.setBorder(new NumberedBorder());

        messageTextArea.setEditable(false);
        messageTextArea.setMargin(new Insets(5, 5, 5, 5));

        JScrollPane editorScrollPane = new JScrollPane(editorTextArea);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editorScrollPane, messageScrollPane);
        splitPane.setDividerLocation(450);
        splitPane.setContinuousLayout(true);
        add(splitPane, BorderLayout.CENTER);


        Action newAction = new AbstractAction("novo [ctrl-n]") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.handleNew();
                }
            }
        };
        newAction.putValue(Action.SMALL_ICON, carregarIcone("/icons/novo.png"));

        Action openAction = new AbstractAction("abrir [ctrl-o]") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.handleOpen();
                }
            }
        };
        openAction.putValue(Action.SMALL_ICON, carregarIcone("/icons/abrir.png"));

        Action saveAction = new AbstractAction("salvar [ctrl-s]") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.handleSave();
                }
            }
        };
        saveAction.putValue(Action.SMALL_ICON, carregarIcone("/icons/salvar.png"));

        Action copyAction = new AbstractAction("copiar [ctrl-c]") {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorTextArea.copy();
            }
        };
        copyAction.putValue(Action.SMALL_ICON, carregarIcone("/icons/copiar.png"));

        Action pasteAction = new AbstractAction("colar [ctrl-v]") {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorTextArea.paste();
            }
        };
        pasteAction.putValue(Action.SMALL_ICON, carregarIcone("/icons/colar.png"));

        Action cutAction = new AbstractAction("recortar [ctrl-x]") {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorTextArea.cut();
            }
        };
        cutAction.putValue(Action.SMALL_ICON, carregarIcone("/icons/cortar.png"));

        Action compileAction = new AbstractAction("compilar [F7]") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.handleCompile();
                }
            }
        };
        compileAction.putValue(Action.SMALL_ICON, carregarIcone("/icons/compilar.png"));

        Action teamAction = new AbstractAction("equipe [F1]") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.handleTeam();
                }
            }
        };
        teamAction.putValue(Action.SMALL_ICON, carregarIcone("/icons/equipe.png"));


        registerKeyboardShortcut(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), "newAction",
                newAction);
        registerKeyboardShortcut(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), "openAction",
                openAction);
        registerKeyboardShortcut(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "saveAction",
                saveAction);
        registerKeyboardShortcut(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), "copyAction",
                copyAction);
        registerKeyboardShortcut(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK), "pasteAction",
                pasteAction);
        registerKeyboardShortcut(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "cutAction",
                cutAction);
        registerKeyboardShortcut(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "compileAction", compileAction);
        registerKeyboardShortcut(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "teamAction", teamAction);


        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.Y_AXIS));
        toolbarPanel.setPreferredSize(new Dimension(150, 800));
        toolbarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));

        toolbarPanel.add(Box.createVerticalStrut(10));

        JButton[] buttons = {
                new JButton(newAction),
                new JButton(openAction),
                new JButton(saveAction),
                new JButton(copyAction),
                new JButton(pasteAction),
                new JButton(cutAction),
                new JButton(compileAction),
                new JButton(teamAction)
        };

        for (JButton btn : buttons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(140, 45));
            btn.setPreferredSize(new Dimension(140, 45));
            btn.setMinimumSize(new Dimension(140, 45));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setIconTextGap(8);
            btn.setFocusable(false);
            btn.setMargin(new Insets(2, 5, 2, 5));
            toolbarPanel.add(btn);
            toolbarPanel.add(Box.createVerticalStrut(5));
        }

        toolbarPanel.add(Box.createVerticalGlue());
        add(toolbarPanel, BorderLayout.WEST);


        JPanel statusBarPanel = new JPanel(new BorderLayout());
        statusBarPanel.setPreferredSize(new Dimension(1500, 25));
        statusBarPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        statusLabel = new JLabel(" ");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        statusBarPanel.add(statusLabel, BorderLayout.WEST);
        add(statusBarPanel, BorderLayout.SOUTH);
    }

    private void registerKeyboardShortcut(KeyStroke keyStroke, String actionName, Action action) {
        JComponent root = getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionName);
        root.getActionMap().put(actionName, action);
    }



    public void setController(CompilerController controller) {
        this.controller = controller;
    }

    public String getEditorText() {
        return editorTextArea.getText();
    }

    public void setEditorText(String text) {
        editorTextArea.setText(text);
    }

    public void setConsoleMessage(String message) {
        messageTextArea.setText(message);
    }

    public void setStatusMessage(String message) {
        statusLabel.setText(message);
    }

    public void requestEditorFocus() {
        editorTextArea.requestFocusInWindow();
    }

    public void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private ImageIcon carregarIcone(String caminho) {
        java.net.URL imgURL = getClass().getResource(caminho);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Ícone não encontrado: " + caminho);
            return null;
        }
    }
}
