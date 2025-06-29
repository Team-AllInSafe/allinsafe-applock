// Generated by view binder compiler. Do not edit!
package com.naver.appLock.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.naver.appLock.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class Ac001LoginBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView btnLogin;

  @NonNull
  public final TextView btnRegister;

  @NonNull
  public final EditText etInputId;

  @NonNull
  public final EditText etInputPw;

  @NonNull
  public final TextView tempPass;

  private Ac001LoginBinding(@NonNull LinearLayout rootView, @NonNull TextView btnLogin,
      @NonNull TextView btnRegister, @NonNull EditText etInputId, @NonNull EditText etInputPw,
      @NonNull TextView tempPass) {
    this.rootView = rootView;
    this.btnLogin = btnLogin;
    this.btnRegister = btnRegister;
    this.etInputId = etInputId;
    this.etInputPw = etInputPw;
    this.tempPass = tempPass;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static Ac001LoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static Ac001LoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.ac0_01_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static Ac001LoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_login;
      TextView btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.btn_register;
      TextView btnRegister = ViewBindings.findChildViewById(rootView, id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.et_input_id;
      EditText etInputId = ViewBindings.findChildViewById(rootView, id);
      if (etInputId == null) {
        break missingId;
      }

      id = R.id.et_input_pw;
      EditText etInputPw = ViewBindings.findChildViewById(rootView, id);
      if (etInputPw == null) {
        break missingId;
      }

      id = R.id.temp_pass;
      TextView tempPass = ViewBindings.findChildViewById(rootView, id);
      if (tempPass == null) {
        break missingId;
      }

      return new Ac001LoginBinding((LinearLayout) rootView, btnLogin, btnRegister, etInputId,
          etInputPw, tempPass);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
