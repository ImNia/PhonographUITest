package com.kabouzeid

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers.*
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RenamePlayListTest {

    @Test
    fun renamePlaylist() {
        activityTestRule.launchActivity(Intent())

        val playlistText = activityTestRule.activity.getString(R.string.playlists)
        //Without uppercase()
        onView(withText(playlistText.uppercase()))
            .perform(scrollTo(), click())

        onView(
            TestUtils.withIndex(withId(R.id.menu), 3)
        ).perform(click())
        val renamePlaylistText = activityTestRule.activity.getString(R.string.rename_action)
        onView(anyOf(withText(renamePlaylistText), withId(R.id.action_rename_playlist)))
            .perform(click())

        onView(allOf(withClassName(endsWith("EditText")), withText(NAME_PLAYLIST)))
            .perform(replaceText(RENAME_PLAYLIST))

        val agreeWithRenameText = activityTestRule.activity.getString(R.string.rename_action)
        onView(withText(agreeWithRenameText)).perform(click())

        activityTestRule.finishActivity()
    }

    companion object {
        const val NAME_PLAYLIST = "Test"
        const val RENAME_PLAYLIST = "RenameTest"

        @ClassRule
        @JvmField
        val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

/*        @BeforeClass
        @JvmStatic
        fun beforeTest() {
            val playlistText = activityTestRule.activity.getString(R.string.playlists)
            onView(withText(playlistText.uppercase())).perform(scrollTo(), click())

            Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)

            val newPlayListText = activityTestRule.activity.getString(R.string.new_playlist_title)
            onView(withText(newPlayListText)).perform(click())

            onView(withClassName(endsWith("EditText"))).perform(replaceText(NAME_PLAYLIST))

            val agreeWithCreateText = activityTestRule.activity.getString(R.string.create_action)
            onView(withText(agreeWithCreateText)).perform(click())

            val songsText = activityTestRule.activity.getString(R.string.songs)
            onView(withText(songsText.uppercase())).perform(scrollTo(), click())

            activityTestRule.finishActivity()
        }*/
    }
}