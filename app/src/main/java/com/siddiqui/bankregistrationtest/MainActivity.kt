package com.siddiqui.bankregistrationtest

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.siddiqui.bankregistrationtest.databinding.ActivityMainBinding
import com.siddiqui.bankregistrationtest.viewmodel.BankRegistrationViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: BankRegistrationViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.submitBtn.setOnClickListener {
            if (viewModel.isFormValid.value == true) {
                Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }
}