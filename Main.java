import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение: ");
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.nextLine();

        char action = getAction(exp);
        String[] data = splitExpression(exp, action);
        expInput(data, action);

        String result = calcResult(data, action);
        printInQuotes(result);
    }

    static char getAction(String exp) throws Exception {
        String[] a = new String[] {"+", "-", "*", "/"};
        for (String b : a) {
            if (exp.contains(b)) {
                return b.charAt(0);
            }
        }
        throw new Exception("Некорректный знак действия! ");
    }

    static String[] splitExpression(String exp, char action) {
        return exp.split(" \\" + action + " ");
    }

    static void expInput(String[] data, char action) throws Exception {
        if (data[0].length() > 11 || data[1].length() > 11) {
            throw new Exception("Введено больше 10 символов! ");
        }
        if ((action == '*' || action == '/') && data[1].contains("\"")) {
            throw new Exception("Строку можно делить или умножать только на число! ");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }
    }

    static String calcResult(String[] data, char action) {
        return switch (action) {
            case '+' -> data[0] + data[1];
            case '*' -> multiplyString(data[0], Integer.parseInt(data[1]));
            case '-' -> subtractString(data[0], data[1]);
            case '/' -> divideString(data[0], Integer.parseInt(data[1]));
            default -> throw new IllegalArgumentException("Exception");
        };
    }

    static String multiplyString(String str, int multiplier) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < multiplier; i++) {
            result.append(str);
        }
        return result.toString();
    }

    static String subtractString(String str, String toRemove) {
        int index = str.indexOf(toRemove);
        if (index == -1) return str;
        return str.substring(0, index) + str.substring(index + toRemove.length());
    }

    static String divideString(String str, int divisor) {
        int newLen = str.length() / divisor;
        return str.substring(0, newLen);
    }

    static void printInQuotes(String text) {
        if (text.length() > 40) text = text.substring(0, 40) + "...";
        System.out.println('\"'+text+'\"');
    }
}