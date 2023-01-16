import timeseries.service.TimeSeriesService

fun main() {
    val repository = JmaRepository(
        HttpClientProvider.create()
    )
    TimeSeriesService(repository).start()
}
