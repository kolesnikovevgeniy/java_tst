package model;

/**
 * Created by MyK on 25.01.17.
 */
public class Point
{
    private int x = 0;
    private int y = 0;

    public Point ()
    {
        // инициализируем переменные
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y)
    {
        //инициализируем переменные
        this.x = x;
        this.y = y;
    }

    // геттер для X
    public int getX()
    {
        return x;
    }

    // гуттер для y
    public int getY()
    {
        return y;
    }

    // сеттер для x
    public void setX(int x)
    {
        this.x = x;
    }

    // сеттер y
    public void setY(int y)
    {
        this.y = y;
    }

    // вычисление по типу Point
    public double distance(Point p)
    {
        return _distance(this.x,this.y,p.getX(),p.getY());
    }

    // вычисление по 2 координатам
    public double distance(int x2, int y2)
    {
        return _distance(this.x, this.y, x2, y2);
    }

    //вычисление буз создания объекта
    public static double distance(int x, int y, int x2, int y2)
    {
        return _distance(x, y, x2, y2);
    }

    // внутренняя функция вычисление дистанции
    private static double _distance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
