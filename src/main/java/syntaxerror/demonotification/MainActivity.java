package syntaxerror.demonotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import syntaxerror.demonotification.notification.GCMClientManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GCMClientManager pushClientManager = new GCMClientManager(this, "601255416351");
        pushClientManager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
            @Override
            public void onSuccess(String registrationId, boolean isNewRegistration) {
              String  deviceID = registrationId;
            }

            @Override
            public void onFailure(String ex) {
                super.onFailure(ex);
            }
        });
    }
}
