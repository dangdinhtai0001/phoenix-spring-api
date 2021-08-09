/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.math;

import com.phoenix.common.maths.Area;
import org.junit.Test;

public class TestArea {
    @Test
    public void testCalculateArea(){
        /* test cube */
        assert Double.compare(Area.surfaceAreaCube(1), 6.0) == 0;

        /* test sphere */
        assert Double.compare(Area.surfaceAreaSphere(5), 314.1592653589793) == 0;
        assert Double.compare(Area.surfaceAreaSphere(1), 12.566370614359172) == 0;

        /* test rectangle */
        assert Double.compare(Area.surfaceAreaRectangle(10, 20), 200.0) == 0;

        /* test square */
        assert Double.compare(Area.surfaceAreaSquare(10), 100.0) == 0;

        /* test triangle */
        assert Double.compare(Area.surfaceAreaTriangle(10, 10), 50.0) == 0;

        /* test parallelogram */
        assert Double.compare(Area.surfaceAreaParallelogram(10, 20), 200.0) == 0;

        /* test trapezium */
        assert Double.compare(Area.surfaceAreaTrapezium(10, 20, 30), 450.0) == 0;

        /* test circle */
        assert Double.compare(Area.surfaceAreaCircle(20), 1256.6370614359173) == 0;

    }
}
