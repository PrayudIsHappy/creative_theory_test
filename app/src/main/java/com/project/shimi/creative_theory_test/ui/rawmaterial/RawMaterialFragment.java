package com.project.shimi.creative_theory_test.ui.rawmaterial;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.shimi.creative_theory_test.R;
import com.project.shimi.creative_theory_test.model.RawMaterialItem;
import com.project.shimi.creative_theory_test.ui.MainActivity;
import com.project.shimi.creative_theory_test.ui.rawmaterial.adapter.RawMaterialAdapter;

import java.util.List;

public class RawMaterialFragment extends Fragment {

    private MainActivity activity;
    private RawMaterialAdapter adapter;
    private RawMaterialPresenter presenter;

    private GridLayoutManager layoutManager;
    private RecyclerView recyclerView;

    public static RawMaterialFragment newInstance() {
        return new RawMaterialFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_raw_material, container, false);
        bindView(view);
        setupInstance();
        setupView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void bindView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
    }

    private void setupInstance() {
    }

    private void setupView() {
        updateToolbar();
        setupRecyclerView();
        callService();
    }

    private void updateToolbar() {
        activity.setupTitleToolbar(getString(R.string.raw_material));
    }

    private void setupRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void callService() {
        try {
            presenter = new RawMaterialPresenter(activity, this);
            presenter.getRawMaterialFromJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAdapter(List<RawMaterialItem> list) {
        List<RawMaterialItem> rawMaterialItemList = list;
        if (!rawMaterialItemList.isEmpty()) {
            adapter = new RawMaterialAdapter(activity, this, rawMaterialItemList);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
    }

}


