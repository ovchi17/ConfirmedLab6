import com.google.gson.Gson
import di.serverModule
import moduleWithResults.ResultModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import workCommandsList.*
import java.net.DatagramPacket
import java.net.DatagramSocket

fun main() {

    startKoin {
        modules(serverModule)
    }

    val serverModule = ServerModuleGet().returnServerModule()

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