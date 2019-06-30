package com.project.shimi.creative_theory_test.ui.cart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.kalert.KAlertDialog;
import com.project.shimi.creative_theory_test.R;
import com.project.shimi.creative_theory_test.model.RawMaterialItem;
import com.project.shimi.creative_theory_test.ui.MainActivity;

public class CartFragment extends Fragment {

    private MainActivity activity;

    private RawMaterialItem rawMaterialItem;

    private Button btnConfirm;
    private EditText etAmount;
    private ImageView ivPicture;
    private TextView tvName;
    private TextView tvTotalPrice;


    public static CartFragment newInstance() {
        return new CartFragment();
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
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
        btnConfirm = view.findViewById(R.id.btn_confirm);
        etAmount = view.findViewById(R.id.et_amount);
        ivPicture = view.findViewById(R.id.iv_picture);
        tvName = view.findViewById(R.id.tv_name);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
    }

    private void setupInstance() {
        rawMaterialItem = activity.getRawMaterialItem();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog();
            }
        });
        Glide.with(this).load(getMipmapFromRawMaterialPicture(rawMaterialItem)).into(ivPicture);
        tvName.setText(rawMaterialItem.getName());
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    tvTotalPrice.setText(String.valueOf(getTotalPriceByCondition(s)));
                } else {
                    tvTotalPrice.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private int getMipmapFromRawMaterialPicture(RawMaterialItem item) {
        String picture = item.getPicture();
        return activity.getResources().getIdentifier(picture, "mipmap", activity.getPackageName());
    }

    private void setupView() {
        updateToolbar();
    }

    private void updateToolbar() {
        activity.setupTitleToolbar(getString(R.string.cart));
    }

    private int getTotalPriceByCondition(CharSequence s) {
        int totalPrice = 0;
        int amount = Integer.parseInt(s.toString());
        if (amount >= 0 && amount <= 100) {
            totalPrice = getConditionBetween0To100(amount);
        } else if (amount >= 101 && amount < 201) {
            totalPrice = getConditionBetween101To200(amount, true);
        } else if (amount >= 201 && amount < 301) {
            totalPrice = getConditionBetween201To300(amount, true);
        } else if (amount >= 301 && amount < 401) {
            totalPrice = getConditionBetween301To400(amount, true);
        } else if (amount >= 401) {
            totalPrice = getConditionOver400(amount, true);
        }
        return totalPrice;
    }

    private int getConditionBetween0To100(int amount) {
        return amount * 2000;
    }

    private int getConditionBetween101To200(int amount, boolean defaultPrice) {
        int differentAmount = amount - 100;
        int priceFromCondition = getConditionBetween0To100(100);
        return defaultPrice ? (differentAmount * 2100) + priceFromCondition : 100 * 2100;
    }

    private int getConditionBetween201To300(int amount, boolean defaultPrice) {
        int differentAmount = amount - 200;
        int priceFromCondition = getConditionBetween0To100(100)
                + getConditionBetween101To200(200, false);
        return defaultPrice ? (differentAmount * 2250) + priceFromCondition : 100 * 2250;
    }

    private int getConditionBetween301To400(int amount, boolean defaultPrice) {
        int differentAmount = amount - 300;
        int priceFromCondition = getConditionBetween0To100(100)
                + getConditionBetween101To200(200, false)
                + getConditionBetween201To300(300, false);
        return defaultPrice ? (differentAmount * 2450) + priceFromCondition : 100 * 2450;
    }

    private int getConditionOver400(int amount, boolean defaultPrice) {
        int differentAmount = amount - 400;
        int priceFromCondition = getConditionBetween0To100(100)
                + getConditionBetween101To200(200, false)
                + getConditionBetween201To300(300, false)
                + getConditionBetween301To400(400, false);
        return defaultPrice ? (differentAmount * 2700) + priceFromCondition : 100 * 2700;
    }

    private int getTotalPriceByConditionReserve(CharSequence s) {
        int totalPrice = 0;
        int amount = Integer.parseInt(s.toString());
        if (amount >= 0 && amount <= 100) {
            totalPrice = amount * 2000;
        } else if (amount >= 101 && amount < 201) {
            totalPrice = amount * 2100;
        } else if (amount >= 201 && amount < 301) {
            totalPrice = amount * 2250;
        } else if (amount >= 301 && amount < 401) {
            totalPrice = amount * 2450;
        } else if (amount >= 401) {
            totalPrice = amount * 2700;
        }
        return totalPrice;
    }

    private void displayDialog() {
        new KAlertDialog(getContext(), KAlertDialog.SUCCESS_TYPE)
                .setTitleText("Confirm")
//                .setContentText(String.format("%s %s", getString(R.string.name_colon), rawMaterialItem.getName()))
//                .setContentText(String.format("%s %s %s", getString(R.string.amount), etAmount.getText().toString(), getString(R.string.ton)))
                .setContentText(String.format("%s %s %s",  getString(R.string.total_price), tvTotalPrice.getText().toString(), getString(R.string.baht)))
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog dialog) {
                        dialog.dismissWithAnimation();
                        activity.changePage(0);
                    }
                }).show();
    }

}


