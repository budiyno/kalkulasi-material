package com.budi.kalkulasimaterial

import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class MainViewModel : ViewModel() {

    private val base = 48000
    private val ml = 0.35
    private val labelBase = 9800
    private val cartonBase = 12
    private val capBase = 3800
    private val pfBase = 20000

    fun totalVolumes(blending: String, at: String, batch: String) : Int {
        return (blending.toInt() + at.toInt() +(batch.toInt() * base))
    }

    // hitung kebutuhan label dengan rumus kebutuhan - ketersediaan
    fun remainLabel(totalVolume: String, stockLabel: String) : BigDecimal{
        val labelPcs = stockLabel.toInt() * labelBase //convert to pcs
        val bottle = (totalVolume.toDouble() / ml)
        val rejection = 0.3/100*bottle
        val endStock = (labelPcs - bottle - rejection) / labelBase //convert to roll after calculation
        return endStock.toBigDecimal().setScale(0, RoundingMode.HALF_DOWN)
    }

    // hitung kebutuhan karton dengan rumus kebutuhan - ketersediaan
    fun remainCar(totalVolume: String, stockCar: String) : BigDecimal{
        val bottle = (totalVolume.toDouble() / ml)
        val rejection = 0.3/100*bottle
        val endStock = stockCar.toInt() - (bottle / cartonBase) - (rejection / cartonBase)
        return endStock.toBigDecimal().setScale(0, RoundingMode.HALF_DOWN)
    }

    // hitung kebutuhan pf
    fun remainPf(totalVolume: String, stockPf: String) : BigDecimal {
        val bottle = (totalVolume.toDouble() / ml)
        val rejection = 0.3/100*bottle
        val stock = (stockPf.toInt() * pfBase) - bottle - rejection //convert to pcs before calculation
        val endStock = stock / pfBase //convert to bin
        return endStock.toBigDecimal().setScale(0, RoundingMode.HALF_DOWN)
    }

    // hitung kebutuhan cap dengan rumus kebutuhan - ketersediaan
    fun remainCap(totalVolume: String, stockCap: String) : BigDecimal{
        val bottle = (totalVolume.toDouble() / ml)
        val rejection = 0.3/100*bottle
        val stock = (stockCap.toInt() * capBase) - bottle - rejection //convert to pcs before calculation
        val endStock = stock / capBase //convert to bin
        return endStock.toBigDecimal().setScale(0, RoundingMode.HALF_DOWN)
    }

}