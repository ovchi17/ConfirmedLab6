package workCommandsList

import moduleWithResults.Status

class UpdateCommand: Command() {

    var listOfNo: List<String> = listOf("help", "info", "show", "clear", "save", "exit", "exit_server", "remove_first", "history", "average_of_distance", "switch", "update_command", "bebra")
    var listOfLong: List<String> = listOf("remove_by_id", "remove_all_by_distance", "filter_less_than_distance")
    var listOfObject: List<String> = listOf("add_if_max", "add")
    var listOfObjectPlus: List<String> = listOf("update_id")

    override fun execute(getArgs: MutableList<Any>) {

        workWithCollection.clearCollection()

        workWithResultModule.setStatus(Status.UPDATE)
        workWithResultModule.setListNo(listOfNo)
        workWithResultModule.setListLong(listOfLong)
        workWithResultModule.setListObject(listOfObject)
        workWithResultModule.setListObjectPlus(listOfObjectPlus)

        serverModule.serverSender(workWithResultModule.getResultModule())
    }
}