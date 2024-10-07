import okhttp3.Interceptor
import okhttp3.Response
import android.util.Log

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("HTTP Request", "URL: ${request.url}")
        return chain.proceed(request)
    }
}
