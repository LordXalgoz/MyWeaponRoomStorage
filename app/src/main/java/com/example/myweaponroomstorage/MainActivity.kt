package com.example.myweaponroomstorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myweaponroomstorage.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var context: Context
        val scope by lazy { CoroutineScope(SupervisorJob()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        context = applicationContext
        super.onCreate(savedInstanceState)
        with(ActivityMainBinding.inflate(layoutInflater)) {
            viewModel = MainVM()
            setContentView(root)
        }
    }
}