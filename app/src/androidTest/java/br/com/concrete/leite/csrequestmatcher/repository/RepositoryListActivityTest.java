package br.com.concrete.leite.csrequestmatcher.repository;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.concrete.leite.csrequestmatcher.R;
import br.com.concrete.leite.csrequestmatcher.TestApp;
import br.com.concrete.leite.csrequestmatcher.resource.RecyclerViewSizeAssertion;
import br.com.concrete.leite.csrequestmatcher.ui.RepositoryListActivity_;
import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(AndroidJUnit4.class)
public class RepositoryListActivityTest {

    private RepositoryListFixtures repositoryListFixtures;
    private RepositoryListRequests repositoryListRequests;

    @Rule
    public ActivityTestRule<RepositoryListActivity_> activityTestRule =
            new ActivityTestRule<>(RepositoryListActivity_.class, false, false);

    @Rule
    public RequestMatcherRule server = new InstrumentedTestRequestMatcherRule();

    @Before
    public void setUp() throws InterruptedException {
        repositoryListRequests = new RepositoryListRequests(server);
        repositoryListFixtures = new RepositoryListFixtures(server);

        TestApp application = (TestApp) InstrumentationRegistry.getTargetContext().getApplicationContext();
        application.setupInstrumentedTestInterceptor(repositoryListRequests.getRepositoryListInterceptor());
    }

    @Test
    public void itShould_loadRepositories() throws InterruptedException {
        repositoryListFixtures.searchRepositories("1");

        activityTestRule.launchActivity(new Intent());

        onView(ViewMatchers.withId(R.id.rvRepositoryList))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void itShould_loadMoreItemsIfBottomListIsReached(){
        repositoryListFixtures
                .searchRepositories("1")
                .searchRepositories("2");

        activityTestRule.launchActivity(new Intent());

        onView(withId(R.id.rvRepositoryList))
                .perform(RecyclerViewActions.scrollToPosition(29));

        onView(withId(R.id.rvRepositoryList))
                .check(new RecyclerViewSizeAssertion(greaterThan(30)))
                .perform(RecyclerViewActions.scrollToPosition(35));
    }
}
