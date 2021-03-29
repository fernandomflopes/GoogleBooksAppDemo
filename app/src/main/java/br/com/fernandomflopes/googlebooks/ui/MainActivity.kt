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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}