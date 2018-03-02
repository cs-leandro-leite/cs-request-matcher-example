package br.com.concrete.leite.csrequestmatcher.resource;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

/**
 * Created by leandro on 27/12/2017.
 */

public class ViewActionUtils {
    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;

                do
                    uiController.loopMainThreadForAtLeast(50);
                while (System.currentTimeMillis() < endTime);
            }
        };
    }
}
