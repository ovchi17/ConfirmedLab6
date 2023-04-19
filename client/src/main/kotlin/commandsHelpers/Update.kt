package commandsHelpers

import Tokenizator
import moduleWithResults.ResultModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import usersView.AnswerToUser

/**
 * Class Update. Works with Result module with Status.UPDATE.
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
class Update: KoinComponent {

    val tokenizator: Tokenizator by inject()
    val answerToUser = AnswerToUser()

    /**
     * execute method. Check new commands
     *
     * @param module ResultModule
     */
    fun execute(module: ResultModule){
        val lstNo = module.listOfNo
        val lstLong = module.listOfLong
        val lstObject = module.listOfObject
        val lstObjectPlus = module.listOfObjectPlus
        var flag = false

        for (i in lstNo){
            if (i !in tokenizator.listOfNo){
                flag = true
                answerToUser.writeToConsoleLn("Получена новая команда $i")
                tokenizator.listOfNo.add(i)
            }
        }

        for (i in lstLong){
            if (i !in tokenizator.listOfLong){
                flag = true
                answerToUser.writeToConsoleLn("Получена новая команда $i")
                tokenizator.listOfLong.add(i)
            }
        }

        for (i in lstObject){
            flag = true
            if (i !in tokenizator.listOfObject){
                answerToUser.writeToConsoleLn("Получена новая команда $i")
                tokenizator.listOfObject.add(i)
            }
        }

        for (i in lstObjectPlus){
            flag = true
            if (i !in tokenizator.listOfObjectPlus){
                answerToUser.writeToConsoleLn("Получена новая команда $i")
                tokenizator.listOfObjectPlus.add(i)
            }
        }

        if (flag == false){
            answerToUser.writeToConsoleLn("Установлена актуальная версия")
        }

        tokenizator.uploadLists()

    }
}