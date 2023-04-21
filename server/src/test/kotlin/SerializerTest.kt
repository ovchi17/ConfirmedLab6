import controllers.CollectionMainCommands
import di.serverModule
import org.apache.logging.log4j.core.appender.routing.Route
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.util.PriorityQueue

internal class SerializerTest: KoinComponent {

    @Test
    fun `Test serialize func`(){

//        startKoin {
//            modules(serverModule)
//        }

        val workWithCollection: CollectionMainCommands by inject()
        val pathToFile: String = "C:\\Users\\Akina\\IdeaProjects\\ConfirmedLab6\\server\\src\\test\\kotlin\\DataOfCollectionTest.txt"
        val str: String = "[{\"id\":2,\"name\":\"3\",\"creationDate\":\"2023-04-21\",\"from\":{\"y\":3,\"x\":3,\"z\":3},\"to\":{\"y\":3,\"x\":3,\"z\":3},\"distance\":3,\"coordinates\":{\"x\":3,\"y\":3}}]"
        val scannerJsonFile: ScannerJsonFile = ScannerJsonFile()
        val serializer: Serializer = Serializer()

        Assertions.assertEquals(str, serializer.serialize(scannerJsonFile.scanFile(pathToFile).toList()))

    }
    @Test
    fun `Test deserialize func`(){

//        startKoin {
//            modules(serverModule)
//        }

        val serializer: Serializer = Serializer()
        val str: String = "[{\"id\":2,\"name\":\"3\",\"creationDate\":\"2023-04-21\",\"from\":{\"y\":3,\"x\":3,\"z\":3},\"to\":{\"y\":3,\"x\":3,\"z\":3},\"distance\":3,\"coordinates\":{\"x\":3,\"y\":3}}]"

        Assertions.assertEquals("3",serializer.deserialize(str).get(0).name)
        Assertions.assertEquals(2,serializer.deserialize(str).get(0).id)
        Assertions.assertEquals(3, serializer.deserialize(str).get(0).distance)
    }

}