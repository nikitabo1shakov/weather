package com.nikitabolshakov.weather.view.contactlist

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nikitabolshakov.weather.R
import com.nikitabolshakov.weather.databinding.FragmentContactListBinding
import com.nikitabolshakov.weather.viewmodel.ContactListViewModel

class ContactListFragment : Fragment() {

    companion object {
        fun newInstance() = ContactListFragment()
    }

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ContactListViewModel by lazy {
        ViewModelProvider(this).get(ContactListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    private fun checkPermission() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    getContacts()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                    AlertDialog.Builder(it)
                        .setTitle(getString(R.string.alter_dialog_title))
                        .setMessage(getString(R.string.alter_dialog_message))
                        .setPositiveButton(getString(R.string.alter_dialog_positive_button)) { _, _ ->
                            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
                        }
                        .setNegativeButton(getString(R.string.alter_dialog_negative_button_1)) { dialog, _ -> dialog.dismiss() }
                        .create()
                        .show()
                }
                else -> requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }

    private fun getContacts() {
        Toast.makeText(requireContext(), getString(R.string.toast_text), Toast.LENGTH_LONG).show()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getContacts()
            } else {
                context?.let {
                    AlertDialog.Builder(it)
                        .setTitle(getString(R.string.alter_dialog_title))
                        .setMessage(getString(R.string.alter_dialog_message))
                        .setNegativeButton(getString(R.string.alter_dialog_negative_button_2)) { dialog, _ -> dialog.dismiss() }
                        .create()
                        .show()
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}