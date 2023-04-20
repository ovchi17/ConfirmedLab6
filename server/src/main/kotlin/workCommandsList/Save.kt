package workCommandsList

import dataSet.Route
import dataSet.RouteComporator
import moduleWithResults.ResultModule
import java.io.FileReader
import java.util.PriorityQueue

/**
 * Class Save. Save to file in JSON format.
 *
 * @author jutsoNNN
 * @since 1.0.0
 */
class Save: Command() {
     //private var pathToFile: String = System.getenv("DataOfCollection.txt")
     //private var fileReader: FileReader = FileReader(pathToFile)

    /**
     * execute method. Save collection to file
     *
     * @param getArgs arguments
     */
    override fun execute(getArgs: MutableList<Any>){
         val pathToFile = System.getProperty("DataOfCollection.server")
         val collection = PriorityQueue<Route>(RouteComporator())
         collection.addAll(workWithCollection.getCollection())
         val list = workWithCollection.collectionToList()
         val jsonString = serializer.serialize(list)
         workWithFile.writeToFile(collection, pathToFile, jsonString)
         workWithResultModule.setMessages("saved")

        serverModule.serverSender(workWithResultModule.getResultModule())
     }
}