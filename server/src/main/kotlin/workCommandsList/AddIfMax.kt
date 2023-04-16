package workCommandsList

import dataSet.Coordinates
import dataSet.Location
import dataSet.Route
import dataSet.RouteComporator
import moduleWithResults.ResultModule
import java.time.LocalDate
import java.util.*

/**
 * Class AddIfMax. Adds a new object to the collection if Distance parameter is greater than the others.
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
class AddIfMax: Command() {

    /**
     * execute method. Add object if distance is max
     *
     * @return info from command as ResultModule
     */
    override fun execute(getArgs: MutableList<Any>) {

        val str = getArgs[0] as List<Any>
        val collection = PriorityQueue<Route>(RouteComporator())
        collection.addAll(workWithCollection.getCollection())

        workWithCollection.idPlusOne()
        var id: Long = workWithCollection.getId()
        val name: String?
        val coordinates: Coordinates
        val creationDate: LocalDate = LocalDate.now()
        val from: Location
        val to: Location
        val distance: Long
        val stopper: Long = 1

        name = str[0] as String
        val coord1: Long? = str[1] as Long?
        val coord2: Long? = str[2] as Long?
        val location1: Long? = str[3] as Long?
        val location2: Long? = str[4] as Long?
        val location3: Int? = str[5] as Int?
        val location1_2: Long? = str[6] as Long?
        val location2_2: Long? = str[7] as Long?
        val location3_2: Int? = str[8] as Int?
        distance = str[9] as Long

        coordinates = Coordinates(coord1, coord2)
        to = Location(location1, location2, location3)
        from = Location(location1_2, location2_2, location3_2)

        val routeToAdd: Route = Route(
            id,
            name = name,
            coordinates = coordinates,
            creationDate = creationDate,
            from = from,
            to = to,
            distance = distance
        )

        if (collection.size == 0){
            workWithCollection.addElementToCollection(routeToAdd)
            workWithResultModule.setMessages("success")
        }else if(collection.size == 1){
            if (distance > collection.peek().distance){
                workWithCollection.addElementToCollection(routeToAdd)
                workWithResultModule.setMessages("success")
            }else{
                workWithResultModule.setMessages("noSuccess")
            }
        }else{
            var intSr: Int = 0
            for (i in 0..collection.size - 1){
                if (distance > collection.peek().distance) {
                    intSr += 1
                }
            }
            if (intSr == collection.size){
                workWithCollection.addElementToCollection(routeToAdd)
                workWithResultModule.setMessages("success")
            }else{
                workWithResultModule.setMessages("noSuccess")
            }
        }

        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}