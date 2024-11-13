import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MatrixTest {

    @Test
    fun `Test for primary constructor fails`(){

        assertThrows(IllegalArgumentException::class.java) {
            Matrix(-2,3)
        }

        assertThrows(IllegalArgumentException::class.java) {
            Matrix(3,-3)
        }
    }

    @Test
    fun `Test for secondary constructors fails`(){

        assertThrows(IllegalArgumentException::class.java) {
            Matrix(
                arrayOf<Array<Double>>(
                    arrayOf<Double>(0.0, 0.0, 0.0, 0.0),
                    arrayOf<Double>(0.0, 0.0, 0.0, 0.0, 0.0),
                    arrayOf<Double>(0.0, 0.0, 0.0, 0.0)
                )
            )
        }

        assertThrows(IllegalArgumentException::class.java) {
            Matrix(2,3,
                2.2, 3.3, 4.5,
                    5.6, 4.89, 1.23,
                    2.45)
        }


    }

    @Test
    fun `Test for constructors`(){
        val mxPrimary=Matrix(3,4)
        for (r in 0 ..< mxPrimary.rowNum){
            for (c in 0 ..< mxPrimary.colNum){
                assertEquals(mxPrimary[r,c], 0.0 )
            }
        }


        val mxSecond1=Matrix(
            arrayOf<Array<Double>>(
                arrayOf<Double>(0.0, 0.0, 0.0, 0.0),
                arrayOf<Double>(0.0, 0.0, 0.0, 0.0),
                arrayOf<Double>(0.0, 0.0, 0.0, 0.0)
            )
        )
        for (r in 0 ..< mxSecond1.rowNum){
            for (c in 0 ..< mxSecond1.colNum){
                assertEquals(mxSecond1[r,c], 0.0 )
            }
        }


        val mxSecond2=Matrix(3,4,
            0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0
        )
        for (r in 0 ..< mxSecond2.rowNum){
            for (c in 0 ..< mxSecond2.colNum){
                assertEquals(mxSecond2[r,c], 0.0 )
            }
        }
    }

    @Test
    fun `Test for setter and getter`(){
        val mx=Matrix(4,3)

        mx[2,1]=7.4

        assertEquals(mx.get(2,1), 7.4)
    }

    @Test
    fun `Test for equal`(){
        val mx=Matrix(arrayOf<Array<Double>>(
                arrayOf<Double>(90.67, 72.3647, 55.82, 73.8),
                arrayOf<Double>(45.1, 78.234, 13.5678, 90.456),
                arrayOf<Double>(23.45, 67.8912, 5.678, 98.1)
        )
        )

        val mx2=Matrix(3, 4,
        90.67, 72.3647, 55.82, 73.8,
        45.1, 78.234, 13.5678, 90.456,
        23.45, 67.8912, 5.678, 98.1)

        assertEquals(mx, mx2)
    }


    @Test
    fun `Test for plus`(){

        val mx0_1=Matrix(2,3)
        val mx0_2=Matrix(2,2)

        assertThrows(IllegalArgumentException::class.java) {
            mx0_1+mx0_2
        }

        val mx1=Matrix(3, 4,
            3.14, 6.28, 9.42, 12.56,
            7.9, 10.0, 13.0, 16.0,
            19.1, 22.4, 25.67, 23.45)

        val mx2=Matrix(3, 4,
            45.6,78.12,13.5,90.34,
            1.2, 3.4, 5.0, 7.8,
            1.0, 4.0, 7.89, 10.11)

        val mxSum=Matrix(3,4,
            48.74, 84.40, 22.92, 102.90,
            9.1, 13.4, 18.0, 23.8,
            20.1, 26.4, 33.56, 33.56,
            )

        assertEquals(mxSum,mx1+mx2)
    }

    @Test
    fun `Test for minus`(){
        val mx0_1=Matrix(2,3)
        val mx0_2=Matrix(2,2)

        assertThrows(IllegalArgumentException::class.java) {
            mx0_1-mx0_2
        }


        val mx1=Matrix(3, 4,
            3.14, 6.28, 9.42, 12.56,
            7.9, 10.0, 13.0, 16.0,
            19.1, 22.4, 25.67, 23.45)

        val mx2=Matrix(3, 4,
            45.6,78.12,13.5,90.34,
            1.2, 3.4, 5.0, 7.8,
            1.0, 4.0, 7.89, 10.11)

        val mxRes=Matrix(3, 4,
            -42.46, -71.84, -4.08, -77.78,
            6.7, 6.6, 8.0, 8.2,
            18.1, 18.4, 17.78, 13.34)

        assertEquals(mxRes,mx1-mx2)

    }

    @Test
    fun `Test for unary minus`(){

        val mx1=Matrix(3, 4,
            3.14, 6.28, 9.42, 12.56,
            7.89, -10.12, 13.45, 16.78,
            19.01, 22.34, -25.67, 23.45)

        val mxRes=Matrix(3, 4,
            -3.14, -6.28, -9.42, -12.56,
            -7.89, 10.12, -13.45, -16.78,
            -19.01, -22.34, 25.67, -23.45)

        assertEquals(mxRes,-mx1)

    }

    @Test
    fun `Test for multiply`(){
        val mx0_1=Matrix(2,3)
        val mx0_2=Matrix(7,3)

        assertThrows(IllegalArgumentException::class.java) {
            mx0_1*mx0_2
        }

        val mx1=Matrix(3, 4,
            3.0, 6.28, 9.4, 12.5,
            7.89, -10.12, 13.45, 16.78,
            19.01, 22.34, -25.67, 23.45)

        val mx2=Matrix(4, 3,
            45.0,78.12,13.5,
            1.2, 3.4, 5.6,
            1.23, 4.6, 7.89,
            90.34, 7.8, 10.11)

        val mxRes=Matrix(3,3,
            1283.348, 396.452, 276.209,
            1875.3547, 774.7128, 325.6093,
            2969.1569, 1625.8452, 416.2822,
        )

        assertEquals(mxRes, mx1*mx2)

    }

    @Test
    fun `Test for transpose`(){
        val mx=Matrix(4, 3,
            45.0,78.12,13.5,
            1.2, 3.4, 5.6,
            1.23, 4.6, 7.89,
            90.34, 7.8, 10.11)

        val mxT=Matrix(3, 4,
            45.0,1.2, 1.23,90.34,
            78.12,  3.4,4.6,7.8,
            13.5, 5.6,7.89, 10.11)

        assertEquals(mxT, mx.transpose())

    }
}