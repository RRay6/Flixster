package com.example.flixster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the NY Times API.
 */
class MovieFragment : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        client[
                "https://api.themoviedb.org/3/movie/now_playing?api_key=34e61e3ddd6944220f1e09bcfb1b726d",
                params,
                object : JsonHttpResponseHandler() {
                    //connect these callbacks to your API call
                    override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {

                        progressBar.hide()

                        // Access a JSON array response with `json.jsonArray`
                        //val movies = json.jsonObject.getJSONObject("results")
                        // books.getJSONObject(2)
                        val movies = json.jsonObject.get("results");

                        val gson = Gson()
                        val arrayBookType = object : TypeToken<List<Movie>>() {}.type
                        val models : List<Movie> = gson.fromJson(movies.toString(), arrayBookType)
                        recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)

                        Log.d("MoviesFragment", "response successful")

                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {

                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("MoviesFragment", errorResponse)
                        }

                    }
                }]
    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: Movie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

}
