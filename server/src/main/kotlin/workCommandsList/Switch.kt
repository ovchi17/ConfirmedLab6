package workCommandsList

import moduleWithResults.ResultModule

/**
 * Class Switch.
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
class Switch: Command() {

    /**
     * execute method. Switch collection (PQ -> LL or LL -> PQ)
     *
     * @param getArgs arguments
     */
    override fun execute(getArgs: MutableList<Any>){

        var keyCollection = workWithCollection.checkCollection()

        if (keyCollection == "PQ"){
            workWithCollection.changeCollection()
            workWithResultModule.setMessages("changeToCollLL")
        }else{
            workWithCollection.changeCollection()
            workWithResultModule.setMessages("changeToCollPQ")
        }
        serverModule.serverSender(workWithResultModule.getResultModule())
    }

}