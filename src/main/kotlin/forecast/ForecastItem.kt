package forecast

/**
 * 天気予報（今日・明日・明後日まで）
 */
data class ForecastItem(
    // 天気（晴れ、くもり など）
    val weather: String,
    // 波
    val wave: String,
    // 風
    val wind: String
)
