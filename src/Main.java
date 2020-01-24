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

        for (int i = 0; i < 9; i++) {
            String[] tokens = rows.get(i).split(" ");
            for (int j = 0; j < 9; j++) {
                Integer value = Integer.parseInt(tokens[j]);
                Square toBeAdded = new Square(j, i, value);
                grid.add(toBeAdded);
            }
        }
    }

    public static void solveGrid(List<Square> grid) {

        for (int i = 0; i < grid.size(); ) {
            String debug = "";
            if (grid.get(i).getIsClue()) {
                i++;
            } else {
                Boolean isValidForSquare = false;

                while (true) {
                    grid.get(i).setValue(grid.get(i).getValue() + 1);
                    if (isGridValid(grid.get(i), grid)) {
                        isValidForSquare = true;

                        break;
                    }
                    if (grid.get(i).getValue() > 9) {
                        break;
                    }
                }

                if (isValidForSquare) {
                    i++;
                }else{
                    grid.get(i).setValue(0);
                    i--;
                    while(grid.get(i).getIsClue()){
                        i--;
                    }
                }
            }
        }
    }

    public static Boolean isGridValid(Square square, List<Square> grid) {
        List<Square> sameRow = new ArrayList<>();
        List<Square> sameColumn = new ArrayList<>();
        List<Square> sameSubGrid = new ArrayList<>();

        for (Square square1 : grid) {
            if (square1.getX() == square.getX()) {
                sameColumn.add(square1);
            }
            if (square1.getY() == square.getY()) {
                sameRow.add(square1);
            }
            if (square1.getSubGridIndex() == square.getSubGridIndex()) {
                sameSubGrid.add(square1);
            }
        }
        String debug = "";
        if (isThisPartValid(sameRow) && isThisPartValid(sameColumn) && isThisPartValid(sameSubGrid)) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isThisPartValid(List<Square> squares) {
        List<Integer> values = new ArrayList<>();
        List<Integer> validNumbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            validNumbers.add(i);
        }
        for (Square square : squares) {
            values.add(square.getValue());
        }

        for (Integer value : values) {
            if (value != 0) {
                if (!validNumbers.contains(value)) {
                    return false;
                } else {
                    validNumbers.remove((Integer) value);
                    // TODO: Make sure it works
                }
            }
        }
        return true;
    }

    public static void writeToFile(List<Square> grid) throws IOException {
        List<String> lines = new ArrayList<>();
        String line = "";
        for (int i = 0; i < 9; i++) {
            line = "";
            for (int j = 0; j < 9; j++) {
                line += grid.get(i *9 + j).getValue() + " ";

            }
            lines.add(line);
        }

        Path file = Paths.get(WRITE_FILE_LOCATION);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }
}
