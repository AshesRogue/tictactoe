import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    // создаем генерики для позиций игрока и компьютера
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args) {
        char[][] gameboard = { { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' } };
        // создаем цикл для ввода координаты в поле
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter placements 1-9");
            int playerpos = scan.nextInt();
            while(playerPositions.contains(playerpos)|| cpuPositions.contains(playerpos)){
                System.out.println("position taken, choose another one!");
                playerpos = scan.nextInt();
            }

            // ход игрока
            System.out.println(playerpos);
            String result = checkWin();

            placeX(gameboard, playerpos, "Player");
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
            // ход компьютера
            Random rand = new Random();
            int cpupos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpupos)|| cpuPositions.contains(cpupos)){
                cpupos = rand.nextInt(9) + 1;
            }
            placeX(gameboard, cpupos, "cpu");



            printgameboard(gameboard);
            result = checkWin();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }
    }

    public static void printgameboard(char[][] gameboard) {
        for (char[] row : gameboard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeX(char[][] gameboard, int pos, String user) {
        char symbol = ' ';
        if (user.equals("Player")) {
            symbol = 'x';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'o';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;
            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    // создаем массивы для проверки победы
    public static String checkWin() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List lowRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        // добавляем значения в новый массив
        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(lowRow);

        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);

        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for(List l : winningConditions){
            if(playerPositions.containsAll(l)){
                return "You won!";
            }else if(cpuPositions.containsAll(l)){
                return "You lose!";
            }else if(playerPositions.size() + cpuPositions.size() == 9){
                return "Tie!";
            }
        }
        return "";
    }
}
