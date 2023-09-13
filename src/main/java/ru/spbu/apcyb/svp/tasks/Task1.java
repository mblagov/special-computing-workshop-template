package ru.spbu.apcyb.svp.tasks;



import java.util.*;
import java.util.logging.Logger;


/**
 * Задание 1. Банкомат и размены.
 */

public class Task1 {

    private Logger logger = Logger.getLogger(Task1.class.getName());
    private ArrayList<String> combinations = new ArrayList<>();

    /**
     * Метод для получения числа комбинаций купюр данной суммы.
     *
     * @param sum - переменная для передачи суммы
     * @param prevNominal - переменная для хранения предыдущего номинала, участвующего в размене.
     * @param combination - строка для хранения и вывода комбинации
     * @param nomArr - массив номиналов
     * @return число комбинаций
     */
    public int getCombinations(int sum, int prevNominal, String combination, Integer [] nomArr) {
        int count = 0;
        if (sum == 0) {
            count++;
            logger.info(combination);
            combinations.add(combination);
        }
        for (int curNominal : nomArr) {
            if ((prevNominal >= curNominal) && (sum >= curNominal)) {
                count += getCombinations(sum - curNominal, curNominal, combination + " " + curNominal + " ", nomArr);
            }
        }
        return count;
    }

    /**
     * Метод для парсинга суммы.
     *
     * @param str - строка с введенной суммой
     * @return сумма
     */

    public int parseSum(String str) {
        return parseToInteger(str);
    }

    /**
     * Метод для парсинга номиналов.
     *
     * @param str - строка с номиналами из ввода в консоли
     * @return отсортированный массив номиналов
     */

    public   Integer [] parseNominal(String str) {
        if(str.equals(" ")){
            throw new IllegalArgumentException("Ошибка! Пустая строка.");
        }
        String[] strMas = str.split(" ");
        Integer [] nomArr = new Integer[strMas.length];
        for (int i = 0; i < nomArr.length; i++) {
            nomArr[i] = parseToInteger(strMas[i]);
        }
        return Arrays.stream(nomArr)
                .sorted(Comparator.reverseOrder())
                .distinct()
                .toArray(Integer[]::new);
    }

    /**
     * Метод для парсинга из String в Integer
     * @param str - число в типе String
     * @return число типа Integer
     */
    public  int parseToInteger(String str){
        int value;
        try{
            value = Integer.parseInt(str);
            if(value <= 0) {
                throw new ArithmeticException("Ошибка! Значение номинала <= 0");
            }
        }
        catch (Exception e){
            throw new IllegalArgumentException("Ошибка! Значение номинала не целое число!");
        }
        return value;
    }

    public List<String> getCombinations(){
        ArrayList<String> answer = new ArrayList<>(combinations);
        combinations.clear();
        return answer;
    }


    /**
     * Программа принимает от пользователя две строки из консоли
     * str1 - сумма
     * str2 - номиналы через пробел
     * выводит комбинации и их число.
     */

    public static void main(String[] args) {
        Task1 ts = new Task1();
        Logger logger = Logger.getLogger(ts.getClass().getName());
        Scanner scanner = new Scanner(System.in);
        String sum = scanner.nextLine();
        String nominal = scanner.nextLine();
        String str = String.valueOf(ts.getCombinations(ts.parseSum(sum), ts.parseNominal(nominal)[0], " ", ts.parseNominal(nominal)));
        logger.info(str);
    }
}