package com.example.noteapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.noteapp.R
import com.example.noteapp.presentation.fragment.notes.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

typealias  Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(private val inflate: Inflate<VB>) :
    Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected abstract val vm: VM


    private var _controller: NavController? = null
    protected val controller get() = _controller!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        _controller = navHostFragment.navController
        initialize()
        setupRequests()
        listeners()
    }

    protected open fun setupRequests() {}
    protected open fun listeners() {}
    protected open fun initialize() {}

    protected fun <T> StateFlow<UiState<T>>.collectState(
        onLoading: () -> Unit,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                this@collectState.collect {
                    when (it) {
                        is UiState.Empty -> {}
                        is UiState.Error -> {
                            onError(it.msg)
                        }
                        is UiState.Loading -> {
                            onLoading()
                        }
                        is UiState.Success -> {
                            it.data?.let { it1 ->
                                onSuccess(it1)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}