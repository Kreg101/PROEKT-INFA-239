package problem;

import javax.media.opengl.GL2;

public class Point {
    public double x, y;

    /*
    конструктор точки
    */
    Point() {
        x = 0;
        y = 0;
    }

    /*
    конструктор точки
    */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /*
    расстояние до точки
    */
    public double distanceTo(Point p) {
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
    }

    //Рисование точки через класс Figures
    void render(GL2 gl, double W) {
        Figures.renderPoint(gl, this, W);
    }

    void render(GL2 gl) {
        render(gl, 4);
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f)",this.x,this.y);
    }
}