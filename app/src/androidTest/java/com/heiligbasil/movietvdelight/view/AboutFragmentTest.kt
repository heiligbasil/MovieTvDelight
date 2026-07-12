package com.heiligbasil.movietvdelight.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.heiligbasil.movietvdelight.R
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AboutFragmentTest {

    val aboutFragment: AboutFragment = AboutFragment()

    @Test
    fun testDemonstrateEspressoOnFragment() {
        onView(withId(R.id.about_text_heading)).check(matches(withText(R.string.app_name)))
    }

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun onCreate() {
    }

    @Test
    fun onCreateView() {
    }

    @Test
    fun onViewCreated() {
    }
}
