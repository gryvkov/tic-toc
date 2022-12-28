package org.madbunny.tictoc;

import org.madbunny.vsrat2d.api.Color;
import org.madbunny.vsrat2d.api.FrameContext;
import org.madbunny.vsrat2d.api.Paint;
import org.madbunny.vsrat2d.api.Point2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DrawManager {

    private final List<StripePoints> deskBorders = new LinkedList<>();
    private final List<Border> cellBorders = new ArrayList<>();
    private final float cellSize;
    private static final float RADIUS = 100;
    private final FrameContext ctx;
    private static final Paint BORDER_PAINT = new Paint(new Color(1, 0, 0), true);
    private static final Paint ZERO_PAINT = new Paint(new Color(1, 0, 0), false);


    public DrawManager(FrameContext ctx) {
        this.ctx = ctx;
        cellSize = ctx.screen().width / 3;

        deskBorders.add(new StripePoints(new Point2D(cellSize, 0),
                new Point2D(cellSize, ctx.screen().height)));
        deskBorders.add(new StripePoints(new Point2D(cellSize * 2, 0),
                new Point2D(cellSize * 2, ctx.screen().height)));
        deskBorders.add(new StripePoints(new Point2D(0, cellSize),
                new Point2D(ctx.screen().width, cellSize)));
        deskBorders.add(new StripePoints(new Point2D(0, cellSize * 2),
                new Point2D(ctx.screen().width, cellSize * 2)));

        cellBorders.add(0, new Border(cellSize, cellSize));
        cellBorders.add(1, new Border(cellSize, cellSize * 2));
        cellBorders.add(2, new Border(cellSize, cellSize * 3));
        cellBorders.add(3, new Border(cellSize * 2, cellSize));
        cellBorders.add(4, new Border(cellSize * 2, cellSize * 2));
        cellBorders.add(5, new Border(cellSize * 2, cellSize * 3));
        cellBorders.add(6, new Border(cellSize * 3, cellSize));
        cellBorders.add(7, new Border(cellSize * 3, cellSize * 2));
        cellBorders.add(8, new Border(cellSize * 3, cellSize * 3));

    }

    public void drawDesk() {
        for (StripePoints sp : deskBorders) {
            ctx.screen().drawStripe(sp.begin, sp.end, 2, BORDER_PAINT);
        }
    }

    public void drawCell(Cell cell) {
        float x = cell.x * cellSize + cellSize / 2;
        float y = cell.y * cellSize + cellSize / 2;
        if (cell.state == Cell.Value.ZERO) {
            drawZero(x, y);
        } else if (cell.state == Cell.Value.CROSS) {
            drawCross(x, y);
        }
    }

    private void drawZero(float x, float y) {
        for (StripePoints sp : deskBorders) {
            ctx.screen().drawCircle(new Point2D(x, y), RADIUS, ZERO_PAINT);
        }
    }

    private void drawCross(float x, float y) {
        ctx.screen().drawLine(new Point2D(x, y), new Point2D(x + RADIUS, y + RADIUS), ZERO_PAINT.color());
        ctx.screen().drawLine(new Point2D(x, y), new Point2D(x + RADIUS, y - RADIUS), ZERO_PAINT.color());
        ctx.screen().drawLine(new Point2D(x, y), new Point2D(x - RADIUS, y + RADIUS), ZERO_PAINT.color());
        ctx.screen().drawLine(new Point2D(x, y), new Point2D(x - RADIUS, y - RADIUS), ZERO_PAINT.color());
    }

    public void drawLine(int[] indexes) {
        float sx = cellBorders.get(indexes[0]).x - cellSize / 2;
        float sy = cellBorders.get(indexes[0]).y - cellSize / 2;

        float ex = cellBorders.get(indexes[2]).x - cellSize / 2;
        float ey = cellBorders.get(indexes[2]).y - cellSize / 2;

        ctx.screen().drawLine(new Point2D(sx, sy), new Point2D(ex, ey), ZERO_PAINT.color());
    }

    private record StripePoints(Point2D begin, Point2D end) {
    }

    public DeskCoordinates getCellCoordinates(float x, float y) {
        int dx = (int) Math.floor(x / cellSize);
        int dy = (int) Math.floor(y / cellSize);

        return new DeskCoordinates(dx, dy);
    }


}

record DeskCoordinates(int x, int y) {
}

class Border {
    public float x;
    public float y;

    public Border(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

