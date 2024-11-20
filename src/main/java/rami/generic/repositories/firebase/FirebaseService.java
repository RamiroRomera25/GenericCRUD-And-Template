package rami.generic.repositories.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    private final FirebaseDatabase firebaseDatabase;

    public FirebaseService() {
        this.firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void saveData(String path, Object data) {
        DatabaseReference ref = firebaseDatabase.getReference(path);
        ref.setValueAsync(data);
    }
}
