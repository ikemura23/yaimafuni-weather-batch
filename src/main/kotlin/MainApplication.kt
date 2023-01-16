import kotlinx.coroutines.runBlocking

/**
 * 石垣島 地域時系列予報 を扱うサービスクラス
 *
 * url: https://www.jma.go.jp/bosai/wdist/timeseries.html#area_type=offices&area_code=474000
 * 詳細: https://github.com/ikemura23/yaimafuni-batch/issues/61#issue-1503651425
 */
class MainApplication(
    private val jmaRepository: JmaRepository
) {

    fun start() {
        runBlocking {
            val timeSeriesWeather = jmaRepository.fetchTimeSeries()
            println("timeSeriesWeather: $timeSeriesWeather")

            val weatherWarning = jmaRepository.fetchWeatherWarning()
            println("weatherWarning: $weatherWarning")
        }
    }
}
