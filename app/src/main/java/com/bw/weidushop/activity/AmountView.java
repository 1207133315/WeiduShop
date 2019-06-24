package com.bw.weidushop.activity;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.weidushop.R;

/**
 * 自定义组件：购买数量，带减少增加按钮
 *
 */
public class AmountView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量


    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(final Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        etAmount.setCursorVisible(false);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        etAmount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setCursorVisible(true);
            }
        });
        etAmount.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                amount=Integer.parseInt(etAmount.getText().toString());
                etAmount.setText(amount + "");
            }
        });
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(etAmount.getText())){
                    etAmount.setText("1");
                    amount=1;
                }else {
                    if (Integer.parseInt(s.toString())<1){
                        Toast.makeText(context, "亲，宝贝不能再少了呦！", Toast.LENGTH_SHORT).show();
                        etAmount.setText("1");
                        amount=1;
                    }else {
                        amount=Integer.parseInt(etAmount.getText().toString());
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);


    }
    public void setcount(int amount){
        this.amount=amount;
        etAmount.setText(amount+"");
    }



    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setText(amount + "");
                onAmountChangeListener.onAmountChange(amount);
            }else {
                Toast.makeText(getContext(), "去你妈的，一件都买不起，你个穷逼！", Toast.LENGTH_SHORT).show();
            }
        } else if (i == R.id.btnIncrease) {
                amount++;
                etAmount.setText(amount + "");
            onAmountChangeListener.onAmountChange(amount);
        }

    }

    public interface OnAmountChangeListener {
        void onAmountChange(int amount);
    }
    private OnAmountChangeListener onAmountChangeListener;
    public void result(OnAmountChangeListener onAmountChangeListener){
        this.onAmountChangeListener=onAmountChangeListener;
    }

}