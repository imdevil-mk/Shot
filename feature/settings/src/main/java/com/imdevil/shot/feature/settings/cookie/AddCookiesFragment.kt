package com.imdevil.shot.feature.settings.cookie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.imdevil.core.common.extensions.print
import com.imdevil.core.common.log.Dog
import com.imdevil.shot.core.model.data.Cookie
import com.imdevil.shot.feature.common.base.BaseFragment
import com.imdevil.shot.feature.settings.R
import com.imdevil.shot.feature.settings.databinding.FragmentAddCookiesBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class AddCookiesFragment : BaseFragment<FragmentAddCookiesBinding>() {

    private val viewModel: AddCookiesViewModel by viewModels()

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddCookiesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cookies.collect {
                    Dog.d(TAG, "User Cookies:\n ${it.print()}")
                    onUserCookiesChanged(it)
                }
            }
        }
    }

    private fun onUserCookiesChanged(cookies: List<Cookie>) {
        binding.addCookies.isVisible = cookies.isEmpty()
        binding.cookieList.isVisible = cookies.isNotEmpty()

        if (cookies.isEmpty()) {
            binding.addCookies.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(R.string.add_cookies)
                    .setView(R.layout.dialog_add_cookie)
                    .setPositiveButton(
                        R.string.action_save
                    ) { dialog, _ ->
                        val input =
                            (dialog as AlertDialog).findViewById<TextView>(R.id.input)!!.text.toString()
                        saveCookie(input)
                    }
                    .setNegativeButton(
                        R.string.action_cancel
                    ) { _, _ ->
                        //no-op
                    }
                    .show()
            }
        }

        if (cookies.isNotEmpty()) {
            val adapter = CookieListAdapter()
            binding.cookieList.adapter = adapter
            adapter.submitList(cookies)
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun saveCookie(json: String) {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter<List<Cookie>>()
        try {
            val cl = adapter.fromJson(json)
            cl?.let {
                viewModel.setUserCookies(it)
            }
        } catch (e: IOException) {
            Dog.e(TAG, "saveCookie: ", e)
        }
    }

    companion object {
        private const val TAG = "AddCookiesFragment"
    }
}