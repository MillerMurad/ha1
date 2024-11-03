package htw.berlin.prog2.ha1;

public class Calculator {

    private String screen = "0";
    private double latestValue; // Letzter Operandenwert
    private String latestOperation = ""; // Letzte Operation

    public String readScreen() {
        return screen;
    }

    public void pressDigitKey(int digit) {
        if (digit > 9 || digit < 0) throw new IllegalArgumentException();

        if (screen.equals("0")) screen = ""; // Reset, wenn der aktuelle Bildschirm 0 ist

        screen += digit;
    }

    public void pressClearKey() {
        // Nur den Bildschirm zurücksetzen, ohne die Operationen oder Werte zu reseten
        screen = "0";
    }

    public void pressBinaryOperationKey(String operation) {
        // Nur den Modus setzen, keine Berechnung durchführen
        if (!screen.equals("0")) {
            latestValue = Double.parseDouble(screen); // Speichern der letzten Zahl
        }
        latestOperation = operation;
        screen = "0"; // Bildschirm zurücksetzen für die nächste Eingabe
    }

    public void pressUnaryOperationKey(String operation) {
        latestValue = Double.parseDouble(screen);
        var result = switch (operation) {
            case "√" -> Math.sqrt(latestValue);
            case "%" -> latestValue / 100;
            case "1/x" -> 1 / latestValue;
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);
        if (screen.equals("NaN")) screen = "Error";
        if (screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);
    }

    public void pressDotKey() {
        if (!screen.contains(".")) screen += ".";
    }

    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
    }

    public void pressEqualsKey() {
        // Wenn keine Operation gesetzt ist, nichts tun
        if (latestOperation.isEmpty()) return;

        double currentValue = Double.parseDouble(screen);
        var result = switch (latestOperation) {
            case "+" -> latestValue + currentValue;
            case "-" -> latestValue - currentValue;
            case "x" -> latestValue * currentValue;
            case "/" -> currentValue == 0 ? Double.NaN : latestValue / currentValue; // Division durch null behandeln
            default -> throw new IllegalArgumentException();
        };

        // Aktualisiere den Bildschirm mit dem Ergebnis
        screen = Double.toString(result);
        if (screen.equals("Infinity") || screen.equals("NaN")) screen = "Error";
        if (screen.endsWith(".0")) screen = screen.substring(0, screen.length() - 2);
        if (screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);

        // Speichere das Ergebnis als latestValue für die nächste Verwendung
        latestValue = result; // Update latestValue für wiederholte Gleichheitsoperationen
    }
}
