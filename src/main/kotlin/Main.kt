import service.TimeSeriesService

fun main() {
    val repository = JmaRepository(
        HttpClientProvider
    )
    TimeSeriesService(repository).start()
}
