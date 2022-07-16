package dev.ogabek.android_saa.fragment.auth.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.ogabek.android_saa.R
import dev.ogabek.android_saa.databinding.FragmentSignupBinding

class SignupFragment : Fragment(R.layout.fragment_signup) {
    private val binding by viewBinding(FragmentSignupBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }

    private fun initViews() {
        binding.tvSignin.setOnClickListener {
            requireActivity().onBackPressed()
//            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }

}