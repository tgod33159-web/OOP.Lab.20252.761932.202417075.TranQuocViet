import javax.swing.JOptionPane;

public class CalculateTwoNumbers {
    public static void main(String[] args) {
        String str1 = JOptionPane.showInputDialog("Enter first number:");
        String str2 = JOptionPane.showInputDialog("Enter second number:");

        double a = Double.parseDouble(str1);
        double b = Double.parseDouble(str2);

        double sum = a + b;
        double diff = a - b;
        double product = a * b;

        String result = "Sum = " + sum +
                        "\nDifference = " + diff +
                        "\nProduct = " + product;

        if (b != 0) {
            double quotient = a / b;
            result += "\nQuotient = " + quotient;
        } else {
            result += "\nCannot divide by 0!";
        }

        JOptionPane.showMessageDialog(null, result);
        System.exit(0);
    }
}