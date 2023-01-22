package forecast

/**
 * 天気予報（今日・明日・明後日まで）
 */
data class Forecast(
    // 発表時間
    val reportDateTime: String,
    // 発表場所
    val publishingOffice: String,
    // 天気（晴れ、くもり など）
    val weather: String,
    // 波
    val wave: String = "",
    // 風
    val wind: String = ""
)
