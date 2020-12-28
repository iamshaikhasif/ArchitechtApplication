package shock.com.architechtapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import shock.com.architechtapplication.base.BaseActivity
import shock.com.architechtapplication.databinding.ActivityMainBinding
import kotlin.math.floor
import kotlin.math.sqrt

class HomeActivity : BaseActivity<HomeViewModel, ActivityMainBinding> (HomeViewModel::class.java){
    var count = 0
    var f = false

    override fun getLayoutRes() = R.layout.activity_main

    override fun initViewModel(viewModel: HomeViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataSource.putData("welcome", "Asif")
        val data = dataSource.getData("Asif", "false")
        Toast.makeText(this, "$data" , Toast.LENGTH_SHORT).show()
        val btn = findViewById<Button>(R.id.btn)
        val text = findViewById<TextView>(R.id.textView)

        btn.setOnClickListener {
            if(f){
                text.text = count.toString()
                count = 0
            }
        }

        viewModel.number.observe(this, Observer {
            if(it != null){
                if (it != ""){
                    try {
                        f = true
                        perfectSqureCount(it.toInt())
                    }catch (e:Exception){
                        Toast.makeText(this, e.message , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    fun perfectSqureCount(n: Int){
        for (i in 1..n){
            val sq = sqrt(i.toDouble())
            val a = sq - floor(sq)
            if (a == 0.0){
                count += 1
            }
        }
    }

}