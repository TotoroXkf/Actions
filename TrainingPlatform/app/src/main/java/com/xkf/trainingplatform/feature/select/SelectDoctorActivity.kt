package com.xkf.trainingplatform.feature.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.xkf.trainingplatform.R
import com.xkf.trainingplatform.databinding.ActivitySelectDoctorBinding
import com.xkf.trainingplatform.databinding.ItemSelectDoctorBinding

class SelectDoctorActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySelectDoctorBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(SelectDoctorViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_doctor)
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this

        viewModel.mockData()

        viewBinding.localButton.setOnClickListener {
            val dialog = BottomSelectDialog(this, viewModel.provinceList)
            dialog.onClickListener = {
                viewModel.province.value = it
                viewModel.filterList()
                viewBinding.recyclerView.scrollToPosition(0)
                viewBinding.recyclerView.adapter?.notifyDataSetChanged()
            }
            dialog.show()
        }
        viewBinding.doctorAgeButton.setOnClickListener {
            val dialog = BottomSelectDialog(this, viewModel.doctorAgeList)
            dialog.onClickListener = {
                viewModel.doctorAge.value = it
                viewModel.filterList()
                viewBinding.recyclerView.scrollToPosition(0)
                viewBinding.recyclerView.adapter?.notifyDataSetChanged()
            }
            dialog.show()
        }
        viewBinding.hospitalButton.setOnClickListener {
            val dialog = BottomSelectDialog(this, viewModel.hospitalList)
            dialog.onClickListener = {
                viewModel.hospital.value = it
                viewModel.filterList()
                viewBinding.recyclerView.scrollToPosition(0)
                viewBinding.recyclerView.adapter?.notifyDataSetChanged()
            }
            dialog.show()
        }
        viewBinding.jobTitleButton.setOnClickListener {
            val dialog = BottomSelectDialog(this, viewModel.jobTitleList)
            dialog.onClickListener = {
                viewModel.jobTitle.value = it
                viewModel.filterList()
                viewBinding.recyclerView.scrollToPosition(0)
                viewBinding.recyclerView.adapter?.notifyDataSetChanged()
            }
            dialog.show()
        }

        viewBinding.recyclerView.adapter = ListAdapter()
    }

    inner class ListAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemViewBinding =
                ItemSelectDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(itemViewBinding)
        }

        override fun getItemCount(): Int = viewModel.currentDoctorList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemViewBinding.itemData = viewModel.currentDoctorList[position]
            holder.itemViewBinding.materialButton.setOnClickListener {
                Toast.makeText(this@SelectDoctorActivity, "选择成功", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    inner class ViewHolder(val itemViewBinding: ItemSelectDoctorBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)
}