package model

data class TimeSeriesWeatherItem(
    // 時間
    val dateTime: String,
    // 天気（晴れ、くもり など）
    val weather: String,
    // 風
    val wind: Wind,
//    // 気温
//    val temperature:Int // 気温だけ要素数が1つ多くて数が合わないので、コメントアウトしておく
)