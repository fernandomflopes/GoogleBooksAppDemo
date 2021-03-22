package br.com.fernandomflopes.googlebooks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fernandomflopes.googlebooks.R
import br.com.fernandomflopes.googlebooks.model.BookHttp
import br.com.fernandomflopes.googlebooks.ui.adapter.BookListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
               BookHttp.searchBook("Dominando o android", getString(R.string.googleBooksKey))
            }
            result?.items?.let {
                recyclerView.adapter = BookListAdapter(it)
            }
        }
    }
}