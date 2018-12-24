package test.design.strategy

import test.util.LogUtil

const val STUDENT_DISCOUNT = 0.6F
const val CHILD_DISCOUNT = 0.2F
const val VIP_DISCOUNT = 0.5F

fun main(args: Array<String>) {
    val system = TicketSystem()
    val student = StudentDiscount()
    val child = ChildDiscount()
    val vip = VipDiscount()

    val price = 60F
    val priceStu = system.getPrice(price, student)
    val priceChild = system.getPrice(price, child)
    val priceVip = system.getPrice(price, vip)

    log(priceStu)
    log(priceChild)
    log(priceVip)
}

class TicketSystem {

    fun getPrice(price: Float, discount: IDiscount): Float {
        return discount.calculate(price)
    }
}

interface IDiscount {

    fun calculate(price: Float): Float
}

class StudentDiscount : IDiscount {

    override fun calculate(price: Float): Float {
        return price * STUDENT_DISCOUNT
    }
}

class ChildDiscount : IDiscount {

    override fun calculate(price: Float): Float {
        return price * CHILD_DISCOUNT
    }
}

class VipDiscount : IDiscount {

    override fun calculate(price: Float): Float {
        return price * VIP_DISCOUNT
    }
}

fun log(price: Float) {
    LogUtil.log(price.toString())
}
