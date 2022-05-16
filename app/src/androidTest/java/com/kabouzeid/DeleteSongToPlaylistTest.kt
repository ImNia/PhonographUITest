package com.kabouzeid

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DeleteSongToPlaylistTest {

    @Ignore("Not ready yet")
    @Test
    fun deleteSongTest() {
        activityTestRule.launchActivity(Intent())
        val textPlaylistText = activityTestRule.activity.getString(R.string.playlists)
        onView(withText(textPlaylistText.uppercase())).perform(scrollTo(), click())
        onView(withText(NAME_PLAYLIST)).perform(click())
        onView(TestUtils.withIndex(withId(R.id.menu), 1)).perform(click())

        val deletePlaylistText = activityTestRule.activity.getString(R.string.action_remove_from_playlist)
        onView(Matchers.anyOf(withText(deletePlaylistText), withId(R.id.action_remove_from_playlist)))
            .perform(click())


        val agreeWithDeletingText = activityTestRule.activity.getString(R.string.remove_action)
        onView(withText(agreeWithDeletingText)).perform(click())

//        onView(withText(R.string.playlist_empty_text)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    companion object {
        const val NAME_PLAYLIST = "Test"

        @ClassRule
        @JvmField
        val activityTestRule = ActivityTestRule(MainActivity::class.java)
/*
        @BeforeClass
        @JvmStatic
        fun beforeTest() {
            val playlistText = activityTestRule.activity.getString(R.string.playlists)
            onView(withText(playlistText.uppercase())).perform(scrollTo(), click())

            Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

            val newPlayListText = activityTestRule.activity.getString(R.string.new_playlist_title)
            onView(withText(newPlayListText)).perform(click())

            onView(withClassName(Matchers.endsWith("EditText"))).perform(
                ViewActions.replaceText(NAME_PLAYLIST)
            )

            val agreeWithCreateText = activityTestRule.activity.getString(R.string.create_action)
            onView(withText(agreeWithCreateText)).perform(click())

            val songsText = activityTestRule.activity.getString(R.string.songs)
            onView(withText(songsText.uppercase())).perform(scrollTo(), click())

            activityTestRule.finishActivity()
        }

        @BeforeClass
        @JvmStatic
        fun addSongBeforeTest() {
            activityTestRule.launchActivity(Intent())
            onView(TestUtils.withIndex(withId(R.id.menu), 1)).perform(click())

            val addPlaylistText = activityTestRule.activity.getString(R.string.action_add_to_playlist)
            onView(Matchers.anyOf(withText(addPlaylistText), withId(R.id.action_add_to_playlist)))
                .perform(click())

            onView(withText(AddSingToPlaylistTest.NAME_PLAYLIST)).perform(click())

            activityTestRule.finishActivity()
        }*/
    }

}