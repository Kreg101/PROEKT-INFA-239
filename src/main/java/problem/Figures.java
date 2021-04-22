package problem;

import javax.media.opengl.GL2;

import static javax.media.opengl.GL.*;

public class Figures {

    public static void renderPoint(GL2 gl, Point p, double W) {
        double x = p.x, y = p.y;


        gl.glPointSize((float) W);
        gl.glBegin(GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
    }

    public static void renderLine(GL2 gl, Point p1, Point p2, double W) {
        double x1 = p1.x, y1 = p1.y;
        double x2 = p2.x, y2 = p2.y;

        gl.glLineWidth((float) W);
        gl.glColor3d(1, 0, 1);
        gl.glBegin(GL_LINES);
        gl.glVertex2d(x1, y1);
        gl.glVertex2d(x2, y2);
        gl.glEnd();
    }

    public static void renderTriangle(GL2 gl, Point p1, Point p2, Point p3, boolean filled) {
        double x1 = p1.x, y1 = p1.y;
        double x2 = p2.x, y2 = p2.y;
        double x3 = p3.x, y3 = p3.y;
        if (!filled) {
            gl.glBegin(GL_LINE_STRIP);
            gl.glVertex2d(x1, y1);
            gl.glVertex2d(x2, y2);
            gl.glVertex2d(x3, y3);
            gl.glVertex2d(x1, y1);
            gl.glEnd();
        } else {
            gl.glBegin(GL_TRIANGLES);
            gl.glVertex2d(x1, y1);
            gl.glVertex2d(x2, y2);
            gl.glVertex2d(x3, y3);
            gl.glEnd();
        }
    }

    public static void renderQuad(GL2 gl, Point p1, Point p2, Point p3, Point p4, boolean filled) {

        double x1 = p1.x, y1 = p1.y;
        double x2 = p2.x, y2 = p2.y;
        double x3 = p3.x, y3 = p3.y;
        double x4 = p4.x, y4 = p4.y;

        if (!filled) {
            gl.glBegin(GL_LINE_STRIP);
            gl.glVertex2d(x1, y1);
            gl.glVertex2d(x2, y2);
            gl.glVertex2d(x3, y3);
            gl.glVertex2d(x4, y4);
            gl.glVertex2d(x1, y1);
            gl.glEnd();
        } else {
            gl.glBegin(GL_TRIANGLE_FAN);
            gl.glVertex2d(x1, y1);
            gl.glVertex2d(x2, y2);
            gl.glVertex2d(x3, y3);
            gl.glVertex2d(x4, y4);
            gl.glEnd();
        }
    }

    public static void renderCircle(GL2 gl, Point p, double R, boolean filled) {
        double radius = R;

        double x0 = p.x, y0 = p.y;

        double delta = 40;
        if (!filled) {
            gl.glBegin(GL_LINE_STRIP);


            for (int i = 0; i <= delta; i++) {
                double alpha = Math.toRadians(i * (360 / delta));

                double x = x0 + radius * Math.cos(alpha);
                double y = y0 + radius * Math.sin(alpha);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        } else {
            gl.glBegin(GL_TRIANGLE_FAN);

            gl.glVertex2d(x0, y0);
            for (int i = 0; i <= delta; i++) {
                double alpha = Math.toRadians(i * (360 / delta));

                double x = x0 + radius * Math.cos(alpha);
                double y = y0 + radius * Math.sin(alpha);
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        }
    }
}