import controllers.Tokenizator
import di.koinModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import usersView.AnswerToUser

fun main() {

    startKoin {
        modules(koinModule)
    }

    val writeToConsole: AnswerToUser = AnswerToUser()
    val tokenizator = KoinStarter().returnTokenizator()

    writeToConsole.writeToConsoleLn("Для получения списка команд введите: help")


    while (true){
        writeToConsole.writeToConsole("> ")
        val getCommandFromUser: List<String> = ((readln().lowercase()) + " 1").split(" ")
        val command = getCommandFromUser[0]
        val argument = mutableListOf<String>()
        for (i in 1..getCommandFromUser.size) {
            argument.add(getCommandFromUser[i])
        }
        tokenizator.tokenizator(command, argument)
    }
}

class KoinStarter: KoinComponent{
    val tokenizator: Tokenizator by inject()

    fun returnTokenizator(): Tokenizator{
        return tokenizator
    }
}
