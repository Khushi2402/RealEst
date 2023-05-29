package com.example.project

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    lateinit var tvname: TextView
    lateinit var tvtitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        // Inflate the layout for this fragment

        tvname = view.findViewById(R.id.profileName)
        tvtitle = view.findViewById(R.id.jobTitle)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = activity?.intent?.getStringExtra("name")
        val title = activity?.intent?.getStringExtra("title")

        tvname.text = name
        tvtitle.text = title

        val personal_details_button = view.findViewById<Button>(R.id.personal_details_button)
        personal_details_button.setOnClickListener {
            launchEditProfileActivity()
        }

        val account_settings_button = view.findViewById<Button>(R.id.account_settings_button)
        account_settings_button.setOnClickListener {
            launchAccountSettingsActivity()
        }

        val viewed_properties_button = view.findViewById<Button>(R.id.viewed_properties_button)
        viewed_properties_button.setOnClickListener {
            launchViewedPropertiesActivity()
        }

        val shortlisted_properties_button = view.findViewById<Button>(R.id.shortlisted_properties_button)
        shortlisted_properties_button.setOnClickListener {
            launchShortlistedPropertiesActivity()
        }
    }

    private fun launchShortlistedPropertiesActivity() {
        val intent = Intent(requireContext(), ShortlistedProperties::class.java)
        startActivity(intent)
    }

    private fun launchViewedPropertiesActivity() {
        val intent = Intent(requireContext(), ViewedProperties::class.java)
        startActivity(intent)
    }

    private fun launchAccountSettingsActivity() {
        val intent = Intent(requireContext(), AccountSettings::class.java)
        startActivity(intent)
    }

    private fun launchEditProfileActivity() {
        val intent = Intent(requireContext(), EditProfile::class.java)
        startActivity(intent)
    }

}