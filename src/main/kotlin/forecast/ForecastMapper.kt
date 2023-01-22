package forecast

import org.json.JSONArray
import org.json.JSONObject

/**
 * 天気予報をモデルに変換する
 */
class ForecastMapper {
    fun fromJson(json: String): Forecast {
        val jsonArray = JSONArray(json)

        val reportDateTime = (jsonArray.first() as JSONObject).getString("reportDatetime")
        val publishingOffice = (jsonArray.first() as JSONObject).getString("publishingOffice")

        val timeSeries = (jsonArray.first() as JSONObject).getJSONArray("timeSeries")
        // println("timeSeries: $timeSeries")
        val todayTimeSeries: JSONObject = timeSeries.first() as JSONObject
        println("todayTimeSeries: $todayTimeSeries")
        // 2件の配列が入ってる、0は今日、1が明日
        val timeDefines: List<String> = (timeSeries.first() as JSONObject).getJSONArray("timeDefines").map { it.toString() }
        // areasには2件入っている、最初が石垣で次は与那国
        val areas: JSONArray = (timeSeries.first() as JSONObject).getJSONArray("areas")
        println("areas: $areas")

        val ishigaskiArea = areas.first() as JSONObject
        val todayWaves = ishigaskiArea.getJSONArray("waves").first().toString()
        val todayWeather = ishigaskiArea.getJSONArray("weathers").first().toString()
        val todayWind = ishigaskiArea.getJSONArray("winds").first().toString()
        println(todayWaves)
        println(todayWeather)
        println(todayWind)

        return Forecast(
            reportDateTime = reportDateTime,
            publishingOffice = publishingOffice,
            weather = todayWaves,
            wind = todayWind,
            wave = todayWaves
        )
    }
}
