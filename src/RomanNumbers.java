public enum RomanNumbers {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private final int value;
    private final static RomanNumbers[] romanNumbers = RomanNumbers.values();

    RomanNumbers(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /*Конвертер из арабской системы в римскую
    Используется только для конвертации результата вычисления
    * оставил проверку на диапазон от 1 до 3999 для возможного масштабирования программмы
    * в конкретном случае она здесь не требуется, т.к. присутствует проверка результата в классе Calculator*/
    public static String convertToRoman(int arabic) throws IllegalArgumentException{
        if (arabic < 1 || arabic > 3999) {
            throw new IllegalArgumentException("Римскими цифрами может быть представлено только число из диапазона от 1 до 3999.");
        }

        StringBuilder roman = new StringBuilder();

        for (int i = romanNumbers.length - 1; i >= 0; ) {
            if (arabic >= romanNumbers[i].getValue()) {
                roman.append(romanNumbers[i].name());
                arabic -= romanNumbers[i].getValue();
            } else i--;
        }

        return roman.toString();
    }

    /*Конвертер из римской системы в арабскую
    * Используется для конвертации операндов, предоставленных пользователем через консоль*/
    public static int convertToArabic(String roman) throws IllegalArgumentException{
        int arabic = 0;

        /*Проверяем входные данные посредством регулярного выражения
        * тут можно убрать часть "M{0,3}(CM|CD|D?", т.к. в нашей программе она не требуется*/
        if (!roman.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
            throw new IllegalArgumentException("Неверный формат числа в римской системе счисления.");
        }

        /*пробегаемся по нашему enum в обратном направлении, при каждой итерации проверяя, начинается ли входная строка (с римским числом)
        * с букв, которыми представлено римское число из enum
        * при совпадении добавляем значение данного числа в переменную arabic и обрезаем строку roman на количество символов римского числа, с которым мы только что сверились
        * если совпадения не происходит, спускаемся вниз по массиву romanNumbers[] к меньшему числу*/
        for (int i = romanNumbers.length - 1; i >=0; ) {
            if (roman.startsWith(romanNumbers[i].name())) {
                arabic += romanNumbers[i].getValue();
                roman = roman.substring(romanNumbers[i].name().length());
            } else i--;
        }

        return arabic;
    }
}
