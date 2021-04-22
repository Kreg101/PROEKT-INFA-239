package problem;

import javax.media.opengl.GL2;
import java.util.ArrayList;

public class Line {
    double DELTA = 0.0001;
    Point p1, p2; // координаты
    double A, B, C; // уравнение прямой
    /*
    расстояние до точки
     */
    public double distanceToPoint(Point p){
        return Math.abs((A*p.x+ B*p.y + C)/(Math.sqrt(A*A + B*B)));
    }
    /*
    проверка принадлежности точки отрезку
     */
    public boolean belong(Point p){
        return Math.abs(p1.distanceTo(p2) - p.distanceTo(p1) - p.distanceTo(p2)) < DELTA;
    }
    /*
    проверка открезков на параллельность
     */
    public boolean isParallel(Line l){
        if(Math.abs(A) > DELTA && Math.abs(B) > DELTA && Math.abs(l.A) > DELTA && Math.abs(B) > 0)
            return(Math.abs(A* l.B - B * l.A) < DELTA);
        else if( Math.abs(B) < DELTA && Math.abs(l.B) < DELTA)
            return true;
        else if (Math.abs(A) < DELTA && Math.abs(l.A) < DELTA)
            return true;
        else
            return false;
    }
    /*
    построение параллельной линии через точку
     */
    public Line parallelLine(Point point) {
        double a = A;
        double b = B;
        double c = -(a * point.x + b * point.y);
        return new Line(a, b, c);
    }
    /*
    построение перендикуляра через точку
     */
    public Line perpendicularLine(Point point) {
        double a = -B * B;
        double b = A * B;
        double c = -(a * point.x + b * point.y);
        return new Line(a, b, c);
    }
    /*
  пересечение прямых, с ограничем той прямой, относительно которой запускается метод
   */
    public Point intersection(Line line) {
        if (isParallel(line)) return null;
        else {
            double a = line.A, b = line.B, c = line.C;
            double X = (b * C / B - c) / (a - A * b / B);
            double Y = -(A * X + C) / B;
            Point t = new Point(X, Y);
            if(this.belong(t))
                return t;
            else
                return null;
        }
    }

    public void render(GL2 gl){
        Figures.renderLine(gl, p1, p2, 3);
    }

    Line(){
        p1 = new Point(0,0);
        p2 = new Point(1,1);
        A = p2.y- p1.y;
        B = p1.x - p2.x;
        if (B == 0) B = 0.0000001;
        C = p1.x* p2.y- p2.x* p1.y;
    }

    Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
        A = p1.y - p2.y;
        B = p2.x- p1.x;
        if (B == 0) B = 0.0000001;
        C = p1.x * p2.y - p2.x * p1.y;
    }

    Line(double A, double B, double C){
        this.A = A;
        this.B = B;
        this.C = C;
    }

}

