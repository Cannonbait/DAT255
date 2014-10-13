package edu.chalmers.pickuapp.app;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import edu.chalmers.pickuapp.app.events.*;
import edu.chalmers.pickuapp.app.events.EventListener;
import edu.chalmers.pickuapp.app.model.*;
import edu.chalmers.pickuapp.app.server.MockServer;

import java.util.*;

/**
 * Created by Tejp on 2014-10-08.
 */
public class MasterActivity extends Activity implements EventListener {

    private Model model;
    private final Map<Class<? extends Sequence>, Class<? extends ChildActivity>> availableActivities = new HashMap<Class<? extends Sequence>, Class<? extends ChildActivity>>();


    private static ChildActivity currentRunningChild = null;
    private MockServer mockServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error);
        EventBus.INSTANCE.registerListener(this);
        setupAvailableActivities();
        if (savedInstanceState == null) {
            model = new Model();
        }

        //start mockserver
        Log.i("hej","hallå");
        mockServer = new MockServer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.INSTANCE.removeListener(model);
        EventBus.INSTANCE.removeListener(this);
    }

    private void setupAvailableActivities() {
        availableActivities.put(Mode.class, ChooseModeActivity.class);
        availableActivities.put(DriverSetRoute.class, DriverSetRouteActivity.class);
        availableActivities.put(DriverMatchmaker.class, DriverMatchmakerActivity.class);
        availableActivities.put(HitchhikerSetRoute.class, DriverSetRouteActivity.class);
        availableActivities.put(HitchhikerMatchmaker.class, HitchhikerMatchmakerActivity.class);
		availableActivities.put(DriverResponse.class, DriverResponseActivity.class);
        availableActivities.put(DisplayInfo.class, DisplayInfoActivity.class);
    }

    public static void setCurrentRunningChild(ChildActivity childActivity) {
        if (currentRunningChild != null) {
            currentRunningChild.finish();
        }
        currentRunningChild = childActivity;
    }

    @Override
    public void onEvent(Event e) {
        if (e instanceof ChangeViewEvent) {
            ChangeViewEvent event = (ChangeViewEvent) e;
            Intent i = new Intent(this, availableActivities.get(event.sequenceClass));
            startActivity(i);

            Log.i("PickUApp", "Good?");
        } else if (e instanceof ExitAppEvent) {
            finish();
        }
        else {
            currentRunningChild.processEvent(e);
        }
    }
}
