package commandsHelpers

import org.jetbrains.kotlin.konan.file.File
import org.koin.core.component.KoinComponent

/**
 * Class ExecuteScript. Run commands from file.
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
class ExecuteScript: KoinComponent {

    var getLink = ""
    var fileLink = File("")
    var stopRecursion = 5
    var checkerRecursion = 0
    var addChecker = 0
    var params = ""
    var specialForAdd = ""

    /**
     * execute method. Starts script
     *
     * @return info from command as ResultModule
     */

/*
fun execute(str: List<Any>) {

    getLink = str[0] as String

    if (File(getLink).exists){
        fileLink = File(getLink)
        if (stopRecursion >= checkerRecursion) {
            val commandFromFile = fileLink.readStrings()
            for (line in commandFromFile) {
                val args = line.split(" ")
                if (args[0] == "execute_script") {
                    checkerRecursion++
                    val sendList = mutableListOf<Any>()
                    sendList.add(args[1])
                    parametrs.setParametrs(sendList)
                    execute()
                }else if (args[0] == "add" || args[0] == "add_if_max"){
                    addChecker = 10
                    specialForAdd = args[0]
                }else{
                    if (addChecker > 0){
                        params = params + line + " "
                        addChecker -= 1
                        if (addChecker == 0){
                            val addList = mutableListOf<String>()
                            addList.add(specialForAdd)
                            addList.add(params)
                            params = ""
                            tokenizator.tokenizatorHelper(specialForAdd, addList)
                        }
                    }else{
                        tokenizator.tokenizatorHelper(args[0], args)
                    }
                }
            }
        }else{
            workWithResultModule.setError("recursion")
            workWithResultModule.setStatus(Status.ERROR)
        }
    }else{
        workWithResultModule.setMessages("noFile")
    }

    checkerRecursion -= 1
    if (checkerRecursion == 0) {
        workWithResultModule.setMessages("success")
    }

}
}
**/