package io.github.sczerepko.tetris.model.pieces;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * The Random piece generator that provides new pieces.
 *
 * @see PieceOfShapeI
 * @see PieceOfShapeL
 * @see PieceOfShapeO
 * @see PieceOfShapeS
 * @see PieceOfShapeT
 * @see PieceOfShapeZ
 * @see PieceOfShapeJ
 */
public class RandomPieceGenerator {

    private static final List<Supplier<? extends PieceRepresentation>> PIECE_REPRESENTATIONS_SUPPLIERS = List.of(
            PieceOfShapeI::new, PieceOfShapeL::new, PieceOfShapeO::new, PieceOfShapeS::new,
            PieceOfShapeT::new, PieceOfShapeZ::new, PieceOfShapeJ::new
    );

    private Piece currentPiece;
    private Piece nextPiece;

    public RandomPieceGenerator() {
        this.currentPiece = generate();
        this.nextPiece = generate();
    }

    /**
     * Assigns the next piece to the current one and creates the new next piece.
     *
     * @return the current piece
     */
    public Piece generate() {
        int random = ThreadLocalRandom.current().nextInt(PIECE_REPRESENTATIONS_SUPPLIERS.size());
        var randomPieceRepresentationSupplier = PIECE_REPRESENTATIONS_SUPPLIERS.get(random);
        currentPiece = nextPiece;
        nextPiece = new Piece(randomPieceRepresentationSupplier.get());
        return currentPiece;
    }

    /**
     * Gives access to the next piece without altering the current state.
     *
     * @return the next piece
     */
    public Piece peek() {
        return nextPiece;
    }

}
