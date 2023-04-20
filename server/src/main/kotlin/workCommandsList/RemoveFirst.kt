package workCommandsList


import dataSet.Route
import dataSet.RouteComporator
import moduleWithResults.ResultModule
import java.util.PriorityQueue

/**
 * Class RemoveFirst. Remove the first object
 *
 * @author jutsoNNN
 * @since 1.0.0
 */
class RemoveFirst: Command(){

    /**
     * execute method. Remove first object in collection
     *
     * @param getArgs arguments
     */
    override fun execute(getArgs: MutableList<Any>){

        val collection = PriorityQueue<Route>(RouteComporator())
        collection.addAll(workWithCollection.getCollection())

        if (collection.size == 0){
            workWithResultModule.setMessages("emptyCollection")
        }else{
            workWithCollection.pollCollection()
            workWithResultModule.setMessages("cleared")
        }
        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}