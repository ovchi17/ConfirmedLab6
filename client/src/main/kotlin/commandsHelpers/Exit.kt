package commandsHelpers

import ClientModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.system.exitProcess

class Exit: KoinComponent {

    val clientModule: ClientModule by inject()

    fun execute(){
        clientModule.stop()

        exitProcess(0)
    }
}