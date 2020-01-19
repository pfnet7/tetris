package io.github.sczerepko.tetris.model.pieces;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomPieceGenerator {

    private static final List<Supplier<? extends Piece>> PIECE_SUPPLIERS = List.of(
            PieceOfShapeI::new, PieceOfShapeL::new, PieceOfShapeO::new, PieceOfShapeS::new,
            PieceOfShapeT::new, PieceOfShapeZ::new, PieceOfShapeJ::new
    );

    private CurrentPiece currentPiece;
    private CurrentPiece nextPiece;

    public RandomPieceGenerator() {
        this.currentPiece = generate();
        this.nextPiece = generate();
    }

    public CurrentPiece generate() {
        int random = ThreadLocalRandom.current().nextInt(PIECE_SUPPLIERS.size());
        Supplier<? extends Piece> randomPieceSupplier = PIECE_SUPPLIERS.get(random);
        currentPiece = nextPiece;
        nextPiece = new CurrentPiece(randomPieceSupplier.get());
        return currentPiece;
    }

    public CurrentPiece peek() {
        return nextPiece;
    }

}
