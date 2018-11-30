package agricolapilon.pilonsa;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by BYTE on 27/11/2018.
 */

public class MyFirebaseApp extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
