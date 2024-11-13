
class Matrix(row: Int, column: Int) {
    var mtrx: Array<Array<Double>>
        private set

    val rowNum: Int=row

    val colNum: Int=column

    init {
        require(row>0 && column>0){
            "Nem megfelelo parameterekkel probal matrixot inicilizalni! (Nem lehet minusz nagysagu egyik ertek se)"
        }

       mtrx = Array(row){
           Array<Double>(column){0.0}
       }
    }

    constructor(mx: Array<Array<Double>>):this(mx.size, mx[0].size){
        require(mx.all { it.size==mx[0].size }){
            "A 2d-s tomb nem alakithato matrixa, mivel nem egyezo a benne levo tombok merete"
        }

        this.mtrx=mx
    }

    constructor(row: Int, column: Int, vararg nums: Double): this(row, column){
        require(nums.size <= row*column){
            "Tul sok erteket adott meg egy ekkorra tomb letrehozasahoz"
        }

        for (r in 0..<rowNum){
            for (c in 0..<colNum){
                if(r*column+c == nums.size)
                    return

                this[r, c]=nums[r*column+c]
            }
        }
    }

    private fun indexRequireCheck(row: Int,column: Int){
        require(row>=0 && column>=0){
            "Minusz index hasznalata nem megengedett es ertelmetlen!"
        }

        require( row<=rowNum && column<=colNum){
            "Nem megfelelo indexet adott meg. (Nincs ennyi sora vagy oszlopa a matrixnak.)"
        }
    }

    operator fun get(row: Int, column: Int): Double{
        indexRequireCheck(row,column)

        return mtrx[row][column]
    }

    operator fun set(row: Int,column: Int, value: Double){
        indexRequireCheck(row,column)

        mtrx[row][column]=value
    }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (!mtrx.contentDeepEquals(other.mtrx)) return false
        if (rowNum != other.rowNum) return false
        if (colNum != other.colNum) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mtrx.contentDeepHashCode()
        result = 31 * result + rowNum
        result = 31 * result + colNum
        return result
    }

    override fun toString(): String {
        val ret: String=buildString {
            this@Matrix.mtrx.forEach {
                it.forEach {
                    append(String.format("%5.2f", it))
                    append(" ")
                }
                append(System.getProperty("line.separator"));
            }
        }

        return ret
    }
}


operator fun Matrix.plus(otherMx: Matrix): Matrix{
    require(rowNum==otherMx.rowNum && colNum==otherMx.colNum){
        "Az adott ket matrix nem osszeadas. (Kulonbozo meretuek)"
    }

    val ret: Matrix=Matrix(rowNum,colNum)

    for (r in 0..<rowNum){
        for (c in 0..<colNum){
            ret[r, c]= this[r, c] + otherMx[r, c]
        }
    }

    return ret
}

operator fun  Matrix.minus(otherMx: Matrix): Matrix{
    require(rowNum==otherMx.rowNum && colNum==otherMx.colNum){
        "Az adott ket matrix nem kivonhato. (Kulonbozo meretuek)"
    }

    return plus(-otherMx)
}

operator fun  Matrix.unaryMinus(): Matrix{
    return Matrix(mtrx.map { it.map { -it }.toTypedArray() }.toTypedArray())
}

operator fun  Matrix.times(otherMx: Matrix): Matrix{
    require(colNum==otherMx.rowNum){"Nem szorozhato ossze a ket matrix (A szorzas bal matrixanak oszlop es a jobb oldali matrix sor szama nem egyezik meg.)"}

    val ret: Matrix=Matrix(rowNum, otherMx.colNum)

    for(r in 0..<rowNum){
        for(c in 0..<otherMx.colNum){
            for(i in 0..<otherMx.rowNum){
                ret[r,c] += this[r,i]*otherMx[i,c]
            }
        }
    }
    return ret
}

operator fun  Matrix.times(num: Double): Matrix{
    return Matrix(mtrx.map { it.map { it*num }.toTypedArray() }.toTypedArray())
}

operator fun  Double.times(mx: Matrix): Matrix{
    val ret: Matrix=Matrix(mx.rowNum, mx.colNum)

    for (r in 0..<mx.rowNum){
        for (c in 0..<mx.colNum){
            ret[r,c]=mx[r,c]*this
        }
    }

    return ret
}


fun Matrix.transpose(): Matrix{

    val ret: Matrix=Matrix(colNum, rowNum)

    for (r in 0..<rowNum){
        for (c in 0..<colNum){
            ret[c,r] = this[r,c]
        }
    }

    return ret
}


