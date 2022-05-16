package com.kabouzeid

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers
import org.hamcrest.Matchers.anyOf
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeletePlayListTest {
    @Ignore("Not ready yet")
    @Test
    fun deletePlaylist() {
        activityTestRule.launchActivity(Intent())
        val textPlaylistText = activityTestRule.activity.getString(R.string.playlists)
        onView(withText(textPlaylistText.uppercase()))
            .perform(scrollTo(), click())

        onView(TestUtils.withIndex(withId(R.id.menu), 3)).perform(click())
        val deletePlaylistText = activityTestRule.activity.getString(R.string.action_delete)
        onView(anyOf(withText(deletePlaylistText), withId(R.id.action_delete_playlist)))
            .perform(click())

        val agreeWithDeletingText = activityTestRule.activity.getString(R.string.action_delete)
        onView(withText(agreeWithDeletingText)).perform(click())

        activityTestRule.finishActivity()
    }

    companion object {
        const val NAME_PLAYLIST = "Test"

        @ClassRule
        @JvmField
        val activityTestRule
                = ActivityTestRule(MainActivity::class.java, true, true)

/*        @BeforeClass
        @JvmStatic
        fun beforeTest() {
            val playlistText = activityTestRule.activity.getString(R.string.playlists)
            onView(withText(playlistText.uppercase())).perform(scrollTo(), click())

            Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)

            val newPlayListText = activityTestRule.activity.getString(R.string.new_playlist_title)
            onView(withText(newPlayListText)).perform(click())

            onView(ViewMatchers.withClassName(Matchers.endsWith("EditText"))).perform(
                ViewActions.replaceText(
                    NAME_PLAYLIST
                )
            )

            val agreeWithCreateText = activityTestRule.activity.getString(R.string.create_action)
            onView(withText(agreeWithCreateText)).perform(click())

            val songsText = activityTestRule.activity.getString(R.string.songs)
            onView(withText(songsText.uppercase())).perform(scrollTo(), click())

            activityTestRule.finishActivity()
        }*/
    }
}