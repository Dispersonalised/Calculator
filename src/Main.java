import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        calculator cr = new calculator();
        cr.Start();
    }
}
class calculator {
    private final char[] action_s = {'+', '-', '*', '/'};
    private final char[] number_s = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Scanner sc = new Scanner(System.in);
    String line;

    int result;

    public void Start(){
        System.out.println("Введите выражение используя только два числа от 0 до 9: ");
        line = sc.nextLine();
        Operation();
    }

    private void Operation(){
        String[] elements = new String[line.length()];
        int elementsCount = 0;
        for(int i = 0; i < line.length(); i++) {
            if(elements[i] == null) {
                for (char action : action_s) {
                    if (line.charAt(i) == action) {
                        elements[i] = String.valueOf(action);
                        elementsCount++;
                    }
                }
            }
            if(elements[i] == null) {
                boolean i_number = false;
                for (char number : number_s) {
                    if (line.charAt(i) == number) {
                        elements[i] = String.valueOf(number);
                        i_number = true;
                        elementsCount++;
                    }
                }
                for (char number : number_s) {
                    if(i+1 < line.length()) {
                        if (line.charAt(i+1) == number && i_number) {
                            System.out.println("ошибка, вы ввели двузначное число");
                            return;
                        }
                    }
                }
            }
        }
        String[] newArray = new String[elementsCount];
        newArray = FixedArray(elementsCount, elements, newArray);
        elements = newArray;
        if(elementsCount > 3)
        {
            System.out.println("ошибка, превышен допустимый лимит выражения");
            return;
        }
        if(elementsCount == 3 && elements != null){
            switch (elements[1]) {
                case "+" -> result = Integer.parseInt(elements[0]) + Integer.parseInt(elements[2]);
                case "-" -> result = Integer.parseInt(elements[0]) - Integer.parseInt(elements[2]);
                case "*" -> result = Integer.parseInt(elements[0]) * Integer.parseInt(elements[2]);
                case "/" -> result = Integer.parseInt(elements[0]) / Integer.parseInt(elements[2]);
            }
            System.out.println(result);
        }
        else if (elementsCount < 3) {
            System.out.println("ошибка, неверное выражение");
        }
    }

    private String[] FixedArray(int count, String[] elements, String[] newArray) {
        int numberLine = 0;
        int actionLine = 1;
        int offset = 0;
        for(int i = 0; i < count; i++) {
            if(i+offset < elements.length) {
                if(elements[i+offset] == null) {
                    offset++;
                    i--;
                }
            }
            if(elements[i+offset] != null) {
                if(i == numberLine) {
                    boolean char_detected = false;
                    for (char number : number_s) {
                        if(elements[i+offset].equals(String.valueOf(number))) {
                            newArray[i] = elements[i+offset];
                            numberLine = numberLine+2;
                            char_detected = true;
                        }
                    }
                    if(!char_detected) {
                        System.out.println("ошибка, неверное выражение");
                        return null;
                    }
                }
                if(i == actionLine) {
                    boolean char_detected = false;
                    for (char action : action_s) {
                        if(elements[i+offset].equals(String.valueOf(action))) {
                            newArray[i] = elements[i+offset];
                            actionLine = actionLine+2;
                            char_detected = true;
                        }
                    }
                    if(!char_detected) {
                        System.out.println("ошибка, неверное выражение");
                        return null;
                    }
                }
            }
        }
        return newArray;
    }
}