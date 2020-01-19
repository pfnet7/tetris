package io.github.sczerepko.tetris.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

class MatrixOperations {

    private MatrixOperations() {
    }

    public static boolean isMoveValid(Color[][] boardMatrix, Color[][] pieceMatrix, int x, int y) {
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                int tempX = x + i;
                int tempY = y + j;
                if (pieceMatrix[i][j] != null && (isOutOfBounds(tempX, tempY, boardMatrix) || boardMatrix[tempX][tempY] != null)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isOutOfBounds(int x, int y, Color[][] boardMatrix) {
        return x < 0 || x >= boardMatrix.length || y >= boardMatrix[0].length;
    }

    public static void embedPiece(Color[][] boardMatrix, Color[][] pieceMatrix, int x, int y) {
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                int newX = x + i;
                int newY = y + j;
                if (pieceMatrix[i][j] != null) {
                    boardMatrix[newX][newY] = pieceMatrix[i][j];
                }
            }
        }
    }

    public static Set<Integer> calculateFullRowsIndexes(final Color[][] boardMatrix) {
        List<Integer> nonEmptyCellIndexes = new ArrayList<>();
        for (Color[] column : boardMatrix) {
            List<Integer> nonEmptyColumnCellIndexes = IntStream.range(0, column.length)
                                                               .filter(i -> column[i] != null)
                                                               .boxed()
                                                               .collect(toList());
            nonEmptyCellIndexes.addAll(nonEmptyColumnCellIndexes);
        }
        Map<Integer, Long> indexesGroupedByCount = nonEmptyCellIndexes.stream()
                                                                      .collect(groupingBy(Function.identity(), Collectors.counting()));
        return indexesGroupedByCount.entrySet()
                                    .stream()
                                    .filter(entry -> entry.getValue() == boardMatrix.length)
                                    .map(Map.Entry::getKey)
                                    .collect(toSet());
    }

    public static Color[][] deleteFullRows(Set<Integer> fullRowIndexes, Color[][] boardMatrix) {
        int width = boardMatrix.length;
        int height = boardMatrix[0].length;
        int numberOfFullRows = fullRowIndexes.size();
        Color[][] newBoard = new Color[width][height];
        Integer minRowIndex = fullRowIndexes.stream()
                                            .min(Integer::compareTo)
                                            .orElseThrow(IllegalArgumentException::new);
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                if (!fullRowIndexes.contains(j)) {
                    if (j < minRowIndex) {
                        newBoard[i][j + numberOfFullRows] = boardMatrix[i][j];
                    } else {
                        newBoard[i][j] = boardMatrix[i][j];
                    }
                }
            }
        }
        return newBoard;
    }

}
