package info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gotennaasssesment.R
import com.example.gotennaasssesment.databinding.ActivityInfoBinding
import model.CrimeResponse

class InfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityInfoBinding
    lateinit var viewModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        binding.viewModel = viewModel

        val crimeData = intent.extras?.get(CRIME_INTENT_EXTRA)

        if (crimeData == null) {
            finish()
        } else {
            viewModel.crimeData.value = crimeData as CrimeResponse
        }
    }

    companion object {
        const val CRIME_INTENT_EXTRA = "CRIME INTENT EXTRA"

        fun newIntent(context: Context, crime: CrimeResponse) {
            context.startActivity(Intent(context, InfoActivity::class.java).apply {
                putExtra(CRIME_INTENT_EXTRA, crime)
            })
        }
    }
}