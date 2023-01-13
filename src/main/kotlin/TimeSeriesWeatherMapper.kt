import model.TimeSeriesWeather
import model.TimeSeriesWeatherItem
import model.Wind
import org.json.JSONArray
import org.json.JSONObject

/**
 * 地域時系列予報をmodelに変換する
 * https://www.jma.go.jp/bosai/wdist/timeseries.html#area_type=offices&area_code=474000
 */
class TimeSeriesWeatherMapper(
    private val isDebug: Boolean = false
) {
    fun fromJson(jsonString: String): TimeSeriesWeather {
        val jsonObject = JSONObject(jsonString)
        if (isDebug) println("jsonObject: $jsonObject")

        jsonObject.getJSONObject("areaTimeSeries")

        // 時系列の時刻
        val dateTimeList = getDateTimeList(jsonObject)
        // 天気
        val weatherList = getWeatherList(jsonObject)
        // 気温
//        val temperatureList = getTemperatureList(jsonObject)
        // 風
        val windList = getWindList(jsonObject)
        // 発表場所
        val publishingOffice = getPublishingOffice(jsonObject)
        // 発表時間
        val reportDateTime = getReportDateTime(jsonObject)

        val timeSeriesWeatherItemList: List<TimeSeriesWeatherItem> = List(dateTimeList.size) { i ->
            TimeSeriesWeatherItem(
                dateTime = dateTimeList[i],
                weather = weatherList[i],
                wind = windList[i],
            )
        }
        return TimeSeriesWeather(
            values = timeSeriesWeatherItemList,
            publishingOffice = publishingOffice,
            reportDateTime = reportDateTime,
        )
    }

    // 時刻
    private fun getDateTimeList(jsonObject: JSONObject): List<String> {
        val timeDefines: JSONArray = jsonObject.getJSONObject("areaTimeSeries").getJSONArray("timeDefines")
        val dateTimeList: List<String> = timeDefines.map {
            val jo: JSONObject = it as JSONObject
            jo.getString("dateTime")
        }
        println("dateTimeList: size ${dateTimeList.size} $dateTimeList")
        return dateTimeList
    }

    // 天気
    private fun getWeatherList(jsonObject: JSONObject): List<String> {
        val jsonArray: JSONArray = jsonObject.getJSONObject("areaTimeSeries").getJSONArray("weather")
        val weatherList: List<String> = jsonArray.map {
            it.toString()
        }
        println("weatherList: size ${weatherList.size} $weatherList")
        return weatherList
    }

    // 気温 （ここだけ数が合わないためコメントアウト）
//    private fun getTemperatureList(jsonObject: JSONObject): List<String> {
//        val jsonArray: JSONArray = jsonObject.getJSONObject("pointTimeSeries").getJSONArray("temperature")
//        val temperatureList: List<String> = jsonArray.map {
//            it.toString()
//        }
//        println("temperatureList: size ${temperatureList.size} $temperatureList")
//        return temperatureList
//    }

    // 風速
    private fun getWindList(jsonObject: JSONObject): List<Wind> {
        val jsonArray: JSONArray = jsonObject.getJSONObject("areaTimeSeries").getJSONArray("wind")
        val windList: List<Wind> = jsonArray.map {
            val jo: JSONObject = it as JSONObject
            Wind(
                direction = jo.getString("direction"),
                speed = jo.getInt("speed"),
                range = jo.getString("range"),
            )
        }
        println("windList: size ${windList.size} $windList")
        return windList
    }

    // 発表時間
    private fun getReportDateTime(jsonObject: JSONObject): String {
        return jsonObject.getString("reportDateTime")
    }

    // 発表場所
    private fun getPublishingOffice(jsonObject: JSONObject): String {
        return jsonObject.getString("publishingOffice")
    }
}