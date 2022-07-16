package dev.ogabek.android_saa.fragment.main

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.ogabek.android_saa.R
import dev.ogabek.android_saa.databinding.FlowFragmentMainBinding
import dev.ogabek.android_saa.fragment.BaseFlowFragment

class MainFlowFragment : BaseFlowFragment(
    R.layout.flow_fragment_main, R.id.nav_host_fragment_main
) {

    private val binding by viewBinding(FlowFragmentMainBinding::bind)

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}