package ru.geekbrains.memo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class MemoFragment extends Fragment {
    public static final ArrayList<SimpleNote> SNote = new ArrayList<>(Arrays.asList(
            new SimpleNote("Встреча (парк)", "В парке .....", "12.03"),
            new SimpleNote("Встреча (кино)", "В кино .......", "17.03"),
            new SimpleNote("Ужин", "В ресторане .....", "04.04"),
            new SimpleNote("Встреча (аэропорт)", "В аэропорту .......", "10.04")
    ));

    private boolean orientation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        orientation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void initList(View view) {
        LinearLayout linearLayout = (LinearLayout) view;

        for (int i = 0; i < SNote.size(); i++) {
            String str = SNote.get(i).getTitle() + ".     " + SNote.get(i).getData();
            TextView tv = new TextView(linearLayout.getContext());
            tv.setText(str);
            tv.setTextSize(20f);
            int index = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chekOrintation(SNote.get(index).getDesk());
                }
            });
            linearLayout.addView(tv);
        }
    }

    private void chekOrintation(String messeg) {
        if (orientation) {
            openNoteFragment(messeg);
        } else {
            startNoteFragment(messeg);
        }
    }

    private void openNoteFragment(String messeg) {
        NoteFragment noteFragment = NoteFragment.newIstance(messeg);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container, noteFragment)
                .commit();
    }

    private void startNoteFragment(String messeg) {
        NoteFragment noteFragment = NoteFragment.newIstance(messeg);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.memo, noteFragment)
                .addToBackStack(null)
                .commit();
    }
}
