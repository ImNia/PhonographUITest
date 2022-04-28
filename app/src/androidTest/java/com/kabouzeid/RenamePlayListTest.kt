package com.kabouzeid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RenamePlayListTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun renamePlaylist() {
        val textPlaylistText = activityTestRule.activity.getString(R.string.playlists)
        //Without uppercase()
        onView(withText(textPlaylistText.uppercase()))
            .perform(scrollTo(), click())

        onView(TestUtils.withIndex(withId(R.id.menu), 3))
            .perform(click())
        val renamePlaylistText = activityTestRule.activity.getString(R.string.rename_action)
        onView(anyOf(withText(renamePlaylistText), withId(R.id.action_rename_playlist)))
            .perform(click())

        onView(allOf(withClassName(endsWith("EditText")), withText("Test")))
            .perform(replaceText("RenameTest"))

        val agreeWithRenameText = activityTestRule.activity.getString(R.string.rename_action)
        onView(withText(agreeWithRenameText)).perform(click())

//        onView(anyOf(withId(R.id.title), withText("RenameTest"))).check(matches(isDisplayed()))
//        Thread.sleep(1000)
    }
}