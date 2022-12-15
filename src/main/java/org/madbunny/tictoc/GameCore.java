package org.madbunny.tictoc;


import org.madbunny.vsrat2d.api.*;


public class GameCore {
    private static final Color BACKGROUND_COLOR = new Color(0.2f, 0.2f, 0.2f);
    private static final Color TEXT_COLOR = new Color(1, 0, 0);


    private final int windowWidth;
    private final int windowHeight;
    private int mainFontId;

    private Desk desk = new Desk();

    public GameCore(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void initialize(ApplicationContext ctx) {
        mainFontId = ctx.fonts().createFont("arial.ttf", 24);
    }

    public void update(FrameContext ctx) {

        ctx.screen().clear(BACKGROUND_COLOR);
        DrawManager dm = new DrawManager(ctx);
        dm.drawDesk();
        desk.fillDesk(dm);
        int[] winner = desk.findWinner();
        if (winner != null) {
            dm.drawLine(winner);
        }

        MouseInput mouse = ctx.mouse();
        if (mouse.isButtonClicked(MouseButton.LEFT)) {
            dm.checkArea(mouse.getMousePosition().x(), mouse.getMousePosition().y(), desk);
        }
        if (mouse.isButtonClicked(MouseButton.RIGHT)) {
            desk.clean();
        }

    }


}
