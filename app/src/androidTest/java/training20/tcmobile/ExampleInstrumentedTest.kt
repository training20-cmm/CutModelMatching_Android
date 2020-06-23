package training20.tcmobile

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import training20.tcmobile.fragments.HairdresserSalonUnregisteredFragment

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("training20.tcmobile", appContext.packageName)

    }

}


/*
@RunWith(AndroidJUnit4::class)
class HairdresserSalonUnregistereFragmentdTest{
    @Test
    fun testNavigationToInHairdresserSalonRegistration(){
        val  = mock(NavController::class.java)

        val titleScenario = launchFragmentInContainer<HairdresserSalonUnregisteredFragment>()

        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        onView(ViewMatchers.withId(R.id.hairdresserSalonUnregistered_yes)).perform(ViewActions.click())
        verify(mockNavController).navigate(R.id.action_hairdresserSalonUnregisteredFragment_to_hairdresserSalonRegistrationFragment)
    }
}
*/
