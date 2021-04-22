package problem;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class Triangle {
    Line[] lines = new Line[3];
    public Point point1, point2, point3;

    public ArrayList<Point> intersection(Line l) {
        ArrayList<Point> answer = new ArrayList<Point>(0);
        for (int i = 0; i < 3; i++) {
            Point temp = lines[i].intersection(l);
            if (temp != null)
                answer.add(temp);
        }
        return answer;
    }

    Triangle(Line[] parties) {
        this.lines = parties;
    }

    public Triangle(Point p1, Point p2, Point p3) {
        this.point1 = p1;
        this.point2 = p2;
        this.point3 = p3;

        lines[0] = new Line(p1, p2);
        lines[1] = new Line(p2, p3);
        lines[2] = new Line(p3, p1);
    }

    public void render(GL2 gl) {
        Figures.renderTriangle(gl, point1, point2, point3, false);
    }


}

