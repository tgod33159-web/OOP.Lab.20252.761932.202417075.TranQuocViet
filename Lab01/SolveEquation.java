import javax.swing.JOptionPane;

public class SolveEquation {
    public static void main(String[] args) {

        // ===== 1. Phương trình bậc 1 =====
        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter b:"));

        if (a == 0) {
            if (b == 0)
                JOptionPane.showMessageDialog(null, "PT bậc 1: Vô số nghiệm");
            else
                JOptionPane.showMessageDialog(null, "PT bậc 1: Vô nghiệm");
        } else {
            double x = -b / a;
            JOptionPane.showMessageDialog(null, "PT bậc 1: x = " + x);
        }

        // ===== 2. Hệ phương trình =====
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("a11:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("a12:"));
        double b1  = Double.parseDouble(JOptionPane.showInputDialog("b1:"));

        double a21 = Double.parseDouble(JOptionPane.showInputDialog("a21:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("a22:"));
        double b2  = Double.parseDouble(JOptionPane.showInputDialog("b2:"));

        double D  = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D == 0) {
            if (D1 == 0 && D2 == 0)
                JOptionPane.showMessageDialog(null, "Hệ PT: Vô số nghiệm");
            else
                JOptionPane.showMessageDialog(null, "Hệ PT: Vô nghiệm");
        } else {
            double x1 = D1 / D;
            double x2 = D2 / D;
            JOptionPane.showMessageDialog(null, "Hệ PT:\nx1 = " + x1 + "\nx2 = " + x2);
        }

        // ===== 3. Phương trình bậc 2 =====
        double a2 = Double.parseDouble(JOptionPane.showInputDialog("a (bậc 2):"));
        double b22 = Double.parseDouble(JOptionPane.showInputDialog("b:"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("c:"));

        if (a2 == 0) {
            JOptionPane.showMessageDialog(null, "Không phải PT bậc 2");
        } else {
            double delta = b22 * b22 - 4 * a2 * c;

            if (delta < 0) {
                JOptionPane.showMessageDialog(null, "PT bậc 2: Vô nghiệm");
            } else if (delta == 0) {
                double x = -b22 / (2 * a2);
                JOptionPane.showMessageDialog(null, "PT bậc 2: Nghiệm kép x = " + x);
            } else {
                double x1 = (-b22 + Math.sqrt(delta)) / (2 * a2);
                double x2 = (-b22 - Math.sqrt(delta)) / (2 * a2);
                JOptionPane.showMessageDialog(null, "PT bậc 2:\nx1 = " + x1 + "\nx2 = " + x2);
            }
        }

        System.exit(0);
    }
}