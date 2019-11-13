package santibanez.com;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
public class SignupActivity extends AppCompatActivity {
    EditText etUsername, etPassword, etName;
    String username, password, name;
    int formsuccess;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = new DbHelper(this);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_cancel,
                menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSave:
                formsuccess = 3;
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                name = etName.getText().toString();
                if (username.equals("")) {
                    etUsername.setError("This field is required");
                    formsuccess--;
                }
                if (password.equals("")) {
                    etPassword.setError("This field is required");
                    formsuccess--;
                }
                if (name.equals("")) {
                    etName.setError("This field is required");
                    formsuccess--;
                }
                if (formsuccess == 3) {
                    HashMap<String, String> map_user = new HashMap();
                    map_user.put(db.TBL_USER_USERNAME, username);
                    map_user.put(db.TBL_USER_PASSWORD, password);
                    map_user.put(db.TBL_USER_NAME, name);
                    if (db.addUser(map_user) > 0) {
                        etUsername.setError("Username already taken");
                    } else {
                        Toast.makeText(this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                        this.finish();
                    }
                    break;
                }
            case R.id.btnCancel:
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}