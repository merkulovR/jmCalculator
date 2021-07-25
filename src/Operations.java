/*Класс с необходимыми нам операциями
* методы используются в Калькуляторе
* статический массив с арифметическими знаками используется в классе InputDataHandling
* для проверки совпадения со знаком, введенным в консоль пользователем*/
public class Operations {
    final static String[] OPERATORS = {"+", "-", "*", "/"};

    public int addition(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int multiplication(int a, int b) {
        return a * b;
    }

    public int division(int a, int b) {
        return a / b;
    }
}
