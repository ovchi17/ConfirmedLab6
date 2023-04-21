package workCommandsList

import dataSet.Route
import java.io.File
import java.util.*

/**
 * Class Load. Load file from server.
 *
 * @author jutsoNNN
 * @since 1.0.0
 */
class Load: Command() {

    /**
     * execute method. Save collection to file
     *
     * @param getArgs arguments
     */
    override fun execute(getArgs: MutableList<Any>){
//        val pathToFile: String = System.getProperty("DataOfCollection.server")
        val pathToFile: String = System.getProperty("DataOfCollection.test")
        if (!workWithFile.checkFile(pathToFile)){
            val list = serializer.deserialize(workWithFile.readFile(File(pathToFile)))
            val collection: PriorityQueue<Route> = workWithCollection.listToCollection(list)
            var maxId: Int = 0
            for(i in list.indices){
                if (collection.element().id > maxId){
                    maxId = collection.element().id.toInt()
                }
                workWithCollection.addElementToCollection(collection.toList().get(i))
            }
            if (workWithCollection.getId() < maxId.toLong()){
                while(workWithCollection.getId() < maxId.toLong()){
                    workWithCollection.idPlusOne()
                }
            }
        }
        workWithResultModule.setMessages("loaded")

        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}