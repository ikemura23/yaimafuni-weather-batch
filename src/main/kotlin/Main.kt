fun main() {
    val repository = JmaRepository(
        HttpClientProvider.create()
    )
    MainApplication(repository).start()
}
