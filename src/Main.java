import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String READ_FILE_LOCATION = "I:\\Sudoku Solver\\src\\Puzzle.txt";
    public static final String WRITE_FILE_LOCATION = "I:\\Sudoku Solver\\src\\Solution.txt";

    public static void main(String[] args) throws IOException {
	    List<Square> grid = new ArrayList<>();
	    fillGrid(grid);
        solveGrid(grid);
        writeToFile(grid);
        System.out.println("Solved");
    }

    public static void fillGrid(List<Square> grid) throws FileNotFoundException {
        File file = new File(READ_FILE_LOCATION);
        Scanner sc = new Scanner(file);

        List<String> rows = new ArrayList<>();
        while (sc.hasNextLine()) {
            rows.add(sc.nextLine());
        }

        for(int i = 0; i < 9; i++){
            String[] tokens = rows.get(i).split(" ");
            for(int j = 0; j < 9; j++){
                Integer value = Integer.parseInt(tokens[j]);
                Square toBeAdded = new Square(j, i, value);
                grid.add(toBeAdded);
            }
        }
    }

    public static void solveGrid(List<Square> grid){
        Boolean isItReturning = false;
        for(int i = 0; i < grid.size(); ){
            Square currentSquare = grid.get(i);
            if(isItReturning && currentSquare.getIsClue()){
                i--;
            }else {
                if (!currentSquare.getIsClue()) {
                    Boolean isValidForSquare = false;
                    while (true) {
                        currentSquare.setValue(currentSquare.getValue() + 1);
                        if (isGridValid(currentSquare, grid)) {
                            isValidForSquare = true;
                            i++;
                            break;
                        }
                        if (currentSquare.getValue() > 9) {
                            break;
                        }
                    }

                    if (!isValidForSquare) {
                        isItReturning = true;
                        i--;
                    }
                }
            }
        }
    }

    public static Boolean isGridValid(Square square, List<Square> grid){
        List<Square> sameRow = new ArrayList<>();
        List<Square> sameColumn = new ArrayList<>();
        List<Square> sameSubGrid = new ArrayList<>();
        for (Square square1 : grid) {
            if(square1.getX() == square.getX()){
                sameRow.add(square1);
            }
            if(square1.getY() == square.getY()){
                sameColumn.add(square1);
            }
            if(square1.getSubGridIndex() == square.getSubGridIndex()){
                sameSubGrid.add(square1);
            }
        }
        if(isThisPartValid(sameRow) && isThisPartValid(sameColumn) && isThisPartValid(sameSubGrid)){
            return true;
        }else{
            return false;
        }
    }

    public static Boolean isThisPartValid(List<Square> squares){
        List<Integer> values = new ArrayList<>();
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (Square square : squares) {
            values.add(square.getValue());
        }

        for (Integer value : values) {
            if(!validNumbers.contains(value)){
                return false;
            }else{
                validNumbers.remove(value);
                // TODO: Make sure it works
            }
        }
        return true;
    }

    public static void writeToFile(List<Square> grid) throws IOException {
        List<String> lines = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                lines.add(grid.get(i * 9 + j).getValue() + " ");
            }
        }

        Path file = Paths.get(WRITE_FILE_LOCATION);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }
}
