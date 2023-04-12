

import commandsHelpers.AddSet
import commandsHelpers.ExecuteScript
import commandsHelpers.Help
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import usersView.AnswerToUser
import usersView.ConsoleWriter



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
        val listOfLong = listOf("update", "remove_by_id", "remove_all_by_distance", "filter_less_than_distance")
        val listOfString = listOf("execute_script")
        val listOfAdd = listOf("add_if_max", "add")

        if (name in listOfNo){
            return "listOfNo"
        }else if (name in listOfLong){
            return  "listOfLong"
        }else if (name in listOfString){
            return "listOfString"
        }else{
            return "noCommand"
        }
    }

    var writeToConsole: ConsoleWriter = ConsoleWriter()
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
            executeScript.execute(sendList)
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
        }else if(commandsList(command) == "listOfNo"){
            if (command == "help"){
                help.execute()
            }else{
                //ПРОПИСАТЬ ОТПРАВКУ НА СЕРВЕР
            }
        }else if(commandsList(command) == "noCommand"){
            writeToConsole.printToConsoleLn("infoAbout")
        }
    }

}