package weatherwarning.model

/**
 * 天気の警報・注意報
 */
data class WeatherWarning(
    // 発表時間
    val reportDateTime: String,
    // 発表場所（石垣島地方気象台で固定？）
    val publishingOffice: String,
    // コメント
    val comment: String
)
