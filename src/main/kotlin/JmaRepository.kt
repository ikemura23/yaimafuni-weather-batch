import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import timeseries.mapper.TimeSeriesMapper
import timeseries.model.TimeSeries

/**
 * 気象庁のAPIと通信するRepository
 */
class JmaRepository(
    private val httpClientProvider: HttpClientProvider,
    private val mapper: TimeSeriesMapper = TimeSeriesMapper(),
    private val isDebug: Boolean = false
) {
    /**
     * 時系列天気を取得する
     */
    suspend fun fetchTimeSeries(): TimeSeries {
        val client = httpClientProvider.create()
        val httpResponse = client.get("https://www.jma.go.jp/bosai/jmatile/data/wdist/VPFD/474010.json")
        if (isDebug) println("httpResponse: $httpResponse")
        if (isDebug) println("bodyAsText: ${httpResponse.bodyAsText()}")

        val timeSeriesWeather = mapper.fromJson(httpResponse.bodyAsText())
        if (isDebug) println("timeSeriesWeather: $timeSeriesWeather")
        return timeSeriesWeather
    }
}
