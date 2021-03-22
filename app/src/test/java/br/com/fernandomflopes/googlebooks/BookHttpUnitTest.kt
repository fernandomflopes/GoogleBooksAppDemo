package br.com.fernandomflopes.googlebooks

import br.com.fernandomflopes.googlebooks.model.BookHttp
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BookHttpUnitTest {
    @Test
    fun googleBooksApiTest() {
        val searchResult = BookHttp.searchBook("Dominando o Android")
        val n = searchResult?.items?.size!! > 0

        assertTrue(n)
    }
}