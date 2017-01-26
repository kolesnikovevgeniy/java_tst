package model;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by MyK on 25.01.17.
 */
public class PointTest {
    @Test
    public void testStaticFunc()
    {
        // проверка на правильность
        Assert.assertEquals(Point.distance(0,1,1,1), 1.0);

        //Проверка на падение
        Assert.assertNotEquals(Point.distance(0,1,1,1), 2.0);
    }
    
    @Test
    public void testMethod1()
    {
        Point p = new Point(2,2);

        // проверка на правильность
        Assert.assertEquals(p.distance(2,4), 2.0);

        //Проверка на падение
        Assert.assertNotEquals(p.distance(2,4), 7.0);

    }

    @Test
    public void testMethod2()
    {
        Point p = new Point(4,4);

        // проверка на правильность
        Assert.assertEquals(p.distance(new Point(2,4)), 2.0);

        //Проверка на падение
        Assert.assertNotEquals(p.distance(new Point(2,4)), 15.0);

    }
}
