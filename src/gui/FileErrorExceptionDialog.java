package gui;

import javax.swing.*;
import java.io.IOException;

public class FileErrorExceptionDialog extends JOptionPane {
    public FileErrorExceptionDialog( IOException ioException) {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, ioException.getMessage());
    }
}
