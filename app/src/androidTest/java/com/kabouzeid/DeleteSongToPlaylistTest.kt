package com.kabouzeid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DeleteSongToPlaylistTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun deleteSongTest() {
        val textPlaylistText = activityTestRule.activity.getString(R.string.playlists)
        //Without uppercase()
        onView(withText(textPlaylistText.uppercase())).perform(scrollTo(), click())
        onView(withText("Test")).perform(click())
        onView(TestUtils.withIndex(withId(R.id.menu), 1)).perform(click())

        val deletePlaylistText = activityTestRule.activity.getString(R.string.action_remove_from_playlist)
        onView(Matchers.anyOf(withText(deletePlaylistText), withId(R.id.action_remove_from_playlist)))
            .perform(click())


        val agreeWithDeletingText = activityTestRule.activity.getString(R.string.remove_action)
        onView(withText(agreeWithDeletingText)).perform(click())

        onView(withText(R.string.playlist_empty_text)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    companion object {
        @BeforeClass
        fun prepareApp() {
            /*val textSongText = activityTestRule.activity.getString(R.string.songs)
            //Without uppercase()
            onView(withText(textSongText.uppercase())).perform(ViewActions.scrollTo(), click())*/
        }
    }

}