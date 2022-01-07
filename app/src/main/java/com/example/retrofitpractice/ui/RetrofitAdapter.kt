package com.example.retrofitpractice.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpractice.R
import com.example.retrofitpractice.databinding.CustomRowAdapterBinding
import com.example.retrofitpractice.model.responses.Posts
import retrofit2.Response

class RetrofitAdapter(private var information: Response<List<Posts>>) :
    RecyclerView.Adapter<RetrofitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.custom_row_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val notification = information.body()?.get(position)
        if (notification != null) {

            holder.bind(notification)
        }
    }

    override fun getItemCount(): Int {

        return information.body()!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = CustomRowAdapterBinding.bind(view)

        fun bind(notification: Posts) {

            binding.tvUserId.text = notification.userId.toString()
            binding.tvId.text = notification.id.toString()
            binding.tvTitle.text = notification.title
            binding.tvBody.text = notification.body
        }
    }
}