import com.google.gson.Gson
import di.serverModule
import moduleWithResults.ResultModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import workCommandsList.*
import java.net.DatagramPacket
import java.net.DatagramSocket
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun main() {

    startKoin {
        modules(serverModule)
    }

    val serverModule = ServerModuleGet().returnServerModule()
    System.setProperty("log4j.configurationFile", "classpath:log4j2.xml")
    val logger: Logger = LogManager.getLogger(ServerModuleGet::class.java)
    logger.info("Запуск сервера")

    while (true){
        serverModule.serverReceiver()
    }

}

class ServerModuleGet : KoinComponent{
    val serverModule: ServerModule by inject()
    fun returnServerModule():ServerModule{
        return serverModule
    }
}