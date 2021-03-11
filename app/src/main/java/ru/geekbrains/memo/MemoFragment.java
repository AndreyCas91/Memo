package ru.geekbrains.memo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MemoFragment extends Fragment implements MemoAdapterCallback {
    public static final ArrayList<SimpleNote> SNote = new ArrayList<>(Arrays.asList(
            new SimpleNote("Встреча (парк)", "В парке .....", "12.03"),
            new SimpleNote("Встреча (кино)", "В кино .......", "17.03"),
            new SimpleNote("Ужин", "В ресторане .....", "04.04"),
            new SimpleNote("Встреча (аэропорт)", "В аэропорту .......", "10.04")
    ));

    private final MemoAdapter adapter = new MemoAdapter(this);

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
        adapter.setItems(SNote);
    }

    private void initList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_memo);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void startNoteFragment(String messeg) {
        NoteFragment noteFragment = NoteFragment.newIstance(messeg);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.memo, noteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onItemClicked(int position) {
        startNoteFragment(SNote.get(position).getDesk());
    }
}
