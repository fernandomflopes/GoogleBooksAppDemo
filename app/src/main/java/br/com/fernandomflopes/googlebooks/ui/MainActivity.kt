package br.com.fernandomflopes.googlebooks.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fernandomflopes.googlebooks.R
import br.com.fernandomflopes.googlebooks.model.BookHttp
import br.com.fernandomflopes.googlebooks.model.Volume
import br.com.fernandomflopes.googlebooks.ui.adapter.BookListAdapter
import br.com.fernandomflopes.googlebooks.ui.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.state.observe(this) { state ->
            when(state) {
                is BookListViewModel.State.Loading ->
                    viewLoading.visibility = View.VISIBLE
                is BookListViewModel.State.Loaded -> {
                    recyclerView.adapter = BookListAdapter(state.items) { vol ->
                        BookDetailActivity.open(this@MainActivity, vol)
                    }
                    viewLoading.visibility = View.GONE
                }
                is BookListViewModel.State.Error -> {
                    Toast.makeText(this@MainActivity, "OPS", Toast.LENGTH_SHORT).show()
                    viewLoading.visibility = View.GONE
                }
            }

        }
        viewModel.loadBooks(getString(R.string.googleBooksKey))
    }
}