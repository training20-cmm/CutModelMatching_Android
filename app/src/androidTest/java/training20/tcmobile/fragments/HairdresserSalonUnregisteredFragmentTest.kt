package training20.tcmobile.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import training20.tcmobile.R
import training20.tcmobile.activities.HairdresserMainActivity
import training20.tcmobile.activities.HairdresserRegistrationFormActivity
import training20.tcmobile.fragments.HairdresserSalonUnregisteredFragment


    @RunWith(AndroidJUnit4::class)
    class HairdresserSalonUnregisteredFragmentTest{

        @get:Rule
        var activityRule: ActivityTestRule<HairdresserMainActivity> = ActivityTestRule(HairdresserMainActivity::class.java)

        @Before
        fun setup(){
        }

        @After
        fun tearDown() {
        }

        @Test  //本文
        fun hairdresserSalonUnregisteredFragmentTest(){
            val view = onView(withId(R.id.hairdresserSalonUnregistered))
            view.check(matches(withText("このサロンは \nまだ詳細登録 \nされていません")))
        }

        @Test  //登録するか
        fun hairdresserSalonUnregisteredFragment_tourokuTest(){
            val view = onView(withId(R.id.hairdresserSalonUnregistered_touroku))
            view.check(matches(withText("詳細登録しますか？")))
        }

        @Test  //はいボタン
        fun hairdresserSalonUnregisteredFragment_yesTest() {
            val view = onView(withId(R.id.hairdresserSalonUnregistered_yes))
            view.check(matches(withText("はい")))
            view.perform(click())
        }

        @Test  //いいえボタン
        fun hairdresserSalonUnregisteredFragment_noTest(){
            val view = onView(withId(R.id.hairdresserSalonUnregistered_no))
            view.check(matches(withText("いいえ")))
            view.perform(click())
        }


    }