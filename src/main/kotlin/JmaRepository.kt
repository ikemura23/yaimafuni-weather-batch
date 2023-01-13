import io.ktor.client.request.*
import io.ktor.client.statement.*
import model.TimeSeriesWeatherItem

/**
 * 気象庁のAPIと通信するRepository
 */
class JmaRepository(
    private val httpClientProvider: HttpClientProvider,
    private val mapper: TimeSeriesWeatherMapper = TimeSeriesWeatherMapper()
) {
    suspend fun fetch() {
        val client = httpClientProvider.create()
        val httpResponse = client.get("https://www.jma.go.jp/bosai/jmatile/data/wdist/VPFD/474010.json")
        println("httpResponse: $httpResponse")
        println("bodyAsText: ${httpResponse.bodyAsText()}")

        val timeSeriesWeather = mapper.fromJson(httpResponse.bodyAsText())
        println("timeSeriesWeather: $timeSeriesWeather")
    }
}


