package br.com.fernandomflopes.googlebooks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fernandomflopes.googlebooks.R
import br.com.fernandomflopes.googlebooks.model.Volume
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val volume = intent.getParcelableExtra<Volume>("EXTRA_BOOK")
        if (volume != null) {


            if(volume.volumeInfo.imageLinks != null) {
                Picasso.get().load(volume?.volumeInfo?.imageLinks?.smallThumbnail?.replace("http", "https")).into(
                    imgCover
                )

            }

            txtTitle.text = volume.volumeInfo.title
            txtAuthor.text = volume.volumeInfo.authors?.joinToString() ?: ""
            txtPages.text = volume.volumeInfo.pageCount.toString() ?: "-"
            txtDescription.text = volume.volumeInfo.description
            txtPublisher.text = volume.volumeInfo.publisher

        } else {
            finish()
        }

    }
}