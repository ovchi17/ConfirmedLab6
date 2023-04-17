import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel
import com.google.gson.Gson
import moduleWithResults.ResultModule
import moduleWithResults.Status
import moduleWithResults.WorkWithResultModule
import usersView.AnswerToUser
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.SocketTimeoutException
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.SocketChannel
import java.util.ResourceBundle

class ClientModule() {

    private lateinit var channel: DatagramChannel
    val answerToUser = AnswerToUser()
    private val nameHost: String = "localhost"
    private val namePort: Int = 2024
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
        val selector = Selector.open()
        channel.configureBlocking(false)
        channel.register(selector, SelectionKey.OP_READ)
        selector.select(3000)
        val selectedKeys = selector.selectedKeys()
        if (selectedKeys.isEmpty()) {
            return ResultModule(mutableListOf(), Status.ERROR, "noAnswer", "noCommand", mutableListOf())
        }else{
            val bufferReceive = ByteBuffer.allocate(65535)
            channel.receive(bufferReceive)
            val bytesReceiver = bufferReceive.array()
            val resultStr = String(bytesReceiver, 0, bufferReceive.position())
            val getInfo = gson.fromJson(resultStr, ResultModule::class.java)
            return getInfo
        }
    }
}