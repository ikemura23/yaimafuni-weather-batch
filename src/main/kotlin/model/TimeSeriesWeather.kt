package model

/**
 * 時系列天気クラス
 */
data class TimeSeriesWeather(
    // 時系列天気
    val values: List<TimeSeriesWeatherItem>,
    // 発表場所（石垣島地方気象台で固定？）
    val publishingOffice: String,
    // 発表時間
    val reportDateTime: String,
)
