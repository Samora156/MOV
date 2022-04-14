package com.example.mov

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mov.databinding.ActivityPilihBangkuBinding
import com.example.mov.model.Checkout
import com.example.mov.model.Film

class PilihBangkuActivity : AppCompatActivity() {
    lateinit var binding: ActivityPilihBangkuBinding

//    STATUS SEAT A
    var statusA1: Boolean = false
    var statusA2: Boolean = false
    var statusA3: Boolean = false
    var statusA4: Boolean = false

//    STATUS SEAT B
    var statusB1: Boolean = false
    var statusB2: Boolean = false
    var statusB3: Boolean = false
    var statusB4: Boolean = false

//    STATUS SEAT C
    var statusC1: Boolean = false
    var statusC2: Boolean = false
    var statusC3: Boolean = false
    var statusC4: Boolean = false
//    STATUS SEAT D
    var statusD1: Boolean = false
    var statusD2: Boolean = false
    var statusD3: Boolean = false
    var statusD4: Boolean = false
    var total: Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPilihBangkuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Film>("data")

            binding.tvKursi.text = data!!.judul

        binding.A1.setOnClickListener {
            if (statusA1) {
                binding.A1.setImageResource(R.drawable.ic_empty)
                statusA1 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("A1", "70000"))

            } else {
                binding.A1.setImageResource(R.drawable.ic_rectangle_21_2)
                statusA1 = true
                total +=1
                belitiket(total)

                val data = Checkout("A1", "70000")
                dataList.add(data)
            }
        }

        binding.A2.setOnClickListener {
            if (statusA2) {
                binding.A2.setImageResource(R.drawable.ic_empty)
                statusA2 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("A2", "70000"))

            } else {
                binding.A2.setImageResource(R.drawable.ic_rectangle_21_2)
                statusA2 = true
                total +=1
                belitiket(total)

                val data = Checkout("A2", "70000")
                dataList.add(data)
            }
        }

        binding.a3.setOnClickListener {
            if (statusA3) {
                binding.a3.setImageResource(R.drawable.ic_empty)
                statusA3 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("A3", "70000"))

            } else {
                binding.a3.setImageResource(R.drawable.ic_rectangle_21_2)
                statusA3 = true
                total +=1
                belitiket(total)

                val data = Checkout("A3", "70000")
                dataList.add(data)
            }
        }

        binding.a4.setOnClickListener {
            if (statusA4) {
                binding.a4.setImageResource(R.drawable.ic_empty)
                statusA4 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("A4", "70000"))

            } else {
                binding.a4.setImageResource(R.drawable.ic_rectangle_21_2)
                statusA4 = true
                total +=1
                belitiket(total)

                val data = Checkout("A4", "70000")
                dataList.add(data)
            }
        }

// PILIH BANGKU SEAT B
        binding.B1.setOnClickListener {
            if (statusB1) {
                binding.B1.setImageResource(R.drawable.ic_empty)
                statusB1 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("B1", "70000"))

            } else {
                binding.B1.setImageResource(R.drawable.ic_rectangle_21_2)
                statusB1 = true
                total +=1
                belitiket(total)

                val data = Checkout("B1", "70000")
                dataList.add(data)
            }
        }

        binding.B2.setOnClickListener {
            if (statusB2) {
                binding.B2.setImageResource(R.drawable.ic_empty)
                statusB2 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("B2", "70000"))

            } else {
                binding.B2.setImageResource(R.drawable.ic_rectangle_21_2)
                statusB2 = true
                total +=1
                belitiket(total)

                val data = Checkout("B2", "70000")
                dataList.add(data)
            }
        }

        binding.B3.setOnClickListener {
            if (statusB3) {
                binding.B3.setImageResource(R.drawable.ic_empty)
                statusB3 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("B3", "70000"))

            } else {
                binding.B3.setImageResource(R.drawable.ic_rectangle_21_2)
                statusB3 = true
                total +=1
                belitiket(total)

                val data = Checkout("B3", "70000")
                dataList.add(data)
            }
        }

        binding.B4.setOnClickListener {
            if (statusB4) {
                binding.B4.setImageResource(R.drawable.ic_empty)
                statusB4 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("B4", "70000"))

            } else {
                binding.B4.setImageResource(R.drawable.ic_rectangle_21_2)
                statusB4 = true
                total +=1
                belitiket(total)

                val data = Checkout("B4", "70000")
                dataList.add(data)
            }
        }

// PILIH BANGKU SEAT C
        binding.C1.setOnClickListener {
            if (statusC1) {
                binding.C1.setImageResource(R.drawable.ic_empty)
                statusC1 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("C1", "70000"))

            } else {
                binding.C1.setImageResource(R.drawable.ic_rectangle_21_2)
                statusC1 = true
                total +=1
                belitiket(total)

                val data = Checkout("C1", "70000")
                dataList.add(data)
            }
        }

        binding.C2.setOnClickListener {
            if (statusC2) {
                binding.C2.setImageResource(R.drawable.ic_empty)
                statusC2 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("C2", "70000"))

            } else {
                binding.C2.setImageResource(R.drawable.ic_rectangle_21_2)
                statusC2 = true
                total +=1
                belitiket(total)

                val data = Checkout("C2", "70000")
                dataList.add(data)
            }
        }

        binding.C3.setOnClickListener {
            if (statusC3) {
                binding.C3.setImageResource(R.drawable.ic_empty)
                statusC3 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("C3", "70000"))

            } else {
                binding.C3.setImageResource(R.drawable.ic_rectangle_21_2)
                statusC3 = true
                total +=1
                belitiket(total)

                val data = Checkout("C3", "70000")
                dataList.add(data)
            }
        }

        binding.C4.setOnClickListener {
            if (statusC4) {
                binding.C4.setImageResource(R.drawable.ic_empty)
                statusC4 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("C4", "70000"))

            } else {
                binding.C4.setImageResource(R.drawable.ic_rectangle_21_2)
                statusC4 = true
                total +=1
                belitiket(total)

                val data = Checkout("C4", "70000")
                dataList.add(data)
            }
        }

// PILIH BANGKU SEAT D
        binding.D1.setOnClickListener {
            if (statusD1) {
                binding.D1.setImageResource(R.drawable.ic_empty)
                statusD1 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("D1", "70000"))

            } else {
                binding.D1.setImageResource(R.drawable.ic_rectangle_21_2)
                statusD1 = true
                total +=1
                belitiket(total)

                val data = Checkout("D1", "70000")
                dataList.add(data)
            }
        }

        binding.D2.setOnClickListener {
            if (statusD2) {
                binding.D2.setImageResource(R.drawable.ic_empty)
                statusD2 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("D2", "70000"))

            } else {
                binding.D2.setImageResource(R.drawable.ic_rectangle_21_2)
                statusD2 = true
                total +=1
                belitiket(total)

                val data = Checkout("D2", "70000")
                dataList.add(data)
            }
        }

        binding.D3.setOnClickListener {
            if (statusD3) {
                binding.D3.setImageResource(R.drawable.ic_empty)
                statusD3 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("D3", "70000"))

            } else {
                binding.D3.setImageResource(R.drawable.ic_rectangle_21_2)
                statusD3 = true
                total +=1
                belitiket(total)

                val data = Checkout("D3", "70000")
                dataList.add(data)
            }
        }

        binding.D4.setOnClickListener {
            if (statusD4) {
                binding.D4.setImageResource(R.drawable.ic_empty)
                statusD4 = false
                total -=1
                belitiket(total)

                // delete data
                dataList.remove(Checkout("D4", "70000"))

            } else {
                binding.D4.setImageResource(R.drawable.ic_rectangle_21_2)
                statusD4 = true
                total +=1
                belitiket(total)

                val data = Checkout("D4", "70000")
                dataList.add(data)
            }
        }

        binding.btnBeli.setOnClickListener {

            val intent = Intent(
                this,
                CheckoutActivity::class.java
            ).putExtra("data", dataList).putExtra("datas", data)
            startActivity(intent)
        }

    }

    private fun belitiket(total:Int) {
        if (total == 0) {
            binding.btnBeli.setText("Beli Tiket")
            binding.btnBeli.visibility = View.INVISIBLE
        } else {
            binding.btnBeli.setText("Beli Tiket ("+total+")")
            binding.btnBeli.visibility = View.VISIBLE
        }

    }
}
