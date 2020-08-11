package com.xkf.trainingplatform.main.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.xkf.trainingplatform.databinding.FragmentDoctorMessageBinding
import com.xkf.trainingplatform.databinding.ItemChatDoctorBinding
import com.xkf.trainingplatform.databinding.ItemChatUserBinding


class DoctorMessageFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(DoctorMessageViewModel::class.java)
    }

    private lateinit var viewBinding: FragmentDoctorMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDoctorMessageBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mockData()
        viewBinding.recyclerView.adapter = Adapter()
    }

    inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemViewType(position: Int): Int {
            return viewModel.messageList[position].type
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == 0) {
                val itemViewBinding =
                    ItemChatUserBinding.inflate(LayoutInflater.from(context), parent, false)
                UserMessageViewHolder(itemViewBinding)
            } else {
                val itemViewBinding =
                    ItemChatDoctorBinding.inflate(LayoutInflater.from(context), parent, false)
                DoctorMessageViewHolder(itemViewBinding)
            }
        }

        override fun getItemCount(): Int = viewModel.messageList.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is UserMessageViewHolder) {
                holder.itemViewBinding.message = viewModel.messageList[position]
            } else if (holder is DoctorMessageViewHolder) {
                holder.itemViewBinding.message = viewModel.messageList[position]
            }
        }
    }

    inner class UserMessageViewHolder(val itemViewBinding: ItemChatUserBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    inner class DoctorMessageViewHolder(val itemViewBinding: ItemChatDoctorBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)
}