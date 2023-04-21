import controllers.CollectionMainCommands
import di.serverModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import kotlin.test.assertEquals

internal class ScannerTest: KoinComponent {

    @Test
    fun `Test Scanner Func`(){

        startKoin{
            modules(serverModule)
        }

        val workWithCollection: CollectionMainCommands by inject()
        val pathToFile: String = "C:\\Users\\Akina\\IdeaProjects\\ConfirmedLab6\\server\\src\\main\\resources\\DataOfCollection.txt"
        val scannerJsonFile: ScannerJsonFile = ScannerJsonFile()

        println(scannerJsonFile.scanFile(pathToFile))

        Assertions.assertEquals(2, scannerJsonFile.scanFile(pathToFile).peek().id)
        Assertions.assertEquals("3", scannerJsonFile.scanFile(pathToFile).peek().name)
        Assertions.assertEquals(3, scannerJsonFile.scanFile(pathToFile).peek().distance)
    }

}