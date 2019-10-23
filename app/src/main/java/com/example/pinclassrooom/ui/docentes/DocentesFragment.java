package com.example.pinclassrooom.ui.docentes;

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

public class DocentesFragment extends Fragment {

    private DocentesViewModel docentesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        docentesViewModel =
                ViewModelProviders.of(this).get(DocentesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_docentes, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        docentesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}