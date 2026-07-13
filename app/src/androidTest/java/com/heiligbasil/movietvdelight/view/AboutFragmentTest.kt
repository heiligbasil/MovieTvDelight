package com.heiligbasil.movietvdelight.view

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.heiligbasil.movietvdelight.R
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AboutFragmentTest {

    private lateinit var scenario: FragmentScenario<AboutFragment>

    @BeforeEach
    fun setUp() {
        scenario = launchFragmentInContainer<AboutFragment>(themeResId = R.style.Theme_MovieTvDelight)
    }

    @AfterEach
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testAppNameHeadingIsDisplayed() {
        onView(withId(R.id.about_text_heading))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAppNameHeadingHasCorrectText() {
        onView(withId(R.id.about_text_heading))
            .check(matches(withText(R.string.app_name)))
    }

    @Test
    fun testLogoImageIsDisplayed() {
        onView(withId(R.id.about_image_logo))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testTmdbLogoImageIsDisplayed() {
        onView(withId(R.id.about_image_tmdb))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCreditsTextIsDisplayed() {
        onView(withId(R.id.about_text_credits))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCreditsTextHasCorrectContent() {
        onView(withId(R.id.about_text_credits))
            .check(matches(withText(R.string.about_tmdb_credit)))
    }

    @Test
    fun testFragmentViewIsCreated() {
        scenario.onFragment { fragment ->
            assert(fragment.view != null)
        }
    }

    @Test
    fun testParentLayoutExists() {
        onView(withId(R.id.fragment_about_parent_layout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testDemonstrateEspressoOnFragment() {
        onView(withId(R.id.about_text_heading))
            .check(matches(withText(R.string.app_name)))
    }
}
