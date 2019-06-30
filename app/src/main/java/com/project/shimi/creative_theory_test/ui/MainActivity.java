package com.project.shimi.creative_theory_test.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.project.shimi.creative_theory_test.R;
import com.project.shimi.creative_theory_test.model.RawMaterialItem;
import com.project.shimi.creative_theory_test.ui.cart.CartFragment;
import com.project.shimi.creative_theory_test.ui.rawmaterial.RawMaterialFragment;

public class MainActivity extends AppCompatActivity {

    private RawMaterialItem rawMaterialItem;

    private Fragment fragment;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        setupInstance();
        setupView();
        if (savedInstanceState == null) {
            changePage(0);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void bindView() {
        toolbar = findViewById(R.id.toolbar);
    }

    private void setupInstance() {
        rawMaterialItem = new RawMaterialItem();
    }

    private void setupView() {
        setupToolbar();
    }

    private void setupToolbar() {
        toolbar.setTitle(getString(R.string.blank));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.TextViewTitle);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorBlack));
    }

    public void changePage(int page) {
        switch (page) {
            case 0:
                fragment = new RawMaterialFragment();
                break;
            case 1:
                fragment = new CartFragment();
                break;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_main, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commitAllowingStateLoss();
    }

    public void setupTitleToolbar(String title) {
        toolbar.setTitle(title);
    }

    public RawMaterialItem getRawMaterialItem() {
        return rawMaterialItem;
    }

    public void setRawMaterialItem(RawMaterialItem rawMaterialItem) {
        this.rawMaterialItem = rawMaterialItem;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

}
