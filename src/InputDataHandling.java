import java.util.Arrays;

/*Класс, обрабатывающий и проверяющий всю, полученную от пользователя информацию*/
public class InputDataHandling {
    private int firstNumber;
    private int secondNumber;
    private String operator;
    private boolean isRoman;

    /*Конструктор принимает в качестве параметра строку с консоли от пользователя и сразу передает ее в методы
    данного класса для проверки чисел и арифметичекого оператора на соответствие условиям работы калькулятора.
    На выходе при соответствии всем условиям мы получим проинициализированные поля класса, которые используем в Калькуляторе*/
    public InputDataHandling (String input) throws IllegalArgumentException {
        isNumbers(input);
        isOperationLegal(input);
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public boolean getIsRoman() {
        return isRoman;
    }

    //Проверка строки от пользователя на наличие двух чисел, с которыми мы будем работать
    public void isNumbers(String input) throws IllegalArgumentException {
        if (input.equals("")) {
            throw new IllegalArgumentException("Вы ничего не ввели.");
        }

        input = input.toUpperCase();    //позволим пользователю не утруждать себя соблюдать регистр при вводе римских цифр
        String[] numbers = input.split("\\W+");

        if (numbers.length != 2) {
            throw new IllegalArgumentException("Вам необходимо ввести ровно два числа!");
        }

        //проверка на соответвие условию "Калькулятор умеет работать только с арабскими или римкими цифрами ОДНОВРЕМЕННО"
        if (isRoman(numbers[0]) && isRoman(numbers[1])) {
            this.firstNumber = RomanNumbers.convertToArabic(numbers[0]);
            this.secondNumber = RomanNumbers.convertToArabic(numbers[1]);
        } else if (isArabic(numbers[0]) && isArabic(numbers[1])) {
            try {
                this.firstNumber = Integer.parseInt(numbers[0]);
                this.secondNumber = Integer.parseInt(numbers[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else throw new IllegalArgumentException("Вы можете использовать только арабские или только римские цифры (отображенные латинскими буквами).");

        if ((firstNumber < 1 || firstNumber > 10) || (secondNumber < 1 || secondNumber > 10)) {
            throw new IllegalArgumentException("Вы можете использовать только числа из диапазона от 1 до 10 (I - X).");
        }
    }

    //Проверка арифметического оператора
    public void isOperationLegal(String input) throws IllegalArgumentException {
        String someOperator = input.replaceAll("\\w+", "");

        if (someOperator.contains(".") || someOperator.contains(",")) {
            throw new IllegalArgumentException("Вы можете использовать только целые числа.");
        }

        someOperator = someOperator.trim();

        if (Arrays.asList(Operations.OPERATORS).contains(someOperator)) {
            this.operator = someOperator;
        } else throw new IllegalArgumentException("В примере может быть указан только один из арифметических операторов: +, -, *, /");
    }

    private boolean isRoman(String num) {
        if (num.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
            this.isRoman = true;
            return isRoman;
        }

        this.isRoman = false;
        return isRoman;
    }

    private boolean isArabic(String num) {
        return num.matches("\\d+");
    }
}
