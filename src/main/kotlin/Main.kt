import kotlinx.coroutines.runBlocking

fun main() {
    val repository = JmaRepository(
        HttpClientProvider
    )
    runBlocking {
        repository.fetch()
    }
}
