import io.ktor.client.*

object HttpClientProvider {
    fun create(): HttpClient {
        return HttpClient()
    }
}