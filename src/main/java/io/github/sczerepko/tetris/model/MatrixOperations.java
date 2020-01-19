package io.github.sczerepko.tetris.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * The utility class that provides various operations on matrices.
 */
class MatrixOperations {

    private MatrixOperations() {
    }

    /**
     * Checks if the move is valid.
     *
     * The move is considered valid if the nonempty tile in the piece does not cross the board boundaries
     * and does not collide with other taken places on the board.
     *
     * @param boardMatrix  the board matrix
     * @param pieceMatrix  the piece matrix
     * @param pieceOriginX the x coordinate of the upper left corner of the piece matrix
     * @param pieceOriginY the y coordinate of the upper left corner of the piece matrix
     * @return true if the move is valid, false otherwise
     */
    public static boolean isMoveValid(Color[][] boardMatrix, Color[][] pieceMatrix, int pieceOriginX, int pieceOriginY) {
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                int tempX = pieceOriginX + i;
                int tempY = pieceOriginY + j;
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

    /**
     * Embeds piece into the board matrix.
     *
     * @param boardMatrix  the board matrix
     * @param pieceMatrix  the piece matrix
     * @param pieceOriginX the x coordinate of the upper left corner of the piece matrix
     * @param pieceOriginY the y coordinate of the upper left corner of the piece matrix
     */
    public static void embedPiece(Color[][] boardMatrix, Color[][] pieceMatrix, int pieceOriginX, int pieceOriginY) {
        for (int i = 0; i < pieceMatrix.length; i++) {
            for (int j = 0; j < pieceMatrix[i].length; j++) {
                int newX = pieceOriginX + i;
                int newY = pieceOriginY + j;
                if (pieceMatrix[i][j] != null) {
                    boardMatrix[newX][newY] = pieceMatrix[i][j];
                }
            }
        }
    }

    /**
     * Calculates the indexes of the full rows on the board.
     *
     * It does so by counting nonempty cell indexes in each column and checking if they sum up to the row size.
     *
     * @param boardMatrix the board matrix
     * @return the set of the full rows indexes
     */
    public static Set<Integer> calculateFullRowsIndexes(Color[][] boardMatrix) {
        List<Integer> nonEmptyCellIndexes = new ArrayList<>();
        for (Color[] column : boardMatrix) {
            List<Integer> nonEmptyColumnCellIndexes = IntStream.range(0, column.length)
                                                               .filter(i -> column[i] != null)
                                                               .boxed()
                                                               .collect(toList());
            nonEmptyCellIndexes.addAll(nonEmptyColumnCellIndexes);
        }
        Map<Integer, Long> indexesGroupedByCount = nonEmptyCellIndexes.stream()
                                                                      .collect(groupingBy(identity(), counting()));
        return indexesGroupedByCount.entrySet()
                                    .stream()
                                    .filter(entry -> entry.getValue() == boardMatrix.length)
                                    .map(Map.Entry::getKey)
                                    .collect(toSet());
    }

    /**
     * Deletes full rows from the board matrix.
     * It takes into account the shift that needs to be made in the new matrix - the effect of falling pieces.
     *
     * @param fullRowIndexes the full row indexes
     * @param boardMatrix    the board matrix
     * @return the new board matrix
     */
    public static Color[][] deleteFullRows(Set<Integer> fullRowIndexes, Color[][] boardMatrix) {
        int width = boardMatrix.length;
        int height = boardMatrix[0].length;
        int numberOfFullRows = fullRowIndexes.size();
        Color[][] newBoard = new Color[width][height];
        Integer maxRowIndex = fullRowIndexes.stream()
                                            .max(Integer::compareTo)
                                            .orElseThrow(IllegalArgumentException::new);
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                if (!fullRowIndexes.contains(j)) {
                    if (j < maxRowIndex) {
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
