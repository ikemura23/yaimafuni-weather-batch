import service.TimeSeriesWeatherService

fun main() {
    val repository = JmaRepository(
        HttpClientProvider
    )
    TimeSeriesWeatherService(repository).start()
}
