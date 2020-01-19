package io.github.sczerepko.tetris.model.pieces;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPieceGenerator {

    private static final List<? extends PieceRepresentation> PIECE_REPRESENTATIONS = List.of(
            PieceOfShapeI.getInstance(), PieceOfShapeL.getInstance(), PieceOfShapeO.getInstance(), PieceOfShapeS.getInstance(),
            PieceOfShapeT.getInstance(), PieceOfShapeZ.getInstance(), PieceOfShapeJ.getInstance()
    );

    private Piece currentPiece;
    private Piece nextPiece;

    public RandomPieceGenerator() {
        this.currentPiece = generate();
        this.nextPiece = generate();
    }

    public Piece generate() {
        int random = ThreadLocalRandom.current().nextInt(PIECE_REPRESENTATIONS.size());
        PieceRepresentation randomPieceRepresentation = PIECE_REPRESENTATIONS.get(random);
        currentPiece = nextPiece;
        nextPiece = new Piece(randomPieceRepresentation);
        return currentPiece;
    }

    public Piece peek() {
        return nextPiece;
    }

}
