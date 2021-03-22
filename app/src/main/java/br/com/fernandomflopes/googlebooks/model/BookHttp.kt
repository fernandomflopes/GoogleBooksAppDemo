package br.com.fernandomflopes.googlebooks.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object BookHttp {
    private const val BOOK_JSON_URL = "https://www.googleapis.com/books/v1/volumes?q=%s&key=%s"

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()


    fun searchBook(apiKey: String, q: String) : SeachResult? {
        val request = Request.Builder()
            .url(String.format(BOOK_JSON_URL, q, apiKey))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()

            return Gson().fromJson(json, SeachResult::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}