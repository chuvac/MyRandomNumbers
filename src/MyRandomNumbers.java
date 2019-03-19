
import java.io.*;
import java.util.*;

public class MyRandomNumbers {
    private static int countOfNumbers;
    private static int countOfNumberSets;
    private static Map<String, String> map;
    private static int[] arr;
    private static int countOfRepeatedNum;
    private static int min;
    private static int max;

    public static void main(String[] args) {
        System.out.println("Программа сохраняет уникальные наборы чисел");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.print("Введите диапазон значений: min = ");
            min = Integer.parseInt(reader.readLine());
            System.out.print("; max = ");
            max = Integer.parseInt(reader.readLine());
            System.out.println();
            System.out.print("Введите количество чисел в наборе: ");
            countOfNumbers = Integer.parseInt(reader.readLine());
            //System.out.println(countOfNumbers);

            System.out.print("Введите количество уникальных наборов: ");
            countOfNumberSets = Integer.parseInt(reader.readLine());
            //System.out.println(countOfNumberSets);
        } catch (IOException e) {

        }



        map = new HashMap();

        int countOfRepeatedSets = 0;
        countOfRepeatedNum = 0;

        System.out.println("Выполняется...");
        //заполнение Set уникальными комбинациями чисел
        for (int i = 0; i < countOfNumberSets; i++) {
            boolean isAddedInSet = false;
            String s = "";
            String key;
            while(!isAddedInSet) {
                randomArr();
                s = Arrays.toString(arr).replace(", ", "\t").replace("[", "").replace("]", "");
                //System.out.println(arr);
                //s = Arrays.toString(arr);
                Arrays.sort(arr);
                key = Arrays.toString(arr).replace(", ", "\t").replace("[", "").replace("]", "");
                System.out.println(key);
                isAddedInSet = map.put(key, s) == null;
                if(!isAddedInSet) {
                    countOfRepeatedSets++;
                }
            }
            //System.out.println("Набор " + (i + 1) + ": " + s);
            boolean isHundred = (i + 1) % 100 == 0;
            if(isHundred) {
                System.out.println((i + 1) + " наборов");
            }
        }
        System.out.println("Выполнено!");
        System.out.println("Количество повторов наборов " + countOfRepeatedSets);
        System.out.println("Количество повторов номеров " + countOfRepeatedNum);
        writeInFile();
        //reader.readLine();

    }
    //заполнение массива случайными неповторяющимися числами
    private static void randomArr() {
        arr = new int[countOfNumbers];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        int tempInt;
        boolean isExistInArr;
        boolean isFull = false;

        while (!isFull) {
            isExistInArr = false;
            tempInt = (min + ((int)(Math.random() * (max - min + 1))));
            while (!isExistInArr) {
                for (int i = 0; i < arr.length; i++) {
                    if (tempInt == arr[i]) {
                        isExistInArr = true;
                        countOfRepeatedNum++;
                        break;
                    } else if (arr[i] == 0){
                        arr[i] = tempInt;
                        //System.out.print(arr[i] + " ");
                        isExistInArr = true;
                        break;
                    }

                }
            }
            if (arr[arr.length - 1] != 0) {
                isFull = true;
                //System.out.println();
            }
        }

    }

    private static void writeInFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rand.txt"))) {

            for (int i = 0; i < countOfNumbers; i++) {
                String str;
                if (i != (countOfNumbers - 1)) {
                    str = "\t";
                } else {
                    str = "";
                }
                writer.write((i+1) + str);
            }
            writer.newLine();
            for (String value: map.values()){
                writer.write(value);
                writer.newLine();
                //System.out.println("Запись " + value);
            }
            System.out.println("Запись завершена.");
        } catch (Exception e) {
            System.out.println("Ошибка записи в файл");
        }

    }
}