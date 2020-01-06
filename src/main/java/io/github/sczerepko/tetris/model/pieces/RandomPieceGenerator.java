package io.github.sczerepko.tetris.model.pieces;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomPieceGenerator {

    private static final List<Supplier<? extends Piece>> pieceSuppliers = List.of(
            IPiece::new, LPiece::new, SPiece::new, SquarePiece::new
    );

    public static Piece generate() {
        int random = ThreadLocalRandom.current().nextInt(pieceSuppliers.size());
        Supplier<? extends Piece> randomPieceSupplier = pieceSuppliers.get(random);
        return randomPieceSupplier.get();
    }

    private RandomPieceGenerator() {
    }

}
