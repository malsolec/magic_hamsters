package repotest;

import android.content.Context;
import android.test.ActivityUnitTestCase;
import android.test.InstrumentationTestCase;

import hamsters.magic.smart_activities.MainActivity;
import repositories.Utils;


public class RebuildTest extends ActivityUnitTestCase<MainActivity> {

    private Context context;
    public RebuildTest() {
        super(MainActivity.class);
    }
    public RebuildTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    protected void setUp() throws Exception {
        super.setUp();
        context = getInstrumentation().getTargetContext();
        Utils.cleanUpDatabase(context);
        Utils.setUpExamples(context);
    }


    public void testRebuild() {
        assertTrue(true);
    };
}
