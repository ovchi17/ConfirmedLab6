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
    //private var pathToFile: String = System.getenv("DataOfCollection")
    //private var fileReader: FileReader = FileReader(pathToFile)

    /**
     * execute method. Save collection to file
     *
     * @return info from command as ResultModule
     */
    override fun execute(getArgs: MutableList<Any>){
        if (!workWithFile.checkFile()){
            val list = serializer.deserialize(workWithFile.readFile(File(serverModule.serverReceiver().toString())))
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