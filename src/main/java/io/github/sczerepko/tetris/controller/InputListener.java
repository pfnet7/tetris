package io.github.sczerepko.tetris.controller;

import io.github.sczerepko.tetris.model.pieces.CurrentPiece;

import java.util.EventListener;

interface InputListener extends EventListener {

    void startNewGame();

    CurrentPiece onDownEvent();

    CurrentPiece onRightEvent();

    CurrentPiece onLeftEvent();

    CurrentPiece onRotateEvent();

}
