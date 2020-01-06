package io.github.sczerepko.tetris.model.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CircularList<E> {

    private ArrayList<E> list;
    private int size;

    private CircularList(List<E> elements) {
        this.list = new ArrayList<E>(elements);
        size = elements.size();
    }

    @SafeVarargs
    public static <T> CircularList<T> of(T... elements) {
        return new CircularList<T>(Arrays.asList(elements));
    }

    public E get(int index) {
        return list.get(index % list.size());
    }

    public int size() {
        return size;
    }

}
