package com.example.myapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.example.myapplication.androidwrapper.ActUtils
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.GetApiModel
import com.example.myapplication.remote.RetrofitService
import com.example.myapplication.remote.dataModel.DefaultModel
import com.example.myapplication.remote.ext.ErrorUtils
import com.example.myapplication.ui.MainActivity2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("ViewConstructor")
class ViewMainActivity(
    private val contextInstance: Context,
    private val utils:ActUtils
) : FrameLayout(contextInstance) {

    val binding = ActivityMainBinding.inflate(
        LayoutInflater.from(context)
    )

    fun onClickHandler(androidId: String) {
        binding.btnSend.setOnClickListener {
            val email = binding.edtInputEmail.text.toString()
            if (email.isEmpty()) {
                binding.textInputEmail.error = "email empty"
                return@setOnClickListener
            }
            sendCodeInEmail(email)

            binding.btnSend.setOnClickListener {
                binding.btnSend.visibility = INVISIBLE
                binding.textInputEmail.visibility = INVISIBLE

                binding.txtSendEmail.visibility = VISIBLE
                binding.textInputCode.visibility = VISIBLE
                binding.btnConfirm.visibility = VISIBLE
                binding.txtWrong.visibility = VISIBLE

                binding.txtSendEmail.text = "send code to email:$email"
            }

            binding.txtWrong.setOnClickListener {
                binding.btnSend.visibility = VISIBLE
                binding.textInputEmail.visibility = VISIBLE

                binding.txtSendEmail.visibility = INVISIBLE
                binding.textInputCode.visibility = INVISIBLE
                binding.btnConfirm.visibility = INVISIBLE
                binding.txtWrong.visibility = INVISIBLE

                binding.txtSendEmail.text = "send code to email:$email"
            }

            binding.btnConfirm.setOnClickListener {

                val code = binding.edtCode.text.toString()
                val email = binding.edtInputEmail.text.toString()

                if (code.isEmpty()) {
                    binding.textInputCode.error = "error"
                    return@setOnClickListener
                } else
                    binding.textInputCode.error = null
                verifyCode(androidId, code, email)
            }

        }
    }

    private fun sendCodeInEmail(email: String) {

        val service = RetrofitService.apiService

        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = service.sendRequest(email)

                if (response.isSuccessful) {
                    launch(Dispatchers.Main) {
                        val data = response.body() as GetApiModel
                        data.api
                        Toast.makeText(context, "لاگین موفقیت آمیز بود", Toast.LENGTH_SHORT).show()
                        context.startActivity(Intent(context, MainActivity2::class.java))
                        utils.finished()
                    }
                } else
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, ErrorUtils.getError(response), Toast.LENGTH_SHORT)
                            .show()
                    }
            } catch (e: Exception) {
                Log.i("SERVER_ERROR", e.message.toString())
            }

        }

    }

    private fun verifyCode(androidId: String, code: String, email: String) {

        val service = RetrofitService.apiService

        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = service.verifyCode(androidId, code, email)
                if (response.isSuccessful) {
                    launch(Dispatchers.Main) {
                        val data = response.body() as DefaultModel
                        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
                    }
                } else
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, ErrorUtils.getError(response), Toast.LENGTH_SHORT)
                            .show()
                    }
            } catch (e: Exception) {
                Log.i("SERVER_ERROR", e.message.toString())
            }

        }

    }
}