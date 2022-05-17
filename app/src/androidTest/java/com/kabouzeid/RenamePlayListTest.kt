package com.kabouzeid

import android.os.Build
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers.*
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RenamePlayListTest {

    @Test
    fun renamePlaylist() {
        activityTestRule.launchActivity(null)
        val playlistText = activityTestRule.activity.getString(R.string.playlists)
        onView(withText(equalToIgnoringCase(playlistText))).perform(scrollTo(), click())

        Thread.sleep(100)
        onView(allOf(withId(R.id.title), withText(NAME_PLAYLIST))).perform(click())

        Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

        val renamePlaylistText = activityTestRule.activity.getString(R.string.rename_action)
        onView(anyOf(withText(renamePlaylistText), withId(R.id.action_rename_playlist)))
            .perform(click())

        onView(allOf(withClassName(endsWith("EditText")), withText(NAME_PLAYLIST)))
            .perform(replaceText(RENAME_PLAYLIST))

        val agreeWithRenameText = activityTestRule.activity.getString(R.string.rename_action)
        onView(withText(agreeWithRenameText)).perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())
        Thread.sleep(100)
        onView(allOf(withId(R.id.title), withText(RENAME_PLAYLIST))).check(matches(isDisplayed()))

        activityTestRule.finishActivity()
    }

    companion object {
        const val NAME_PLAYLIST = "Test"
        const val RENAME_PLAYLIST = "RenameTest"

        @ClassRule
        @JvmField
        val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

        @BeforeClass
        @JvmStatic
        fun beforeTest() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getInstrumentation().uiAutomation.executeShellCommand(
                    "pm grant " + getTargetContext().packageName
                            + " android.permission.READ_EXTERNAL_STORAGE"
                )
            }
            activityTestRule.launchActivity(null)
            onView(withId(R.id.mi_button_cta)).perform(click())

            val playlistText = activityTestRule.activity.getString(R.string.playlists)
            onView(withText(equalToIgnoringCase(playlistText))).perform(scrollTo(), click())

            Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

            val newPlayListText = activityTestRule.activity.getString(R.string.new_playlist_title)
            onView(withText(newPlayListText)).perform(click())

            onView(withClassName(endsWith("EditText"))).perform(replaceText(NAME_PLAYLIST))

            val agreeWithCreateText = activityTestRule.activity.getString(R.string.create_action)
            onView(withText(agreeWithCreateText)).perform(click())
//            onView(allOf(withId(R.id.title), withText(NAME_PLAYLIST))).check(matches(isDisplayed()))

            val songsText = activityTestRule.activity.getString(R.string.songs)
            onView(withText(equalToIgnoringCase(songsText))).perform(scrollTo(), click())

            activityTestRule.finishActivity()
        }

        @AfterClass
        @JvmStatic
        fun afterTest() {
            activityTestRule.launchActivity(null)

            val playlistText = activityTestRule.activity.getString(R.string.playlists)
            onView(withText(equalToIgnoringCase(playlistText))).perform(scrollTo(), click())

            Thread.sleep(100)
            onView(allOf(withId(R.id.title), withText(RENAME_PLAYLIST))).perform(click())

            Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

            val deletePlaylistText = activityTestRule.activity.getString(R.string.delete_action)
            onView(anyOf(withText(deletePlaylistText), withId(R.id.action_delete_playlist)))
                .perform(click())

            onView(withId(R.id.md_buttonDefaultPositive)).perform(click())
            onView(withText(RENAME_PLAYLIST)).check(doesNotExist())

//            activityTestRule.finishActivity()
        }
    }
}