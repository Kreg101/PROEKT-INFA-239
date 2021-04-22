package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Problem {

    public static final String PROBLEM_TEXT = "На плоскости задан треугольник и еще множество точек.\n " +
            "Необходимо найти такие две точки множества, что прямая, проходящая через эти две точки,\n " +
            "пересекает треугольник, и при этом отрезок этой прямой, оказавшейся внутри треугольника,\n " +
            "оказывается наибольшей длины. В качестве ответа хотелось бы видеть выделенные эти две точки,\n " +
            "прямую, через них проходящую, и этот отрезок.";


    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-7 класса Кравченко Егора";

    ArrayList<Point> points = new ArrayList<Point>();
    public Triangle treug;
    public Point[] TruePoint = new Point[2];
    public boolean ans;

    public void solve() {
        ans = false;
        double maxDist = 0;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                ArrayList<Point> t = treug.intersection(new Line(points.get(i), points.get(j)));
                if (t.size() > 0) {
                    double dist = t.get(0).distanceTo(t.get(1));
                    if (dist > maxDist) {
                        ans = true;
                        maxDist = dist;
                        TruePoint[0] = t.get(0);
                        TruePoint[1] = t.get(1);
                    }
                }
            }
        }
    }

    //добавить треугольник
    public void addTriangle(Point p1, Point p2, Point p3) {
        Triangle nTriangle = new Triangle(p1, p2, p3);
        treug = nTriangle;
    }

    public void addPoint(double x, double y) {
        Point point = new Point(x, y);
        points.add(point);
    }

    Random random = new Random();

    //получить случайный треугольник
    public void getRandomTriangle() {
        Point
                p1 = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1),
                p2 = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1),
                p3 = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);
        Triangle tr = new Triangle(p1, p2, p3);
        treug = tr;
    }

    public void getRandomPoint(int n) {
        for (int i = 0; i < n; i++) {
            Point p = new Point(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);
            points.add(p);
        }
    }

    //Загрузить задачу из файла
    public void loadFromFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            boolean tb = false, pb = false;

            while ((line = reader.readLine()) != null) {
                if (line.equals("triangle")) {
                    tb = true;
                    pb = false;
                } else if (line.equals("points")) {
                    tb = false;
                    pb = true;
                } else if (tb == true) {
                    String[] s = line.split(", ");


                    Point[] pT = new Point[3];

                    for (int i = 0; i < 3; i++) {
                        String pS = s[i].replace("(", "");
                        pS = pS.replace(")", "");
                        pS = pS.replace(",", ".");
                        String[] xyS = pS.split(";");
                        pT[i] = new Point(Double.parseDouble(xyS[0]), Double.parseDouble(xyS[1]));
                    }
                    treug = new Triangle(pT[0], pT[1], pT[2]);
                } else if (pb == true) {
                    String s = line.replace("(", "");
                    s = s.replace(")", "");
                    s = s.replace(",",".");
                    String[] xy = s.split("; ");
                    double px = Double.parseDouble(xy[0]),
                            py = Double.parseDouble(xy[1]);
                    points.add(new Point(px, py));
                }
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("ошибка чтения файла");
            ex.printStackTrace();
        }
    }

    //Сохранить задачу в файл
    public void saveToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("triangle\n");
            String s = treug.point1 + ", " + treug.point2 + ", " + treug.point3 + "\n";
            writer.write(s);

            writer.write("points\n");
            s = "";
            for (Point p : points) {
                s += (p + "\n");
            }

            writer.write(s);
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Очистить задачу
    public void clear() {
        ans = false;
        treug = null;
        TruePoint[0] = null;
        TruePoint[1] = null;
        points.clear();
    }

    public void render(GL2 gl) {
        if (treug != null) {
            treug.render(gl);
        }

        if (points.size() != 0) {
            for (Point p : points) {
                p.render(gl);
            }
        }

        if(ans == true){
            Line l = new Line(TruePoint[0], TruePoint[1]);
            l.render(gl);
        }
    }

}
