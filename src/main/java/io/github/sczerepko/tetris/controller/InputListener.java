package io.github.sczerepko.tetris.controller;

import java.util.EventListener;

interface InputListener extends EventListener {

    void startNewGame();

    void onDownEvent();

    void onRightEvent();

    void onLeftEvent();

    void onRotateEvent();

}
