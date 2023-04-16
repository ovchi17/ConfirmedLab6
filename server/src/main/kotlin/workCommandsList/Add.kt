package workCommandsList

import dataSet.*
import java.time.LocalDate
import moduleWithResults.ResultModule

/**
 * Class Add. Adds a new object to the collection
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
class Add: Command() {

    /**
     * execute method. Add object to collection
     *
     * @return info from command as ResultModule
     */
    override fun execute(getArgs: MutableList<Any>) {

        val str = getArgs as List<Any>

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

        workWithCollection.addElementToCollection(routeToAdd)
        workWithResultModule.setMessages("success")

        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}