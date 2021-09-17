package com.budi.kalkulasimaterial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.budi.kalkulasimaterial.databinding.ActivityResultBinding
import java.math.RoundingMode

class ResultActivity : AppCompatActivity() {

    private lateinit var activityResultBinding: ActivityResultBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(activityResultBinding.root)

        val data = intent.getParcelableExtra<Data>(EXTRA_DATA)

        val volume = data?.vol
        val ctn = data?.car?.toInt()
        val label = data?.label?.toInt()
        val cap = data?.cap?.toInt()
        val pf = data?.pf?.toInt()

        activityResultBinding.txtPerluCap.text = cap.toString()
        activityResultBinding.txtTotalSolution.text = volume.toString()
        activityResultBinding.txtPerluKarton.text = ctn.toString()
        activityResultBinding.txtPerluLabel.text = label.toString()
        activityResultBinding.txtPerluPf.text = pf.toString()

        if (ctn != null) {
            if(ctn > 0){
                activityResultBinding.tvNoteKarton.text = "Karton cukup"
            }else{
                activityResultBinding.tvNoteKarton.text = "Karton kurang : ${ctn.toInt()*-1} pcs"
            }
        }
        if (label != null) {
            if(label > 0){
                activityResultBinding.tvNoteLabel.text = "Label cukup"
            }else{
                activityResultBinding.tvNoteLabel.text = "Label kurang : ${label.toInt()*-1} roll"
            }
        }
        if (pf != null) {
            if(pf > 0){
                activityResultBinding.tvNotePf.text = "Preform cukup"
            }else{
                activityResultBinding.tvNotePf.text = "Preform kurang : ${pf.toInt()*-1} niktener"
            }
        }
        if (cap != null) {
            if(cap > 0){
                activityResultBinding.tvNoteCap.text = "Cap cukup"
            }else{
                activityResultBinding.tvNoteCap.text = "Cap kurang : ${cap.toInt()*-1} box"
            }
        }

    }
}