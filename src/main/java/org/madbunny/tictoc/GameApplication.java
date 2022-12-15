package org.madbunny.tictoc;

import org.madbunny.vsrat2d.api.*;

public class GameApplication {
    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 900;
    private static final String WINDOW_TITLE = "tic toc game";

    public static void main(String[] args) {
        var game = new GameCore(WINDOW_WIDTH, WINDOW_HEIGHT);
        new Application()
                .setWindowWidth(WINDOW_WIDTH)
                .setWindowHeight(WINDOW_HEIGHT)
                .setWindowTitle(WINDOW_TITLE)
                .setInitializer(game::initialize)
                .run(game::update);
        System.exit(0);
    }

}
