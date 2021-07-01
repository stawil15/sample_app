package com.example.gotennaasssesment.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.gotennaasssesment.R
import com.example.gotennaasssesment.databinding.ActivityMainBinding
import networking.CrimesAPI
import repo.CrimeRepo

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private val adapter = CrimeAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        initCrimes()

        binding.btError.setOnClickListener {
            viewModel.getCrimes()
        }
    }

    private fun initCrimes() {
        binding.recyclerview.adapter = adapter

        viewModel.getCrimes()

        viewModel.crimes.observe(this, Observer {
            adapter.setData(it)
        })
    }
}
