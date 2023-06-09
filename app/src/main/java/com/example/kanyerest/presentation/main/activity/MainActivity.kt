package com.example.kanyerest.presentation.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.example.kanyerest.databinding.ActivityMainBinding
import com.example.kanyerest.presentation.state.MainState
import com.example.kanyerest.presentation.main.viewmodel.MainViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val tvQuotes = binding.tvQuotes
        val btnQuotesSearch = binding.btnQuotesSearch

        mainViewModel.state.observe(this, Observer { state ->
            when(state){
                is MainState.ShowItems -> {

                    tvQuotes.text = state.items

                }
                else -> Log.d("stateHomeActivity", "retornou com erro")
        }
        })
        MainScope().launch {
            mainViewModel.searchQuote(btnQuotesSearch)
        }
    }


}