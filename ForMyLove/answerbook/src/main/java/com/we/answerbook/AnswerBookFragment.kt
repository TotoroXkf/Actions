package com.we.answerbook

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.we.answerbook.databinding.AnswerBookFragmentBinding
import com.we.common.view.BaseFragment


class AnswerBookFragment : BaseFragment() {
    companion object {
        const val NAME = "answerBookFragment"
    }

    private lateinit var viewModel: AnswerBookViewModel
    private lateinit var viewBinding: AnswerBookFragmentBinding

    private val outAnimatorSet = AnimatorSet()
    private val inAnimatorSet = AnimatorSet()

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AnswerBookViewModel::class.java)
    }

    override fun createView(): View {
        viewBinding = AnswerBookFragmentBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this
        return viewBinding.root
    }

    override fun setupViews() {
        prepareAnimation()

        val editTextView = viewBinding.textInputLayoutView.editText
        editTextView?.let { view ->
            view.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onFinishInput()
                }
                false
            }
        }

        viewBinding.buttonView.setOnClickListener {
            onFinishInput()
        }
    }

    private fun prepareAnimation() {
        // 卡片翻转动画
        val duration = 1000L

        outAnimatorSet.apply {
            val outObjectAnimator1 = ObjectAnimator()
            outObjectAnimator1.apply {
                this.duration = duration
                setPropertyName("rotationY")
                setFloatValues(0f, 180f)
            }

            val outObjectAnimator2 = ObjectAnimator()
            outObjectAnimator2.apply {
                this.duration = duration
                setPropertyName("alpha")
                setFloatValues(1f, 0f)
            }

            playTogether(outObjectAnimator1, outObjectAnimator2)
        }

        inAnimatorSet.apply {
            val inObjectAnimator2 = ObjectAnimator()
            inObjectAnimator2.apply {
                this.duration = duration
                setPropertyName("rotationY")
                setFloatValues(-180f, 0f)
            }

            val inObjectAnimator3 = ObjectAnimator()
            inObjectAnimator3.apply {
                this.duration = duration
                setPropertyName("alpha")
                setFloatValues(0f, 1f)
            }

            playTogether(inObjectAnimator2, inObjectAnimator3)
        }

        val distance = 16000
        val scale = resources.displayMetrics.density * distance
        viewBinding.frontCardView.cameraDistance = scale
        viewBinding.backCardView.cameraDistance = scale

        // 卡片入场动画

    }

    private fun onFinishInput() {
        val text = viewBinding.textInputLayoutView.editText?.toString() ?: ""
        if (text.isEmpty()) {
            return
        }

        viewBinding.frontCardView.setOnClickListener {
            flipCardView()
        }
    }

    private fun flipCardView() {
        if (!viewModel.cardIsShowBack) {
            outAnimatorSet.setTarget(viewBinding.frontCardView)
            inAnimatorSet.setTarget(viewBinding.backCardView)
            outAnimatorSet.start()
            inAnimatorSet.start()
            viewModel.cardIsShowBack = true
        } else {
            outAnimatorSet.setTarget(viewBinding.backCardView)
            inAnimatorSet.setTarget(viewBinding.frontCardView)
            outAnimatorSet.start()
            inAnimatorSet.start()
            viewModel.cardIsShowBack = false
        }
    }
}
