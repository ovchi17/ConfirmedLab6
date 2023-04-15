import moduleWithResults.ResultModule
import java.net.DatagramPacket
import java.net.DatagramSocket
import com.google.gson.Gson

class ServerModule {
    var socket = DatagramSocket(1717)

    fun serverReceiver(): ResultModule{
        val buffer = ByteArray(65535)
        val packet = DatagramPacket(buffer, buffer.size)
        socket.receive(packet)
        val gson = Gson()
        val json = String(packet.data, 0, packet.length)
        print("here")
        return gson.fromJson(json, ResultModule::class.java)
    }
}