package com.example.pinclassrooom.ui.aulas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pinclassrooom.R;

public class AulasFragment extends Fragment {

    private AulasViewModel aulasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aulasViewModel =
                ViewModelProviders.of(this).get(AulasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_aulas, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        aulasViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}