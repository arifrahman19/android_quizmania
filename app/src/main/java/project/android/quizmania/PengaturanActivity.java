package project.android.quizmania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import project.android.quizmania.Fragment.PengaturanFragment;

public class PengaturanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPengaturan);
        setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction().replace(R.id.konten_frame, new PengaturanFragment()).commit();
    }
}