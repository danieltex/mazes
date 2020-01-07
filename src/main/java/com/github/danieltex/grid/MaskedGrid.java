package com.github.danieltex.grid;

public class MaskedGrid extends Grid {
    private Mask mask;

    private MaskedGrid(GridFactory gridFactory) {
        super(gridFactory);
    }

    public MaskedGrid(Mask mask) {
        this(new MaskedGridFactory(mask));
        this.mask = mask;
    }

    public Mask getMask() {
        return mask;
    }

    @Override
    public Cell randomCell() {
        int[] location = mask.randomLocation();
        return cellAt(location[0], location[1]);
    }

    @Override
    public int size() {
        return mask.count();
    }
}