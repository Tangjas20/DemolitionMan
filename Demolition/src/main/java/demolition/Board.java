package demolition;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
    String[][] firstBoard = new String[13][15];
    String[][] secondBoard = new String[13][15];
    public Board(){
    }

    public String[][] make1stBoard() {

        try{
            File firstFile = new File("level1.txt");
            Scanner scanner = new Scanner(firstFile);
            int i = 0;

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                firstBoard[i] = data.split("(?!^)");
                i++;
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("No such file.");
        }
        return firstBoard;
    }

    public String[][] make2ndBoard() {

        try{
            File secondFile = new File("level2.txt");
            Scanner scanner2 = new Scanner(secondFile);
            int i = 0;
            while(scanner2.hasNextLine()){
                String data = scanner2.nextLine();
                secondBoard[i] = data.split("(?!^)");
                i++;
            }
            scanner2.close();
        } catch(FileNotFoundException e) {
            System.out.println("No such file.");
        }
        return secondBoard;
    }

    public void get1stBoard() {
        for(int i = 0; i < firstBoard.length; i++){
            if(i >= 1)
                System.out.print("\n");
            for(int j = 0; j < firstBoard[0].length; j++){
                System.out.print(firstBoard[i][j]);
            }
        }
    }

    public String[][] get2ndBoard() {
        return null;
    }

    public static void main(String[] args){
        /*Board board = new Board();
        board.make1stBoard();
        board.make2ndBoard();
        System.out.println(board.firstBoard[1][1]);
        System.out.println(board.secondBoard[5][1]);
        */
    }

}
