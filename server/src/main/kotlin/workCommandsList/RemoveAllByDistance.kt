package workCommandsList

import dataSet.Route
import dataSet.RouteComporator
import moduleWithResults.ResultModule
import java.util.*

/**
 * Class RemoveAllByDistance. Delete all objects with the given distance.
 *
 * @author jutsoNNN
 * @since 1.0.0
 */
class RemoveAllByDistance: Command() {

    /**
     * execute method. Remove all objects with distance in parametrs
     *
     * @param getArgs arguments
     */
    override fun execute(getArgs: MutableList<Any>){

        val checkDistance = (getArgs[0] as Double).toLong()

        val collection = PriorityQueue<Route>(RouteComporator())
        collection.addAll(workWithCollection.getCollection())

        if (collection.size == 0){
            workWithResultModule.setMessages("emptyCollection")
        }else if(collection.size == 1){
            if (collection.peek().distance == checkDistance){
                workWithCollection.clearCollection()
                workWithResultModule.setMessages("cleared")
            }else{
                workWithResultModule.setMessages("noDistance")
            }
        }else{
            workWithCollection.clearCollection()
            for (i in 0..collection.size - 1){
                if (collection.peek().distance == checkDistance){
                    collection.poll()
                    workWithResultModule.setMessages("cleared")
                }else{
                    workWithCollection.addElementToCollection(collection.peek())
                    collection.poll()
                }
            }
        }
        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}