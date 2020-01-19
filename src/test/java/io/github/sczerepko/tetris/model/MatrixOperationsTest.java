package io.github.sczerepko.tetris.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static javafx.scene.paint.Color.BLUE;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Matrix Operations Test")
public class MatrixOperationsTest {

    @Nested
    @DisplayName("Test isMoveValid method")
    class IsMoveValidMethodTest {

        @Test
        @DisplayName("Check validity expecting false on full board")
        void testValidityCheckExpectingFalse() {
            Color[][] boardMatrix = new Color[][] {
                    {BLUE, BLUE, BLUE, BLUE, BLUE},
                    {BLUE, BLUE, BLUE, BLUE, BLUE},
                    {BLUE, BLUE, BLUE, BLUE, BLUE},
                    {BLUE, BLUE, BLUE, BLUE, BLUE},
                    {BLUE, BLUE, BLUE, BLUE, BLUE}
            };

            Color[][] pieceMatrix = new Color[][] {
                    {null, BLUE, null, null},
                    {null, BLUE, null, null},
                    {null, BLUE, null, null},
                    {null, BLUE, null, null}
            };

            boolean result = MatrixOperations.isMoveValid(boardMatrix, pieceMatrix, 0, 1);

            assertFalse(result, "No move should be valid on full board");
        }

        @Test
        @DisplayName("Check validity expecting true on empty board")
        void testValidityCheckExpectingTrue() {
            Color[][] boardMatrix = new Color[][] {
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
            };

            Color[][] pieceMatrix = new Color[][] {
                    {null, BLUE, null, null},
                    {null, BLUE, null, null},
                    {null, BLUE, null, null},
                    {null, BLUE, null, null}
            };

            boolean result = MatrixOperations.isMoveValid(boardMatrix, pieceMatrix, 0, 1);

            assertTrue(result, "Move should be valid on empty board");
        }

        @Test
        @DisplayName("Check validity expecting false on collision")
        void testValidityCheckExpectingFalseOnCollision() {
            Color[][] boardMatrix = new Color[][] {
                    {null, null, null, null, null},
                    {null, null, BLUE, null, null},
                    {null, null, BLUE, null, null},
                    {null, null, BLUE, null, null},
                    {null, null, BLUE, null, null}
            };

            Color[][] pieceMatrix = new Color[][] {
                    {null, BLUE, null, null},
                    {null, BLUE, null, null},
                    {null, BLUE, null, null},
                    {null, BLUE, null, null}
            };

            boolean result = MatrixOperations.isMoveValid(boardMatrix, pieceMatrix, 1, 1);

            assertFalse(result, "Move should not be valid on collision");
        }

    }

    @Test
    @DisplayName("Successfully embed piece into board matrix")
    void successfullyEmbedPieceIntoBoardMatrix() {
        Color[][] boardMatrix = new Color[][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
        };

        Color[][] pieceMatrix = new Color[][] {
                {null, BLUE, null, null},
                {null, BLUE, null, null},
                {null, BLUE, null, null},
                {null, BLUE, null, null}
        };

        MatrixOperations.embedPiece(boardMatrix, pieceMatrix, 0, 0);

        assertArrayEquals(new Color[][]{
                {null, BLUE, null, null, null},
                {null, BLUE, null, null, null},
                {null, BLUE, null, null, null},
                {null, BLUE, null, null, null},
                {null, null, null, null, null}
        }, boardMatrix);
    }

}
