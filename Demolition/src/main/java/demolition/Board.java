package demolition;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
    
    String[][] madeBoard = new String[13][15];
    public Board(){
    }

    public String[][] makeBoard(String fileName) {

        try{
            File firstFile = new File(fileName);
            Scanner scanner = new Scanner(firstFile);
            int i = 0;

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                madeBoard[i] = data.split("(?!^)");
                i++;
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("No such file.");
        }
        return madeBoard;
    }


/*
    public void get1stBoard() {
        for(int i = 0; i < firstBoard.length; i++){
            if(i >= 1)
                System.out.print("\n");
            for(int j = 0; j < firstBoard[0].length; j++){
                System.out.print(firstBoard[i][j]);
            }
        }
    }*/

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
