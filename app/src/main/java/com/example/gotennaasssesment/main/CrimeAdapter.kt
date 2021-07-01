package com.example.gotennaasssesment.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.gotennaasssesment.R
import com.example.gotennaasssesment.databinding.ViewCrimeListItemBinding
import info.InfoActivity
import model.CrimeResponse

class CrimeAdapter(val context: Context): RecyclerView.Adapter<CrimeViewHolder>() {

    private var data: List<CrimeResponse> = emptyList()

    fun setData(data: List<CrimeResponse>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        return CrimeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_crime_list_item, parent, false), context)
    }

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        holder.crime = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class CrimeViewHolder(itemView: View, val context:Context): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val binding: ViewCrimeListItemBinding? = DataBindingUtil.bind(itemView)

    init {
        binding?.clBack?.setOnClickListener(this)
    }

    var crime: CrimeResponse? = null
    set(value) {
        field = value
        field?.let {
            binding?.tvCategory?.text = it.category
            binding?.tvMonth?.text = it.month
            binding?.tvOutcome?.text = it.outcomeStatus?.category
        }
    }

    inner class CrimeItemViewModel: ViewModel()

    override fun onClick(p0: View?) {
        crime?.let {
            InfoActivity.newIntent(context, it)
        }
    }
}
