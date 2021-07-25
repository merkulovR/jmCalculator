/*Данный класс проводит заключительные операции с числами, полученными от пользователя, после всех проверок на соответствие уловиям*/
public class Calculator {
    private final InputDataHandling idh;
    private final Operations operations = new Operations();

    /*Конструктор принимает в качестве параметра строку от пользоваателя с консоли
    * Создает экземпляр InputDataHandling и передает ему строку от пользователя для проведения всех проверок
    * Если проверки пройдены, сразу считает результат с помощью метода calculate()*/
    public Calculator(String input) throws IllegalArgumentException{
        this.idh = new InputDataHandling(input);
        calculate();
    }

    public void calculate() {
        int result = 0;
        switch (idh.getOperator()) {
            case "+":
                result = operations.addition(idh.getFirstNumber(), idh.getSecondNumber());
                break;
            case "-":
                result = operations.subtraction(idh.getFirstNumber(), idh.getSecondNumber());
                break;
            case "*":
                result = operations.multiplication(idh.getFirstNumber(), idh.getSecondNumber());
                break;
            case "/":
                result = operations.division(idh.getFirstNumber(), idh.getSecondNumber());
                break;
        }

        /*В ТЗ не нашел пункта про отрицательный или равный нулю результат при работе с римскими цифрами
        * поэтому позволил себе вольность с выведением результата в арабской системе счисления,
        * а затем уже выбрасываем исключение с сообщением о том, что конвертировать результат не получится*/
        if (idh.getIsRoman()) {
            if (result < 1) {
                System.out.println("Ваш результат равен " + result);
                throw new IllegalArgumentException("Римскими цифрами может быть представлено только число из диапазона от 1 до 3999.");
            }
            System.out.println(RomanNumbers.convertToRoman(result));
        } else System.out.println(result);
    }
}
