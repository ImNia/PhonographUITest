package com.kabouzeid

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AddSingToPlaylistTest {

    @Test
    fun A_addSingTest() {
        activityTestRule.launchActivity(Intent())

        onView(TestUtils.withIndex(withId(R.id.menu), 1))
            .perform(click())

        val addPlaylistText = activityTestRule.activity.getString(R.string.action_add_to_playlist)
        onView(anyOf(withText(addPlaylistText), withId(R.id.action_add_to_playlist)))
            .perform(click())

        onView(withText(NAME_PLAYLIST)).perform(click())

        val toastMessageText = activityTestRule.activity.getString(
            R.string.inserted_x_songs_into_playlist_x, 1, NAME_PLAYLIST
        )
        onView(withText(toastMessageText))
            .inRoot(withDecorView(not(activityTestRule.activity.getWindow().getDecorView())))
            .check(matches(isDisplayed()))

        activityTestRule.finishActivity()
    }

    companion object {
        const val NAME_PLAYLIST = "Test"

        @ClassRule
        @JvmField
        val activityTestRule
            = ActivityTestRule(MainActivity::class.java, true, true)

        @BeforeClass
        @JvmStatic
        fun beforeTest() {
            val playlistText = activityTestRule.activity.getString(R.string.playlists)
            onView(withText(playlistText.uppercase())).perform(scrollTo(), click())

            openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

            val newPlayListText = activityTestRule.activity.getString(R.string.new_playlist_title)
            onView(withText(newPlayListText)).perform(click())

            onView(withClassName(endsWith("EditText"))).perform(replaceText(NAME_PLAYLIST))

            val agreeWithCreateText = activityTestRule.activity.getString(R.string.create_action)
            onView(withText(agreeWithCreateText)).perform(click())

            val songsText = activityTestRule.activity.getString(R.string.songs)
            onView(withText(songsText.uppercase())).perform(scrollTo(), click())

            activityTestRule.finishActivity()
        }

        /*@AfterClass
        @JvmStatic
        fun afterTest() {
            activityTestRule.launchActivity(Intent())
            val playlistText = activityTestRule.activity.getString(R.string.playlists)
            onView(withText(playlistText.uppercase())).perform(scrollTo(), click())

            onView(allOf(withId(R.id.title), withText(NAME_PLAYLIST))).perform(click())
//            openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

            val deletePlayListText = activityTestRule.activity.getString(R.string.action_delete)
            onView(withText(deletePlayListText)).perform(click())
        }*/
    }
}