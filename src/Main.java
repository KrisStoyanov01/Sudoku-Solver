import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String FILE_LOCATION = "I:\\Sudoku Solver\\src\\Puzzle.txt";

    public static void main(String[] args) throws FileNotFoundException {
	    Integer[][] grid = new Integer[9][9];
	    fillGrid(grid);

        System.out.println(grid);
    }

    public static void fillGrid(Integer[][] grid) throws FileNotFoundException {
        File file = new File(FILE_LOCATION);
        Scanner sc = new Scanner(file);

        List<String> rows = new ArrayList<>();
        while (sc.hasNextLine()) {
            rows.add(sc.nextLine());
        }

        for(int i = 0; i < 9; i++){
            String[] tokens = rows.get(i).split(" ");
            for(int j = 0; j < 9; j++){
                grid[i][j] = Integer.parseInt(tokens[j]);
            }
        }
    }

    
}
