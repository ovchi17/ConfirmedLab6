
import di.koinModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import usersView.AnswerToUser

fun main() {

    startKoin {
        modules(koinModule)
    }
    //Stas
    val writeToConsole: AnswerToUser = AnswerToUser()
    val tokenizator = KoinStarter().returnTokenizator()
    val clientModule = KoinStarter().returnClientModule()

    writeToConsole.writeToConsoleLn("Для получения списка команд введите: help")
    clientModule.start()

    while (true){
        writeToConsole.writeToConsole("> ")
        val getCommandFromUser: List<String> = ((readln().lowercase()) + " 1").split(" ")
        val command = getCommandFromUser[0]
        val argument = mutableListOf<String>()
        for (i in 1..getCommandFromUser.size - 1) {
            argument.add(getCommandFromUser[i])
        }
        tokenizator.tokenizator(command, argument)
    }
}

class KoinStarter: KoinComponent{
    val tokenizator: Tokenizator by inject()
    val clientModule: ClientModule by inject()
    fun returnTokenizator(): Tokenizator{
        return tokenizator
    }

    fun returnClientModule(): ClientModule{
        return clientModule
    }
}
