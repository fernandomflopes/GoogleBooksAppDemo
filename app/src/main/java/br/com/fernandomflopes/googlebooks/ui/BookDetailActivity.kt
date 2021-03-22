package br.com.fernandomflopes.googlebooks.ui

import android.content.Context
import android.content.Intent
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

        val volume = intent.getParcelableExtra<Volume>(TAG)
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