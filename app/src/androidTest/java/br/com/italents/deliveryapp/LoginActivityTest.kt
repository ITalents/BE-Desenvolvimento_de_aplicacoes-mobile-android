package br.com.italents.deliveryapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.italents.deliveryapp.ui.LoginActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class LoginActivityTest {
    // Uma regra que sabe como executar os testes de uma activity
    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @org.junit.Test
    fun verificandoDisponibilidadeDaTelaDeLogin() {
        onView(withId(R.id.nameDelivery)).check(matches(isDisplayed()))
        onView(withId(R.id.imageDelivery)).check(matches(isDisplayed()))
        onView(withId(R.id.edtUsername)).check(matches(isDisplayed()))
        onView(withId(R.id.edtPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.btnRegister)).check(matches(isDisplayed()))

        onView(withId(R.id.loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.error)).check(matches(not(isDisplayed())))
    }

    @Test
    fun cliqueNoBotaoDeLogin() {
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.btnLogin)).perform(click())
        sleep(500)

        onView(withId(R.id.loading)).check(matches(isDisplayed()))
    }

    @Test
    fun cliqueCadastrese() {
        onView(withId(R.id.btnRegister)).perform(click())
        onView(withId(R.id.txvEmail)).check(matches(isDisplayed()))

        onView(withId(R.id.btnRegister)).perform(scrollTo())

        onView(withId(R.id.btnRegister)).check(matches(isDisplayed()))
    }

}