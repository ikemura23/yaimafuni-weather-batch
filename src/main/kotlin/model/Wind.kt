package model

/**
 * 風
 */
data class Wind(
    // 風向き（南など）
    val direction: String,
    // 風速（4など）
    val speed: Int,
    // 風速（9 10など）
    val range: String,
)