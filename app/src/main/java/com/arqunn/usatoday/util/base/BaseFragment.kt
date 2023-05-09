package com.arqunn.usatoday.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T: ViewDataBinding, VM: ViewModel> : Fragment() {

    protected lateinit var binding: T
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[targetViewModel()]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        onViewReady()
        return binding.root
    }

    abstract fun getLayoutResource(): Int

    abstract fun targetViewModel(): Class<VM>

    abstract fun onViewReady()

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}