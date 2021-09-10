package com.technorapper.technoraploader.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.technorapper.technoraploader.CustomMatchers.Companion.withItemCount
import com.technorapper.technoraploader.R
import junit.framework.TestCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun A_isViewsVisible() {
        Espresso.onView(withId(R.id.fab)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun countPrograms() {
        runBlocking { delay(5000) }
        onView(withId(R.id.items))
            .check(matches(withItemCount(10)))
    }
}