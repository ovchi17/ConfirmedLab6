import moduleWithResults.ResultModule
import java.net.DatagramPacket
import java.net.DatagramSocket
import com.google.gson.Gson
import controllers.CollectionMainCommands
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import workCommandsList.*
import java.net.InetAddress

class ServerModule {
    var socket = DatagramSocket(2019)
    val commandStarter = CommandStarter()
    val gson = Gson()
    val buffer = ByteArray(65535)
    val packet = DatagramPacket(buffer, buffer.size)

    fun serverReceiver(){
        socket.receive(packet)
        val json = String(packet.data, 0, packet.length)
        val getInfo = gson.fromJson(json, ResultModule::class.java)
        if (getInfo.commandName != "noCommand"){
            println(getInfo)
            commandStarter.mp(getInfo.commandName)?.execute(getInfo.args)
        }
    }

    fun serverSender(result: ResultModule){
        val gson = Gson()
        val json = gson.toJson(result)
        val changedToBytes = json.toByteArray()
        val packetToSend = DatagramPacket(changedToBytes, changedToBytes.size, packet.address, packet.port)
        print(result.msgToPrint)
        socket.send(packetToSend)
    }

}

class CommandStarter(): KoinComponent{

    val workWithCollection: CollectionMainCommands by inject()
    fun mp(command: String): Command? {
        val info: Info = Info()
        val show: Show = Show()
        val add: Add = Add()
        val removeById: RemoveById = RemoveById()
        val clear: Clear = Clear()
        //val save: Save = Save()
        val exitServer: ExitServer = ExitServer()
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
            //"save" to save,
            "exit" to exitServer,
            "remove_first" to removeFirst,
            "add_if_max" to addIfMax,
            "history" to history,
            "remove_all_by_distance" to removeAllByDistance,
            "average_of_distance" to averageOfDistance,
            "filter_less_than_distance" to filterLessThanDistance,
            "switch" to switch)

        if (command in COMMANDS) {
            workWithCollection.historyUpdate(command)
            return COMMANDS[command]
        }else{
            return null
        }
    }
}