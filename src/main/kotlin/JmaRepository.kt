import io.ktor.client.request.*
import io.ktor.client.statement.*

/**
 * 気象庁のAPIと通信するRepository
 */
class JmaRepository(
    private val httpClientProvider: HttpClientProvider
) {
    suspend fun fetch() {
        val client = httpClientProvider.create()
        val httpResponse = client.get("https://www.jma.go.jp/bosai/jmatile/data/wdist/VPFD/474020.json")
        println("httpResponse: $httpResponse")
        println("bodyAsText: ${httpResponse.bodyAsText()}")
    }

}