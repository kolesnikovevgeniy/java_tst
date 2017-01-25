import model.Point;
public class Test
{
	public static void main(String[] args)
	{
		// примеры вызовов
		distance(new Point(10,10), new Point(5,5));
		System.out.println("distance(new Point(1,1), new Point(0,0)) = " + Double.toString(distance(new Point(-1,1), new Point(5,5))));

		Point p = new Point(10, 10);
		System.out.println("p.distance(7,7) = " + Double.toString(p.distance(7,7)));

		System.out.println("p.distance(new Point(5,5) = " + Double.toString(p.distance(new Point(5,5))));

		System.out.println("Point.distance(6,6,1,1) = " + Double.toString(Point.distance(6,6,1,1)));

	}

	// вычисление расстояния между точками
	public static double distance(Point p1, Point p2)
	{
		return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
	}
}