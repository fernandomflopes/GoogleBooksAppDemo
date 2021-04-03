package br.com.fernandomflopes.googlebooks.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fernandomflopes.googlebooks.R
import br.com.fernandomflopes.googlebooks.model.Volume
import br.com.fernandomflopes.googlebooks.ui.BookDetailActivity
import br.com.fernandomflopes.googlebooks.ui.adapter.BookListAdapter
import br.com.fernandomflopes.googlebooks.ui.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_book_list.view.*

class BookListFragment: Fragment() {

    val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                is BookListViewModel.State.Loading ->
                    view.viewLoading.visibility = View.VISIBLE
                is BookListViewModel.State.Loaded -> {
                    view.recyclerView.adapter = BookListAdapter(state.items) { vol ->
                        BookDetailActivity.open(requireContext(), vol)
                    }
                    view.viewLoading.visibility = View.GONE
                }
                is BookListViewModel.State.Error -> {
                    if (!state.hasConsumed)
                        Toast.makeText(requireContext(), "OPS", Toast.LENGTH_SHORT).show()
                    view.viewLoading.visibility = View.GONE
                }
            }

        }
        viewModel.loadBooks(getString(R.string.googleBooksKey))
        view.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(txt: String?): Boolean {
                if (txt != null) {
                    viewModel.loadBooks(getString(R.string.googleBooksKey), txt)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean = true


        })
    }

    companion object {
        val TAG = "BOOK_EXTRA"

        fun open(ctx: Context, volume: Volume) {
            val intent = Intent(ctx, BookDetailActivity::class.java).apply {
                putExtra(TAG, volume)
            }
            ctx.startActivity(intent)
        }
    }
}