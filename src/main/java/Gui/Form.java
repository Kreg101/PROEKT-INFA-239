package Gui;

import problem.Point;
import problem.Problem;
import problem.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Класс формы приложения
 */
public class Form extends JFrame {
    /**
     * панель для отображения OpenGL
     */
    private JFrame frame = new JFrame();

    //JPanel
    private JPanel GLPlaceholder;
    private JPanel root;

    //надписи
    private JLabel problemText;
    private JLabel addRectangleText;

    //треугольник
    private JTextField xApex1Field;
    private JTextField xApex2Field;
    private JTextField xApex3Field;

    private JTextField yApex1Field;
    private JTextField yApex2Field;
    private JTextField yApex3Field;

    private JButton loadFromFileBtn;
    private JButton saveToFileBtn;
    private JButton clearBtn;
    private JButton solveBtn;

    private JButton addTriangleBth;
    private JLabel solveText;
    private JButton addPointBth;
    private JButton addRandomPoint;
    private JTextField cntPoint;
    private JTextField xPoint;
    private JTextField yPoint;
    private JButton rndTriangleBth;


    /**
     * таймер
     */
    private final Timer timer;
    /**
     * рисовалка OpenGL
     */
    private final RendererGL renderer;

    /**
     * Конструктор формы
     */
    private Form() {
        super(Problem.PROBLEM_CAPTION);
        // инициализируем OpenGL
        renderer = new RendererGL();
        // связываем элемент на форме с рисовалкой OpenGL
        GLPlaceholder.setLayout(new BorderLayout());
        GLPlaceholder.add(renderer.getCanvas());
        // указываем главный элемент формы
        getContentPane().add(root);

        setSize(getPreferredSize());
        // показываем форму
        setVisible(true);
        // обработчик зарытия формы
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(() -> {
                    renderer.close();
                    timer.stop();
                    System.exit(0);
                }).start();
            }
        });
        // тинициализация таймера, срабатывающего раз в 100 мсек
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTime();
            }
        });
        timer.start();
        //renderer.problem.solve();
        initWidgets();
    }

    public void setSolveText() {
        if (renderer.problem.TruePoint[0]!=null && renderer.problem.TruePoint[1]!=null)
            solveText.setText("Отрезок с концами " + renderer.problem.TruePoint[0]+ " и " + renderer.problem.TruePoint[1]);
        else
            solveText.setText("Нет решения");

    }

    /**
     * Инициализация виджетов
     */
    private void initWidgets() {
        // задаём текст полю описания задачи
        problemText.setText("<html>" + Problem.PROBLEM_TEXT.replaceAll("\n", "<br>"));

        addTriangleBth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x1 = Double.parseDouble(xApex1Field.getText());
                double y1 = Double.parseDouble(yApex1Field.getText());
                double x2 = Double.parseDouble(xApex2Field.getText());
                double y2 = Double.parseDouble(yApex2Field.getText());
                double x3 = Double.parseDouble(xApex3Field.getText());
                double y3 = Double.parseDouble(yApex3Field.getText());
                Point
                        Point1 = new Point(x1, y1),
                        Point2 = new Point(x2, y2),
                        Point3 = new Point(x3, y3);
                Triangle tr = new Triangle(Point1, Point2, Point3);
                renderer.problem.treug = tr;
            }
        });

        addRandomPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = (int) Double.parseDouble(cntPoint.getText());
                renderer.problem.getRandomPoint(n);
            }
        });

        rndTriangleBth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.getRandomTriangle();
            }
        });

        addPointBth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(xPoint.getText());
                double y = Double.parseDouble(yPoint.getText());
                renderer.problem.addPoint(x,y);
            }
        });

        loadFromFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.showOpenDialog(frame);
                renderer.problem.clear();
                renderer.problem.loadFromFile(fileOpen.getSelectedFile());
            }
        });

        saveToFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileSave = new JFileChooser();
                fileSave.showSaveDialog(frame);
                renderer.problem.saveToFile(fileSave.getSelectedFile());
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.clear();
            }
        });

        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.problem.solve();
                setSolveText();
            }
        });
    }

    /**
     * Событие таймера
     */
    private void onTime() {
        // события по таймеру
    }

    /**
     * Главный метод
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        new Form();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}