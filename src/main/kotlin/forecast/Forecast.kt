package forecast

/**
 * 天気予報（今日と明日の天気は配列で持つ）
 */
data class Forecast(
    // 発表時間
    val reportDateTime: String,
    // 発表場所
    val publishingOffice: String,
    // 天気（今日、明日）
    val forecasts: List<ForecastItem>
)
