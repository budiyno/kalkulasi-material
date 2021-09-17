package com.budi.kalkulasimaterial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.budi.kalkulasimaterial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    private var bt: String = ""
    private var batch: String = ""
    private var car: String = ""
    private var at: String = ""
    private var label: String = ""
    private var cap: String = ""
    private var pf: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.btnCalculation.setOnClickListener {
            bt = activityMainBinding.edMixingVol.text.toString().trim()
            at = activityMainBinding.edAt.text.toString().trim()
            batch = activityMainBinding.edBatch.text.toString().trim()
            car = activityMainBinding.edStockKarton.text.toString().trim()
            label = activityMainBinding.edStockLabel.text.toString().trim()
            cap = activityMainBinding.edStockCap.text.toString().trim()
            pf = activityMainBinding.edStockPreform.text.toString().trim()

            when {
                bt.isEmpty() -> {
                    activityMainBinding.edMixingVol.error = "Kolom masih kosong"
                }
                at.isEmpty() -> {
                    activityMainBinding.edAt.error = "Kolom masih kosong"
                }
                batch.isEmpty() -> {
                    activityMainBinding.edBatch.error = "Kolom masih kosong"
                }
                car.isEmpty() -> {
                    activityMainBinding.edStockKarton.error = "Kolom masih kosong"
                }
                label.isEmpty() -> {
                    activityMainBinding.edStockLabel.error = "Kolom masih kosong"
                }
                cap.isEmpty() -> {
                    activityMainBinding.edStockCap.error = "Kolom masih kosong"
                }
                pf.isEmpty() -> {
                    activityMainBinding.edStockPreform.error = "Kolom masih kosong"
                }
                else -> {
                    calculation()

                }

            }
        }
    }

    private fun calculation() {
        val volume = viewModel.totalVolumes(bt, at, batch).toString()
        val preform = viewModel.remainPf(volume, pf).toString()
        val caps = viewModel.remainCap(volume, cap).toString()
        val cart = viewModel.remainCar(volume, car).toString()
        val labels = viewModel.remainLabel(volume, label).toString()

        val intent = Intent(this, ResultActivity::class.java)
        val data = Data(volume, caps, preform, cart, labels)
        intent.putExtra(ResultActivity.EXTRA_DATA, data)
        startActivity(intent)
    }

}


