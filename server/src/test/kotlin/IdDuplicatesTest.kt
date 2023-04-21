import controllers.CollectionMainCommands
import controllers.Parametrs
import di.serverModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import workCommandsList.Add
import workCommandsList.RemoveById

internal class IdDublicatesTest: KoinComponent {

    @Test
    fun `Check, if there can be double id`() {

////        startKoin {
////            modules(serverModule)
////        }
//
//        val parametrs: Parametrs by inject()
//        val workWithCollection: CollectionMainCommands by inject()
//
//        val add: Add = Add()
//        var sendList = mutableListOf<Any>()
////        sendList.add(1.toLong())
//        sendList.add("Egor 2 2 2 2 2 2 2 2 2")
//        parametrs.setParametrs(sendList)
//
//        println("////////////////////")
//        println(parametrs.getParametrs())
//        println("////////////////////")
//
//        add.execute(parametrs.getParametrs())
//        var sendList2 = mutableListOf<Any>()
//        sendList2.add(1.toLong())
//        sendList2.add("Oleg 2 2 2 2 2 2 2 2 2")
//        parametrs.setParametrs(sendList2)
//        val removeById: RemoveById = RemoveById()
//        var sendList4 = mutableListOf<Any>()
//        sendList4.add(2)
//        removeById.execute(parametrs.getParametrs())
//        var sendList3 = mutableListOf<Any>()
//        sendList3.add(1.toLong())
//        sendList3.add("Oleg 2 2 2 2 2 2 2 2 2")
//        add.execute(parametrs.getParametrs())
//
//        Assertions.assertEquals(workWithCollection.pollCollection()!!.id, workWithCollection.peekCollection()!!.id + 1)
    }

}