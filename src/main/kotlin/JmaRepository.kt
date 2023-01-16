import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import timeseries.mapper.TimeSeriesMapper
import timeseries.model.TimeSeries
import weatherwarning.mapper.WeatherWarningMapper
import weatherwarning.model.WeatherWarning

/**
 * 気象庁のAPIと通信するRepository
 */
class JmaRepository(
    private val httpClient: HttpClient,
    private val mapper: TimeSeriesMapper = TimeSeriesMapper(),
    private val isDebug: Boolean = true
) {
    /**
     * 時系列天気を取得する
     */
    suspend fun fetchTimeSeries(): TimeSeries {
        val httpResponse = httpClient.get("https://www.jma.go.jp/bosai/jmatile/data/wdist/VPFD/474010.json")
        if (isDebug) println("httpResponse: $httpResponse")
        if (isDebug) println("bodyAsText: ${httpResponse.bodyAsText()}")

        val timeSeriesWeather = mapper.fromJson(httpResponse.bodyAsText())
        if (isDebug) println("timeSeriesWeather: $timeSeriesWeather")
        return timeSeriesWeather
    }

    /**
     * 天気の警告・注意を取得する
     */
    suspend fun fetchWeatherWarning(): WeatherWarning {
        val httpResponse = httpClient.get("https://www.jma.go.jp/bosai/warning/data/warning/474000.json")
        if (isDebug) println("httpResponse: $httpResponse")
        if (isDebug) println("bodyAsText: ${httpResponse.bodyAsText()}")
        val mapper = WeatherWarningMapper()
        return mapper.fromJson(httpResponse.bodyAsText())
    }
}
