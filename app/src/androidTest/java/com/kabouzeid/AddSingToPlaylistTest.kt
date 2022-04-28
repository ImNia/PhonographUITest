package com.kabouzeid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers.*
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AddSingToPlaylistTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addSingTest() {
        onView(TestUtils.withIndex(withId(R.id.menu), 1))
            .perform(click())

        val addPlaylistText = activityTestRule.activity.getString(R.string.action_add_to_playlist)
        onView(anyOf(withText(addPlaylistText), withId(R.id.action_add_to_playlist)))
            .perform(click())

        onView(withText("Test")).perform(click())

        val toastMessageText = activityTestRule.activity.getString(
            R.string.inserted_x_songs_into_playlist_x, 1, "Test"
        )
        onView(withText(toastMessageText))
            .inRoot(withDecorView(not(activityTestRule.activity.getWindow().getDecorView())))
            .check(matches(isDisplayed()))

    }

    companion object {
        @BeforeClass
        fun prepareApp() {
            /*val textSongText = activityTestRule.activity.getString(R.string.songs)
            //Without uppercase()
            onView(withText(textSongText.uppercase())).perform(ViewActions.scrollTo(), click())*/
            InstrumentationRegistry
                .getInstrumentation()
                .uiAutomation
                .executeShellCommand("pm clear com.kabouzeid.gramophone").close()
        }
    }
}