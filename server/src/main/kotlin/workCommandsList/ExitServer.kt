package workCommandsList

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
        exitProcess(0)

        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}