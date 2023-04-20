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
class ExitServer: Command(){

    /**
     * execute method. Stops server
     *
     * @param getArgs arguments
     */
    override fun execute(getArgs: MutableList<Any>){
        //workWithResultModule.setMessages("end")
        exitProcess(0)

        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}