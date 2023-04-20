package workCommandsList


import moduleWithResults.ResultModule

/**
 * Class Clear. Remove all objects from the collection
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
class Clear: Command() {

    /**
     * execute method. Clear collection
     *
     * @param getArgs arguments
     */
    override fun execute(getArgs: MutableList<Any>) {

        workWithCollection.clearCollection()

        workWithResultModule.setMessages("cleared")

        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}