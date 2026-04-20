import javax.swing.JOptionPane;

public class ChoosingOption {
    public static void main(String[] args) {
        int option = JOptionPane.showConfirmDialog(null, "Do you want to continue?");

        if (option == JOptionPane.YES_OPTION)
            JOptionPane.showMessageDialog(null, "You chose YES");
        else if (option == JOptionPane.NO_OPTION)
            JOptionPane.showMessageDialog(null, "You chose NO");
        else
            JOptionPane.showMessageDialog(null, "You cancelled");

        System.exit(0);
    }
}