package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //TODO hier weitere Tests erstellen
    @Test
    @DisplayName("should repeat last addition when equals is pressed consecutively")
    void testRepeatedEqualsForAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressEqualsKey(); // ergibt 8
        calc.pressEqualsKey(); // sollte 8 + 3 wiederholen und 11 ergeben
        calc.pressEqualsKey(); // sollte erneut 3 addieren und 14 ergeben

        String expected = "14";  // Erwartet: 5 + 3 + 3 + 3 = 14
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("sollte nur den aktuellen Eintrag löschen beim ersten Drücken der Clear-Taste")
    void testClearKeySinglePress() {
        Calculator calc = new Calculator();

        // Eingabe einer Zahl und einer Operation
        calc.pressDigitKey(9);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(1);

        // Erstes Drücken der Clear-Taste
        calc.pressClearKey();

        String erwartet = "0"; // Der Bildschirm sollte "0" anzeigen
        String aktuell = calc.readScreen();

        assertEquals(erwartet, aktuell);

        // Überprüfen, ob der Operationsmodus und latestValue erhalten geblieben sind
        // Zum Beispiel sollte das Hinzufügen fortgesetzt werden, wenn eine weitere Zahl eingegeben wird
        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        String erwartetNachAddition = "11"; // 9 + 2 = 11
        String aktuellNachAddition = calc.readScreen();

        assertEquals(erwartetNachAddition, aktuellNachAddition);
    }


}
