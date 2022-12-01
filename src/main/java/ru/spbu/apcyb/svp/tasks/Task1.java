package ru.spbu.apcyb.svp.tasks;

import java.util.*;
import org.apache.logging.log4j.*;


/**
 * Задание 1.
 */
public class Task1 {
    private static final Logger logger = LogManager.getLogger(Task1.class);

    /**
     * Запуск программы.
     *
     * @param args вспомогательный аргумент.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        logger.log(Level.INFO, "input the amount: ");
        String stringAmount = in.nextLine();
        logger.log(Level.INFO, "input banknotes: ");
        String stringBanknotes = in.nextLine();
        in.close();

        exchangeOfBanknotes(stringAmount, stringBanknotes);
    }

    /**
     * Подпрограмма для перевода строки в массив чисел.
     *
     * @param stringBanknotes строка со списком доступных номиналов, записанных через пробел.
     * @return массив купюр.
     */
    public static long[] inputLongList(String stringBanknotes){

        String[] splitStr = stringBanknotes.split("\\D+");

        long[] banknotes = new long[splitStr.length];

        for (int i = 0; i < banknotes.length; i++) {
            try {
                banknotes[i] = Long.parseLong(splitStr[i]);
                if (banknotes[i] <= 0){throw new NumberFormatException();}
                }catch(NumberFormatException e) {
                logger.log(Level.INFO, "incorrect input banknotes");
                throw e;}
        }
        if (banknotes.length == 0){
            logger.log(Level.INFO, "incorrect input banknotes");
            throw new NumberFormatException();}
        return banknotes;
    }

    /**
     * Подпрограмма для вывода результата на экран.
     *
     * @param result число полученных комбинаций.
     * @param amount размениваемая сумма.
     * @param banknotes массив банкнот.
     */
    public static void printExchangeOfBanknotes(List<List<Long>> result, long amount, long[] banknotes){
        logger.log(Level.INFO, "----------------------");

        logger.log(Level.INFO, "amount: {}", amount);
        logger.log(Level.INFO, "banknotes: ");

        for (long banknote : banknotes){
            logger.log(Level.INFO, "{} ", banknote);
        }
        logger.log(Level.INFO,"number of combinations:  {}", result.size());
        if (result.size() != 0) {
            logger.log(Level.INFO,"combinations:");
            for (List<Long> combination : result) {
                logger.log(Level.INFO, combination);
            }
        } else { logger.log(Level.INFO,"Sorry, not today"); }
    }
    /**
     * Размен указанной купюры всеми возможными способами.
     *
     * @param stringBanknotes строка со списком банкнот.
     * @param stringAmount строка с размениваемой суммой.
     * @return массив массивов всевозможных комбинаций.
     */
    public static List<List<Long>> exchangeOfBanknotes(String stringAmount, String stringBanknotes){
        List<List<Long>> result = new ArrayList<>();
        List<Long> valueList = new ArrayList<>();
        long amount;
        try {
        amount = Long.parseLong(stringAmount); } catch(NumberFormatException e){
            logger.log(Level.INFO,"incorrect input amount");
            throw e;
        }
        if (amount<=0){
            logger.log(Level.INFO,"incorrect input amount");
            throw new NumberFormatException();
        }
        long[] banknotes = inputLongList(stringBanknotes);
        banknotes = Arrays.stream(banknotes).distinct().toArray();
        long maxBanknote = -1;
        for (long num : banknotes) {
            if (maxBanknote < num) {
                maxBanknote = num;
            }
        }

        exchangeOfBanknotes(amount, maxBanknote, banknotes, valueList, result);
        printExchangeOfBanknotes(result, amount, banknotes);

        return result;
    }

    /**
     * Подпрограмма для размена указанной купюры всеми возможными способами (ввод через с преобразованными параметрами).
     *
     * @param result массив массивов, в который будет записан результат.
     * @param amount размениваемая сумма.
     * @param valueList массив для хранения одной (текущей) комбинации купюр.
     * @param minvalue минимальная купюра из имеющихся для размена.
     * @param banknotes массив купюр.
     */
    private static void exchangeOfBanknotes(long amount, long minvalue, long[] banknotes, List<Long> valueList, List<List<Long>> result) {
        if (amount == 0) {
            result.add(new ArrayList<>(valueList));
            return;
        }
        long b;
        int i;
        for (i = 0; i < banknotes.length; i++) {
            b = banknotes[i];
            if ((minvalue >= b) && (amount >= b)) {
//                List<Long> copyValueList = new ArrayList<>(valueList);
                valueList.add(b);
                exchangeOfBanknotes(amount - b, b, banknotes, valueList, result);
                valueList.remove(valueList.size() - 1);
            }
        }
    }
}
