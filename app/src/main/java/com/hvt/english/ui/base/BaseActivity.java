package com.hvt.english.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hvt.english.util.DialogUtils;
import com.hvt.english.util.LocaleHelper;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseActivity extends AppCompatActivity implements BaseView, TextToSpeech.OnInitListener {

    private Unbinder mUnBinder;
    private SweetAlertDialog dialogLoading;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mUnBinder = ButterKnife.bind(this);
        textToSpeech = new TextToSpeech(this, this);
        initView();
        initData();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        attachView();
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    public abstract void initView();

    public abstract void initData();

    public abstract int getLayoutID();


    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        detachView();
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                initTextToSpeech(false);
                showToast("Your device isn't supported play audio");
            } else {
                initTextToSpeech(true);
            }

        } else {
            initTextToSpeech(false);
            showToast("Your device isn't supported play audio");
        }
    }

    public void speakOut(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    protected void initTextToSpeech(boolean success) {

    }

    @Override
    public void showLoading() {
        if (dialogLoading == null) {
            dialogLoading = DialogUtils.showLoadingDialog(this);
        }
        dialogLoading.show();
    }

    @Override
    public void hideLoading() {
        if (dialogLoading != null) {
            dialogLoading.dismissWithAnimation();
        }
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.show();
    }

    @Override
    public void showError(int message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
