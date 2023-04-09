package di

import controllers.*
import moduleWithResults.WorkWithResultModule
import org.koin.dsl.module


val koinModule = module {

    single<CollectionMainCommands> {
        WorkWithCollection()
    }

    single {
        ConsoleWriter()
    }

    single {
        Tokenizator()
    }

    single {
        AddSet()
    }

    single {
        WorkWithFile()
    }

    single {
        Serializer()
    }

    factory {
        WorkWithResultModule()
    }

    single {
        Parametrs()
    }

}
