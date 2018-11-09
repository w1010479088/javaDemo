package test.design.command

import test.utils.LogUtil

//命令:1,2,3
//执行:1,2,3
fun main(args: Array<String>) {
    Invoker().execute(1)
}

class Invoker {
    private val command = ArrayList<ICommand>()

    init {
        list()
    }

    private fun list() {
        command.add(Command1(Receiver3()))
        command.add(Command2(Receiver1()))
        command.add(Command3(Receiver2()))
    }

    fun execute(index: Int) {
        command[index].command()
    }
}

interface ICommand {

    fun command()
}

interface IReceiver {

    fun execute()
}

open class BaseCommand(private val receiver: IReceiver) : ICommand {
    override fun command() {
        receiver.execute()
    }
}

class Command1(receiver: IReceiver) : BaseCommand(receiver) {
    override fun command() {
        log("命令1发出...")
        super.command()
    }
}

class Command2(receiver: IReceiver) : BaseCommand(receiver) {

    override fun command() {
        log("命令2发出...")
        super.command()
    }
}

class Command3(receiver: IReceiver) : BaseCommand(receiver) {

    override fun command() {
        log("命令3发出...")
        super.command()
    }
}

class Receiver1 : IReceiver {

    override fun execute() {
        log("Receiver1执行")
    }
}

class Receiver2 : IReceiver {

    override fun execute() {
        log("Receiver2执行")
    }
}

class Receiver3 : IReceiver {

    override fun execute() {
        log("Receiver3执行")
    }
}

private fun log(content: String) {
    LogUtil.log(content)
}