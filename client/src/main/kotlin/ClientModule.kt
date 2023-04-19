import java.net.InetSocketAddress
import java.nio.channels.DatagramChannel
import com.google.gson.Gson
import moduleWithResults.ResultModule
import moduleWithResults.Status
import moduleWithResults.WorkWithResultModule
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import usersView.AnswerToUser
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.SocketTimeoutException
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.SocketChannel
import java.util.ResourceBundle

/**
 * Class ClientModule. Works with client.
 *
 * @author OvchinnikovI17
 * @since 1.0.0
 */
class ClientModule() {

    private lateinit var channel: DatagramChannel
    val answerToUser = AnswerToUser()
    private val nameHost: String = "localhost"
    private val namePort: Int = 2029
    val gson = Gson()
    val logger: Logger = LogManager.getLogger(ClientModule::class.java)

    /**
     * start method. Starts client module
     *
     */
    fun start(){
        logger.info("Запуск DatagramChannel")
        channel = DatagramChannel.open()
    }

    /**
     * stop method. Stops client module
     *
     */
    fun stop(){
        logger.info("Остановка DatagramChannel")
        if (channel.isConnected){
            channel.disconnect()
        } else if (channel.isOpen){
            channel.close()
        }
    }

    /**
     * sender method. Send ResultModule to server
     *
     * @param command String with commmand
     * @param args List with arguments
     */
    fun sender(command: String, args: List<Any>){
        val data = WorkWithResultModule()
        data.setCommand(command)
        data.setArgs(args)
        val json = gson.toJson(data.getResultModule())
        val buffer = ByteBuffer.wrap(json.toByteArray())
        val address = InetSocketAddress(nameHost, namePort)
        logger.info("Запрос отправлен")
        channel.send(buffer, address)
    }

    /**
    * reciver method. Get ResultModule from server
    *
    * @return ResultModule from server
    */
    fun receiver():ResultModule{
        val selector = Selector.open()
        channel.configureBlocking(false)
        channel.register(selector, SelectionKey.OP_READ)
        selector.select(3000)
        val selectedKeys = selector.selectedKeys()
        if (selectedKeys.isEmpty()) {
            logger.info("Ответ от сервера не получен")
            return ResultModule(mutableListOf(), Status.ERROR, "noAnswer", "noCommand", mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        }else{
            val bufferReceive = ByteBuffer.allocate(65535)
            logger.info("Получен ответ от сервера")
            channel.receive(bufferReceive)
            val bytesReceiver = bufferReceive.array()
            val resultStr = String(bytesReceiver, 0, bufferReceive.position())
            val getInfo = gson.fromJson(resultStr, ResultModule::class.java)
            return getInfo
        }
    }
}