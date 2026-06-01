package hust.soict.dsai.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NumberGrid extends JFrame {
    private final JButton[] btnNumbers = new JButton[10];
    private final JButton btnDelete = new JButton("DEL");
    private final JButton btnReset = new JButton("C");
    private final JTextField tfDisplay = new JTextField();

    public NumberGrid() {
        tfDisplay.setEditable(false);
        add(tfDisplay, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel(new GridLayout(4, 3));
        addButtons(panelButtons);
        add(panelButtons, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButtons(JPanel panelButtons) {
        ButtonListener listener = new ButtonListener();

        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton(String.valueOf(i));
            btnNumbers[i].addActionListener(listener);
            panelButtons.add(btnNumbers[i]);
        }

        btnDelete.addActionListener(listener);
        panelButtons.add(btnDelete);

        btnNumbers[0] = new JButton("0");
        btnNumbers[0].addActionListener(listener);
        panelButtons.add(btnNumbers[0]);

        btnReset.addActionListener(listener);
        panelButtons.add(btnReset);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGrid::new);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            for (int i = 0; i <= 9; i++) {
                if (source == btnNumbers[i]) {
                    tfDisplay.setText(tfDisplay.getText() + i);
                    return;
                }
            }

            if (source == btnDelete) {
                String text = tfDisplay.getText();
                if (!text.isEmpty()) {
                    tfDisplay.setText(text.substring(0, text.length() - 1));
                }
            } else if (source == btnReset) {
                tfDisplay.setText("");
            }
        }
    }
}
