package com.jusfoun

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.junit.Before
import org.junit.Test

import java.io.IOException
import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.TimeUnit

/**
 * Create on 2017-06-23.
 * @author Jeffer Lau <jefferlzu></jefferlzu>@gmail.com>
 */
class OkHttp3Tests {

    private var okHttpClient: OkHttpClient? = null
    private val cookieStore = HashMap<String, List<Cookie>>()

    @Before
    fun init() {
        okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .cookieJar(object : CookieJar {
                    override fun saveFromResponse(httpUrl: HttpUrl, list: List<Cookie>) {
                        cookieStore.put(httpUrl.host(), list)
                    }

                    override fun loadForRequest(httpUrl: HttpUrl): List<Cookie> {
                        val cookies = cookieStore[httpUrl.host()]
                        return cookies ?: ArrayList<Cookie>()
                    }
                })
                .build()
    }

    /**
     * 同步 GET
     * @throws IOException
     */
    @Test
    @Throws(IOException::class)
    fun syncGet() {
        val httpUrl = HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegments(PATH + API_TOPICS)
                .addEncodedQueryParameter("page", "1")
                .addQueryParameter("tab", TAB_DEV)
                .addQueryParameter("limit", "2")
                .addQueryParameter("mdrender", "true")
                .build()

        val request = Request.Builder().url(httpUrl).build()

        val call = okHttpClient!!.newCall(request)
        val response = call.execute()

        if (!response.isSuccessful) throw IOException("Unexpected code " + response)

        printResponseHeaders(response)
        printResponseBody(response)
    }

    /**
     * 异步 GET
     * @throws IOException
     */
    @Test
    @Throws(IOException::class)
    fun asyncGet() {
        val request = Request.Builder().url("http://publicobject.com/helloworld.txt").build()

        okHttpClient!!.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) throw IOException("Unexpected code " + response)

                printResponseHeaders(response)
                printResponseBody(response)
            }
        })

        val start = System.currentTimeMillis()
        while (true) {
            val end = System.currentTimeMillis()
            if (end - start > 10000) {
                break
            }
        }
    }

    @Test
    @Throws(IOException::class)
    fun postFormBody() {
        val title = "测试帖"
        val content = "主题内容"

        val formBody = FormBody.Builder()
                .add("title", title)
                .add("content", content)
                .add("accesstoken", "")
                .add("tab", TAB_DEV)
                .build()

        val httpUrl = HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegments(PATH + API_TOPICS)
                .build()

        val request = Request.Builder()
                .url(httpUrl)
                .post(formBody)
                .build()

        val call = okHttpClient!!.newCall(request)
        val response = call.execute()

        if (!response.isSuccessful) throw IOException("Unexpected code " + response)

        printResponseHeaders(response)
        printResponseBody(response)
    }

    @Test
    @Throws(IOException::class)
    fun postBody() {
        val postBody = """
Releases
---

* 1.0
* 1.1
* 1.2
"""

        val request = Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build()

        val response = okHttpClient!!.newCall(request).execute()
        if (!response.isSuccessful) throw IOException("Unexpected code " + response)

        printResponseHeaders(response)
        printResponseBody(response)
    }

    private fun printResponseHeaders(response: Response) {
        val headers = response.headers()
        val names = headers.names()
        println("========================= Response Headers ===============================")
        for (name in names) {
            println(String.format("Response Header: %s - %s", name, headers.get(name)))
        }
    }

    @Throws(IOException::class)
    private fun printResponseBody(response: Response) {
        println("========================= Response Body ==================================")
        println(response.body()!!.string())
    }

    companion object {

        private val SCHEME = "https"
        private val HOST = "cnodejs.org"
        private val PATH = "api/v1"

        private val API_TOPICS = "/topics"
        private val ACCESSTOKEN = ""
        private val TAB_DEV = "dev"

        val MEDIA_TYPE_JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val MEDIA_TYPE_MARKDOWN: MediaType? = MediaType.parse("text/x-markdown; charset=utf-8")
    }
}
