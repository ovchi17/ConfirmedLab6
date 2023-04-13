import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel
import com.google.gson.Gson
import moduleWithResults.ResultModule
import java.net.DatagramPacket
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

data class MyData(val str: String, val list: List<Any>)

class ClientModule(val nameHost: String, val namePort: Int) {

    private lateinit var channel: DatagramChannel

    fun start(){
        channel = DatagramChannel.open()
        channel.bind(InetSocketAddress(nameHost, namePort))
    }

    fun stop(){
        if (channel.isConnected){
            channel.disconnect()
        } else if (channel.isOpen){
            channel.close()
        }
    }

    fun sender(command: String, args: List<Any>){
        val gson = Gson()
        val data = MyData(command, args)
        val json = gson.toJson(data)
        val address = InetSocketAddress(nameHost, namePort)
        val buffer = ByteBuffer.wrap(json.toByteArray())
        channel.send(buffer, address)

    }

    fun receiver():ResultModule{
        TODO("доделать")
    }
}