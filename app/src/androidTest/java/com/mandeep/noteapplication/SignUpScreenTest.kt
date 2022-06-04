package com.mandeep.noteapplication

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpScreenTest {

    @get:Rule
    val activityTestRule  = ActivityTestRule(SignUpScreen::class.java)

    /*Test case - Email validation:
    regex-based email validation,
    */
    @Test
    fun emailValidate() {
        val signUpScreen=activityTestRule.activity
       val bool = signUpScreen.emailValidate("mandeepsinghsaggu2@gmail.com")
        Log.d("4gh4ng",bool.toString())
        assertEquals(true,bool)
    }

    @Test
    fun if_password_matching_with_requirements_return_true() {
        val signUpScreen=activityTestRule.activity
        val bool = signUpScreen.passwordValidate("mandeEP22@","mandeep")
        Log.d("4gh4ng",bool.toString())
        assertEquals(true,bool)
    }

    @Test
    fun if_password_match_with_name_return_false() {
        val signUpScreen=activityTestRule.activity
        val bool = signUpScreen.passwordValidate("mandeep","mandeep")
        Log.d("4gh4ng",bool.toString())
        assertEquals(false,bool)
    }

    @Test
    fun if_password_first_letter_is_not_lowercase_return_false() {
        val signUpScreen=activityTestRule.activity
        val bool = signUpScreen.passwordValidate("MandeEP22@","mandeep")
        Log.d("4gh4ng",bool.toString())
        assertEquals(false,bool)
    }
    @Test
    fun if_password_not_contains_two_uppercase_letters_return_false() {
        val signUpScreen=activityTestRule.activity
        val bool = signUpScreen.passwordValidate("mandeE22@","mandeep")
        Log.d("4gh4ng",bool.toString())
        assertEquals(false,bool)
    }
    @Test
    fun if_password_not_contains_2_digits_return_false() {
        val signUpScreen=activityTestRule.activity
        val bool = signUpScreen.passwordValidate("mandeEP2@","mandeep")
        Log.d("4gh4ng",bool.toString())
        assertEquals(false,bool)
    }

    @Test
    fun if_password_not_contains_1_special_character_return_false() {
        val signUpScreen=activityTestRule.activity
        val bool = signUpScreen.passwordValidate("mandeEP22","mandeep")
        Log.d("4gh4ng",bool.toString())
        assertEquals(false,bool)
    }

    @Test
    fun if_Phonelength_equals_to_10_return_true() {
        val signUpScreen=activityTestRule.activity
        val bool = signUpScreen.phoneValidate("9650226920")
        Log.d("4gh4ng",bool.toString())
        assertEquals(true,bool)
    }

    @Test
    fun if_Phonelength_not_equals_to_10_return_false() {
        val signUpScreen=activityTestRule.activity
        val bool = signUpScreen.phoneValidate("965020")
        Log.d("4gh4ng",bool.toString())
        assertEquals(false,bool)
    }
}