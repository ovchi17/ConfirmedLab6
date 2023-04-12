package commandsHelpers

import usersView.ConsoleWriter

class Help {

    /**
     * execute method. Returns add commands with info
     *
     * @return info from command as ResultModule
     */
    fun execute() {
        val writeToConsole: ConsoleWriter = ConsoleWriter()

        writeToConsole.printToConsoleLn("***")

        writeToConsole.printToConsoleLn("helpHelp")
        writeToConsole.printToConsoleLn("helpInfo")
        writeToConsole.printToConsoleLn("helpShow")
        writeToConsole.printToConsoleLn("helpAdd")
        writeToConsole.printToConsoleLn("helpUpdate")
        writeToConsole.printToConsoleLn("helpRemove")
        writeToConsole.printToConsoleLn("helpClear")
        writeToConsole.printToConsoleLn("helpSave")
        writeToConsole.printToConsoleLn("helpExecute")
        writeToConsole.printToConsoleLn("helpExit")
        writeToConsole.printToConsoleLn("helpRemoveFirst")
        writeToConsole.printToConsoleLn("helpAddIfMax")
        writeToConsole.printToConsoleLn("helpHistory")
        writeToConsole.printToConsoleLn("helpRemoveAllByDistance")
        writeToConsole.printToConsoleLn("helpAverageOfDistance")
        writeToConsole.printToConsoleLn("helpFilterLessThanDistance")
        writeToConsole.printToConsoleLn("helpSwitchCollection")

        writeToConsole.printToConsoleLn("***")

    }
}