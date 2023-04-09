package workCommandsList

import controllers.*
import moduleWithResults.ResultModule
import moduleWithResults.WorkWithResultModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Abstract Class Command.
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
abstract class Command: KoinComponent {

    val workWithCollection: CollectionMainCommands by inject()
    //val consoleWriter: ConsoleWriter by inject()
    //val tokenizator: Tokenizator by inject()
    //val addSet: AddSet by inject()
    val workWithFile: WorkWithFile by inject()
    val serializer: Serializer by inject()
    val workWithResultModule: WorkWithResultModule by inject()
    val parametrs: Parametrs by inject()

    /**
     * execute method. Using in all workCommandsList
     *
     * @return info from command as ResultModule
     */
    abstract fun execute(): ResultModule

}