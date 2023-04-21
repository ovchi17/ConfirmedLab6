import controllers.CollectionMainCommands
import di.serverModule
import io.mockk.InternalPlatformDsl.toArray
import moduleWithResults.WorkWithResultModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import workCommandsList.Info

internal class TestInfo: KoinComponent {

    @Test
    fun `Test Info func`(){

//        startKoin {
//            modules(serverModule)
//        }

        val info: Info = Info()
        val argument = mutableListOf<String>()
        val str: String = "[Тип коллекции: class java.util.PriorityQueue, Размер коллекции: 0, Дата создания коллекции: Fri Apr 21 13:33:49 MSK 2023]"
        val workWithResultModule: WorkWithResultModule by inject()
        info.execute(argument.toMutableList())
        Assertions.assertEquals("SUCCESS", workWithResultModule.getResultModule().status.toString())
    }

}