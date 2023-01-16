package weatherwarning.mapper

import org.json.JSONObject
import weatherwarning.model.WeatherWarning

/**
 * Json文字列から WeatherWarningクラスに変換する
 */
class WeatherWarningMapper {
    fun fromJson(json: String): WeatherWarning {
        val jsonObject = JSONObject(json)
        return WeatherWarning(
            reportDateTime = jsonObject.getString("reportDatetime"),
            publishingOffice = jsonObject.getString("publishingOffice"),
            comment = jsonObject.getString("headlineText")
        )
    }
}
