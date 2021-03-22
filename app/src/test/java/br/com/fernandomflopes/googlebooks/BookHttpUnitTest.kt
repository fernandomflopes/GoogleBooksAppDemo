package br.com.fernandomflopes.googlebooks

import android.app.Activity
import br.com.fernandomflopes.googlebooks.model.BookHttp
import org.junit.Test

import org.junit.Assert.*


class BookHttpUnitTest {
    private val KEY = "MY_KEY"

    @Test
    fun googleBooksApiTest() {

        val searchResult = BookHttp.searchBook(KEY,"Dominando o Android")
        val n = searchResult?.items?.size!! > 0

        assertTrue(n)
    }
}