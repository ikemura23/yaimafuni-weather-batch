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

        // 2件の配列が入ってる、0は今日、1が明日
        val timeSeries: JSONArray = (jsonArray.first() as JSONObject).getJSONArray("timeSeries")
        val areas: JSONArray = (timeSeries.first() as JSONObject).getJSONArray("areas") as JSONArray

        val todayForecast = getIshigakiForecast(areas.first() as JSONObject)
        val tomorrowForecast = getIshigakiForecast(areas[1] as JSONObject)

        return Forecast(
            reportDateTime = reportDateTime,
            publishingOffice = publishingOffice,
            forecasts = listOf(todayForecast, tomorrowForecast)
        )
    }

    /**
     * 石垣の天気予報（天気・風・波）を取得
     */
    private fun getIshigakiForecast(jsonObject: JSONObject): ForecastItem {
        val waves = jsonObject.getJSONArray("waves").first().toString()
        val weather = jsonObject.getJSONArray("weathers").first().toString()
        val wind = jsonObject.getJSONArray("winds").first().toString()

        return ForecastItem(
            weather = weather,
            wind = wind,
            wave = waves
        )
    }
}
