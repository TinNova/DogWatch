package tin.novakovic.dogwatch

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tin.novakovic.dogwatch.ui.main.MainActivity

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun dataIsDisplayedOnRecyclerView() {
        assertDisplayed(R.id.rv_main_activity)
    }

    @Test
    fun clickOnMainRecylerViewItem_ShowDetailRecyclerView() {
        assertDisplayed(R.id.rv_main_activity)
        clickListItem(R.id.rv_main_activity, 0)
        assertDisplayed(R.id.rv_detail_activity)

    }

}
