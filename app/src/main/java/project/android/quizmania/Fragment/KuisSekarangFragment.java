package project.android.quizmania.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import project.android.quizmania.R;
import project.android.quizmania.SoalActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class KuisSekarangFragment extends Fragment {
    private TextView tingkat;
    private Button mat,ipa,indo,ing;
    int tingkatSekolah;

    public KuisSekarangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kuis_sekarang, container, false);

        tingkat=(TextView) rootView.findViewById(R.id.textViewTingkat);
        mat=(Button)rootView.findViewById(R.id.buttonMat);
        ipa=(Button)rootView.findViewById(R.id.buttonIpa);
        indo=(Button)rootView.findViewById(R.id.buttonIndo);
        ing=(Button)rootView.findViewById(R.id.buttonIng);

        Spinner spinnerTingkatPendidikan = (Spinner) rootView.findViewById(R.id.spinner_tingkat_pendidikan);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_tingkat_pendidikan, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerTingkatPendidikan.setAdapter(adapter);

        spinnerTingkatPendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String item = arg0.getItemAtPosition(arg2).toString();
                if(item.equals("smp kelas 1"))
                    tingkatSekolah=7;
                else if(item.equals("smp kelas 2"))
                    tingkatSekolah=8;
                else if(item.equals("smp kelas 3"))
                    tingkatSekolah=9;

                tingkat.setText(String.valueOf(tingkatSekolah));

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SoalActivity.class);
                intent.putExtra("mapel","mat");
                intent.putExtra("tingkatSekolah",tingkatSekolah);
                startActivity(intent);
            }
        });
        ipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SoalActivity.class);
                intent.putExtra("mapel","ipa");
                intent.putExtra("tingkatSekolah",tingkatSekolah);
                startActivity(intent);
            }
        });
        indo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SoalActivity.class);
                intent.putExtra("mapel","indo");
                intent.putExtra("tingkatSekolah",tingkatSekolah);
                startActivity(intent);
            }
        });
        ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SoalActivity.class);
                intent.putExtra("mapel","ing");
                intent.putExtra("tingkatSekolah",tingkatSekolah);
                startActivity(intent);
            }
        });

        return rootView;

    }
}
