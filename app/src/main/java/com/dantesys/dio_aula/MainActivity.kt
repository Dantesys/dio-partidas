package com.dantesys.dio_aula

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dantesys.dio_aula.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}