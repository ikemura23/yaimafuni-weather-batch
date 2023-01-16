import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import timeseries.mapper.TimeSeriesMapper

/**
 * 気象庁のAPIと通信するRepository
 */
class JmaRepository(
    private val httpClientProvider: HttpClientProvider,
    private val mapper: TimeSeriesMapper = TimeSeriesMapper()
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
