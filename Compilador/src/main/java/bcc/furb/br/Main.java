package bcc.furb.br;

import bcc.furb.br.controller.CompilerController;
import bcc.furb.br.view.CompilerFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            
            CompilerFrame frame = new CompilerFrame();
            CompilerController controller = new CompilerController(frame);
            frame.setController(controller);
            
            frame.setVisible(true);
        });
    }
}