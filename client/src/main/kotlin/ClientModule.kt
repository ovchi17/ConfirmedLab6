import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel
import com.google.gson.Gson
import moduleWithResults.ResultModule
import moduleWithResults.WorkWithResultModule
import java.net.DatagramPacket
import java.net.InetAddress
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class ClientModule() {

    private lateinit var channel: DatagramChannel
    private val nameHost: String = "localhost"
    private val namePort: Int = 2019
    val gson = Gson()

    fun start(){
        channel = DatagramChannel.open()
    }

    fun stop(){
        if (channel.isConnected){
            channel.disconnect()
        } else if (channel.isOpen){
            channel.close()
        }
    }

    fun sender(command: String, args: List<Any>){
        val data = WorkWithResultModule()
        data.setCommand(command)
        data.setArgs(args)
        val json = gson.toJson(data.getResultModule())
        val buffer = ByteBuffer.wrap(json.toByteArray())
        val address = InetSocketAddress(nameHost, namePort)
        println("Отправленно")
        channel.send(buffer, address)
    }

    fun receiver():ResultModule{
        val bufferReceive = ByteBuffer.allocate(65535)
        channel.receive(bufferReceive)
        val bytesReceiver = bufferReceive.array()
        val resultStr = String(bytesReceiver, 0, bufferReceive.position())
        val getInfo = gson.fromJson(resultStr, ResultModule::class.java)
        return getInfo
    }
}