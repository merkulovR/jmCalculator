import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите пример для сложения/вычитания/умножения/деления двух целых чисел от 1 до 10 включительно.\n" +
                "Вы можете использовать как арабские (1-10), так и римские числа (I-X).\n" +
                "Данные должны быть введены в одну строку. Например: \"VIII - IV\" или \"9 * 2\".");

        Scanner scanner = new Scanner(System.in);

        try {
            new Calculator(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        scanner.close();
    }
}
