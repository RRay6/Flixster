package com.example.flixster

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R.id

/**
 * [RecyclerView.Adapter] that can display a [BestSellerBook] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MovieRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
//        val mBookTitle: TextView = mView.findViewById<View>(id.book_title) as TextView
//        val mBookAuthor: TextView = mView.findViewById<View>(id.book_author) as TextView

        val mMovieTitle: TextView = mView.findViewById<View>(id.title) as TextView
        val mMovieReleaseDate: TextView = mView.findViewById<View>(id.release_date) as TextView
        // val mMoviePopularity: TextView = mView.findViewById<View>(id.ranking) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(id.overview) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(id.image) as ImageView
        // val mBookButton: Button = mView.findViewById<View>(id.buy_button) as Button

//        override fun toString(): String {
//            return mMovieTitle.toString() + " '" + mBookAuthor.text + "'"
//        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
//        holder.mBookTitle.text = book.title
//        holder.mBookAuthor.text = book.author

        holder.mMovieTitle.text = movie.title
        holder.mMovieReleaseDate.text = movie.releaseDate
        holder.mMovieDescription.text = movie.overview
        // holder.mMoviePopularity.text = movie.popularity.toString()

//        holder.mBookButton.setOnClickListener {
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.amazonUrl))
//            startActivity(it.context, browserIntent, null)
//        }

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500"+movie.posterPath)
            .centerInside()
            .into(holder.mMovieImage)

//        holder.mView.setOnClickListener {
//            holder.mItem?.let { book ->
//                mListener?.onItemClick(book)
//            }
//        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}