package com.kabouzeid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import com.kabouzeid.gramophone.ui.activities.MainActivity
import org.hamcrest.Matchers.anyOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeletePlayListTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun deletePlaylist() {
        val textPlaylistText = activityTestRule.activity.getString(R.string.playlists)
        //Without uppercase()
        onView(withText(textPlaylistText.uppercase()))
            .perform(scrollTo(), click())

        onView(TestUtils.withIndex(withId(R.id.menu), 3)).perform(click())
        val deletePlaylistText = activityTestRule.activity.getString(R.string.action_delete)
        onView(anyOf(withText(deletePlaylistText), withId(R.id.action_delete_playlist)))
            .perform(click())

        val agreeWithDeletingText = activityTestRule.activity.getString(R.string.action_delete)
        onView(withText(agreeWithDeletingText)).perform(click())
    }
}