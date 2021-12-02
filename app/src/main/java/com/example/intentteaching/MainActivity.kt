package com.example.intentteaching

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentteaching.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var resultLauncher2: ActivityResultLauncher<Intent>
    companion object
    {
        private const val FIRST_ACTIVITY_REQUEST_CODE=1
        const val Name = "name"
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnlaunchactivityfirst.setOnClickListener{
            var intent = Intent(this,FirstActivity::class.java)
            //intent.putExtra("info",binding.tvfirstactivityresult.toString())
            resultLauncher.launch(intent)
        }

        binding.btnlaunchactivitysecond.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            resultLauncher2.launch(intent)

        }
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult() ){result ->
            if(result.resultCode == Activity.RESULT_OK)
            {
                val data: Intent? = result.data
                binding.tvfirstactivityresult.text="First activity success"
            }
        }
        resultLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK)
            {
                val data: Intent? = result.data
                binding.tvsecondactivityresult.text=data?.getStringExtra(Name)
                binding.tvsecondactivityresult2.text=data?.getStringExtra("email")
            }

        }
    }
}