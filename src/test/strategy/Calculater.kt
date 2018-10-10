package test.strategy

class Calcluater(private val pricePre: Float) : ICalculate {
    private val mReduceList = ArrayList<FullReduceType>()
    private val mDiscount: Int = 7

    init {
        mReduceList.add(FullReduceType(100F, 20F))
        mReduceList.add(FullReduceType(300F, 60F))
        mReduceList.add(FullReduceType(0F, 0F))
        mReduceList.add(FullReduceType(200F, 40F))
        mReduceList.sort()
    }

    override fun calculate(): Float {
        find()?.let { _fullType ->
            val fullReduced = FullReduceCal(pricePre, _fullType).calculate()
            return DiscountCal(fullReduced, mDiscount).calculate()
        }
        return 0F
    }

    private fun find(): FullReduceType? = mReduceList.find { pricePre > it.priceRule }
}

class DiscountCal(private val pricePre: Float, private val discount: Int) : ICalculate {
    override fun calculate() = pricePre * discount / 10
}

class FullReduceCal(private val pricePre: Float, private val reduceType: FullReduceType) : ICalculate {

    override fun calculate(): Float {
        return if (pricePre > reduceType.priceRule) {
            pricePre - reduceType.priceReduce
        } else {
            pricePre
        }
    }
}

class FullReduceType(val priceRule: Float, val priceReduce: Float) : Comparable<FullReduceType> {
    override fun compareTo(other: FullReduceType): Int = -1 * (priceRule - other.priceRule).toInt()
}

interface ICalculate {

    fun calculate(): Float
}