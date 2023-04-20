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
     * @param getArgs arguments
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
        val coord1: Long? = (str[1] as Double).toLong()
        val coord2: Long? = (str[2] as Double).toLong()
        val location1: Long? = (str[3] as Double).toLong()
        val location2: Long? = (str[4] as Double).toLong()
        val location3: Int? = (str[5] as Double).toInt()
        val location1_2: Long? = (str[6] as Double).toLong()
        val location2_2: Long? = (str[7] as Double).toLong()
        val location3_2: Int? = (str[8] as Double).toInt()
        distance = (str[9] as Double).toLong()

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