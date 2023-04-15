import com.google.gson.Gson
import di.serverModule
import moduleWithResults.ResultModule
import org.koin.core.context.startKoin
import workCommandsList.*
import java.net.DatagramPacket
import java.net.DatagramSocket

fun main() {

    startKoin {
        modules(serverModule)
    }

    var socket = DatagramSocket(2003)

    while (true){
        val buffer = ByteArray(65535)
        val packet = DatagramPacket(buffer, buffer.size)
        socket.receive(packet)
        val gson = Gson()
        val json = String(packet.data, 0, packet.length)
        val gotIt: ResultModule = gson.fromJson(json, ResultModule::class.java)
        println(gotIt.commandName)
    }

}

class CommandStarter(){
    fun mp(command: String): Command? {
        val info: Info = Info()
        val show: Show = Show()
        val add: Add = Add()
        val removeById: RemoveById = RemoveById()
        val clear: Clear = Clear()
        val save: Save = Save()
        val exit: Exit = Exit()
        val removeFirst: RemoveFirst = RemoveFirst()
        val addIfMax: AddIfMax = AddIfMax()
        val history: History = History()
        val removeAllByDistance: RemoveAllByDistance = RemoveAllByDistance()
        val averageOfDistance: AverageOfDistance = AverageOfDistance()
        val filterLessThanDistance: FilterLessThanDistance = FilterLessThanDistance()
        val switch: Switch = Switch()

        val COMMANDS = mapOf(
            "info" to info,
            "show" to show,
            "add" to add,
            "remove_by_id" to removeById,
            "clear" to clear,
            "save" to save,
            "exit" to exit,
            "remove_first" to removeFirst,
            "add_if_max" to addIfMax,
            "history" to history,
            "remove_all_by_distance" to removeAllByDistance,
            "average_of_distance" to averageOfDistance,
            "filter_less_than_distance" to filterLessThanDistance,
            "switch" to switch)

        if (command in COMMANDS) {
            return COMMANDS[command]
        }else{
            return null
        }
    }
}