package workCommandsList

import moduleWithResults.ResultModule

class Switch: Command() {
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