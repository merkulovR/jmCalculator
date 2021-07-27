/*Данный класс проводит заключительные операции с числами, полученными от пользователя, после всех проверок на соответствие уловиям*/
public class Calculator {
    private final InputDataHandling IDH;
    private final Operations OPERATIONS = new Operations();

    /*Конструктор принимает в качестве параметра строку от пользоваателя с консоли
    * Создает экземпляр InputDataHandling и передает ему строку от пользователя для проведения всех проверок
    * Если проверки пройдены, сразу считает результат с помощью метода calculate()*/
    public Calculator(String input) throws IllegalArgumentException{
        this.IDH = new InputDataHandling(input);
        calculate();
    }

    public void calculate() {
        int result = 0;
        switch (IDH.getOperator()) {
            case "+":
                result = OPERATIONS.addition(IDH.getFirstNumber(), IDH.getSecondNumber());
                break;
            case "-":
                result = OPERATIONS.subtraction(IDH.getFirstNumber(), IDH.getSecondNumber());
                break;
            case "*":
                result = OPERATIONS.multiplication(IDH.getFirstNumber(), IDH.getSecondNumber());
                break;
            case "/":
                result = OPERATIONS.division(IDH.getFirstNumber(), IDH.getSecondNumber());
                break;
        }

        /*В ТЗ не нашел пункта про отрицательный или равный нулю результат при работе с римскими цифрами
        * поэтому позволил себе вольность с выведением результата в арабской системе счисления,
        * а затем уже выбрасываем исключение с сообщением о том, что конвертировать результат не получится*/
        if (IDH.getIsRoman()) {
            if (result < 1) {
                System.out.println("Ваш результат равен " + result);
                throw new IllegalArgumentException("Римскими цифрами может быть представлено только число из диапазона от 1 до 3999.");
            }
            System.out.println(RomanNumbers.convertToRoman(result));
        } else System.out.println(result);
    }
}
