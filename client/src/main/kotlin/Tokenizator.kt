

import commandsHelpers.AddSet
import commandsHelpers.ExecuteScript
import commandsHelpers.Help
import moduleWithResults.ResultModule
import moduleWithResults.Status
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import usersView.AnswerToUser
import usersView.ConsoleWriter
import usersView.TypeMessages


/**
 * Tokenizator class.
 *
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */


class Tokenizator: KoinComponent {

    fun commandsList(name: String): String{
        val listOfNo = listOf("help", "info", "show", "clear", "save", "exit", "remove_first", "history", "average_of_distance", "switch")
        val listOfLong = listOf("remove_by_id", "remove_all_by_distance", "filter_less_than_distance")
        val listOfString = listOf("execute_script")
        val listOfAdd = listOf("add_if_max", "add")

        if (name in listOfNo){
            return "listOfNo"
        }else if (name in listOfLong){
            return  "listOfLong"
        }else if (name in listOfString){
            return "listOfString"
        }else if (name in listOfAdd){
            return "listOfAdd"
        }else{
            return "noCommand"
        }
    }

    var writeToConsole: ConsoleWriter = ConsoleWriter()
    val typeMessages: TypeMessages = TypeMessages()
    val answerToUser: AnswerToUser = AnswerToUser()
    val executeScript: ExecuteScript by inject()
    val addSet: AddSet by inject()
    val help = Help()

    /**
     * tokenizator method. Tokenizate massive to commands with right arguments.
     *
     * @param command: Command. Contains the command to be executed.
     * @param mass: Array of String arguments.
     * @param workWithCollection: WorkWithCollection contains our main collection
     */
    fun tokenizator(command: String, mass: List<String>){
        val sendList = mutableListOf<Any>()
        val clientModule: ClientModule by inject()
        if (commandsList(command) == "listOfLong"){
            var newToken:Long = 1
            try {
                newToken = mass[0].toLong()
            }catch (e: NumberFormatException){
                answerToUser.writeToConsoleLn("Ошибка в парматрах, установлено значение по умолчанию")
            }
            sendList.add(newToken)
        }else if(commandsList(command) == "listOfString"){
            sendList.add(mass[0])
            val getResultModule: ResultModule = executeScript.execute(sendList)
            if (getResultModule.status == Status.SUCCESS) {
                for (msg in getResultModule.msgToPrint) {
                    if (typeMessages.msgToPrint(msg) != null) {
                        writeToConsole.printToConsoleLn(msg)
                    } else {
                        answerToUser.writeToConsoleLn(msg)
                    }
                }
                answerToUser.writeToConsoleLn(" ")
            }else{
                getResultModule.errorDescription?.let { writeToConsole.printToConsoleLn(it) }
            }
        }else if(commandsList(command) == "listOfAdd"){
            val name = addSet.name("noInfo")
            val coord1: Long = addSet.coord1("noInfo")
            val coord2: Long = addSet.coord2("noInfo")
            val location1: Long = addSet.location1("noInfo")
            val location2: Long = addSet.location2("noInfo")
            val location3: Int = addSet.location3("noInfo")
            val location1_2: Long = addSet.location12("noInfo")
            val location2_2: Long = addSet.location22("noInfo")
            val location3_2: Int = addSet.location32("noInfo")
            val distance = addSet.distance("noInfo")
            val list = listOf<Any>(name, coord1, coord2, location1, location2, location3, location1_2, location2_2, location3_2, distance)
            sendList.addAll(list)
            //ПРОПИСАТЬ ОТПРАВКУ НА СЕРВЕР
            clientModule.sender(command, sendList)
            clientModule.receiver()
        }else if(commandsList(command) == "listOfNo"){
            if (command == "help"){
                help.execute()
            }else{
                //ПРОПИСАТЬ ОТПРАВКУ НА СЕРВЕР
                clientModule.sender(command, sendList)
                clientModule.receiver()
            }
        }else if(commandsList(command) == "noCommand"){
            writeToConsole.printToConsoleLn("infoAbout")
        }
    }

    fun tokenizatorAdder(command: String, mass: List<String>){
        val sendList = mutableListOf<Any>()
        val args = mass[1].split(" ")

        val name = addSet.name(args[0])
        val coord1: Long = addSet.coord1(args[1])
        val coord2: Long = addSet.coord2(args[2])
        val location1: Long = addSet.location1(args[3])
        val location2: Long = addSet.location2(args[4])
        val location3: Int = addSet.location3(args[5])
        val location1_2: Long = addSet.location12(args[6])
        val location2_2: Long = addSet.location22(args[7])
        val location3_2: Int = addSet.location32(args[8])
        val distance = addSet.distance(args[9])
        val list = listOf<Any>(name, coord1, coord2, location1, location2, location3, location1_2, location2_2, location3_2, distance)
        sendList.addAll(list)

    }

}