package workCommandsList

//import commandsHelpers.ResultModule
import moduleWithResults.ResultModule
import kotlin.system.exitProcess

/**
 * Class Exit. Stop the program.
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
class Exit: Command(){

    /**
     * execute method. Stops command
     *
     * @return info from command as ResultModule
     */
    override fun execute(getArgs: MutableList<Any>): ResultModule {
        //workWithResultModule.setMessages("end")
        exitProcess(0)
    }
}