package com.girlathome.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.girlathome.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by steve on 4/17/17.
 */
public class LoginOrSignUpFragment extends Fragment {
    private static final String TAG = LoginOrSignUpFragment.class.getSimpleName();
    Activity parentActivity;
   /* @BindView(R.id.close_frag)
    ImageView closeFrag;*/

    public LoginOrSignUpFragment() {
    }


    public static LoginOrSignUpFragment newInstance() {
        LoginOrSignUpFragment fragment = new LoginOrSignUpFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: hit");
        super.onCreate(savedInstanceState);
    }

    /**
     * Change the null parameter in {@code inflater.inflate()}
     * to a layout resource {@code R.layout.example}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: hit");
        View v = inflater.inflate(R.layout.login_or_sign_up_fragment, container, false);
        ButterKnife.bind(this, v);

        views();
        return v;
    }

    private void views() {

    }

    @OnClick(R.id.close_frag)
    void closeActivity() {
        parentActivity.finish();
    }

    @OnClick(R.id.login)
    public void login() {
        ((LoginOrSignUp) parentActivity).createFragments(new LoginFragment());
    }

    @OnClick(R.id.register)
    public void register() {
        ((LoginOrSignUp) parentActivity).createFragments(new RegisterFragment());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: hit");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume: hit");
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    parentActivity.finish();

                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause: hit");
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: hit");
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: hit");
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parentActivity = activity;
    }
} 