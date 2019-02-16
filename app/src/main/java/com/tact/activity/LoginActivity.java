package com.tact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.tact.helpers.InputValidation;
import com.tact.kumbhca.R;
import com.tact.model.SignInOneModel;
import com.tact.model.SignInSendModel;
import com.tact.network.ApiClient;
import com.tact.network.ApiClientInterface;
import com.tact.utils.Constant;
import com.tact.utils.SharedPreference;
import com.tact.utils.Support;
import com.trend.progress.ProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private final AppCompatActivity activity = LoginActivity.this;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private InputValidation inputValidation;
    ProgressDialog progressBar;
    private SharedPreference sp;
    private EditText etUserName;
    private EditText etPassword;
    ApiClientInterface apiClientInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = new SharedPreference(this);
        apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        progressBar = new ProgressDialog(this);
        progressBar.setBarColor(getResources().getColor(R.color.app_color));

        initViews();
    }

    private void initViews()
    {
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInput_et_userid);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        inputValidation = new InputValidation(activity);
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.btn_login);

        appCompatButtonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_login:

                validateCALogin();

                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void validateCALogin()
    {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email)))
        {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password)))
        {
            return;
        }

        hitSignInService(textInputEditTextEmail.getText().toString() , textInputEditTextPassword.getText().toString());
    }


    private void hitSignInService(final String userName, String password)
    {
        progressBar.show();

        SignInSendModel sism = new SignInSendModel();
        sism.setUserName(userName);
        sism.setPassword(password);

        apiClientInterface.signIn(sism).enqueue(new Callback<SignInOneModel>()
        {
            @Override
            public void onResponse(Call<SignInOneModel> call, Response<SignInOneModel> response)
            {
                progressBar.dismiss();


                if (response.isSuccessful())
                {
                    if (response.body().getStatusCode() == 200)
                    {
                        Gson gson = new Gson();
                        String favData = gson.toJson(response.body().getData());

                        sp.setValueInt(Constant.KEY_USER_ID, response.body().getData().getUserData().getId());
                        sp.setValueString("LOGIN_DATA" , favData);
                        sp.setValueBool(Constant.KEY_ISLOGIN, true);

                        SignInOneModel one = response.body();

                        Intent intent = new Intent(LoginActivity.this, StepOne.class);
                        intent.putExtra("KEY_USER_DATA" , one);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                        finish();
                    }
                    else if (response.body().getStatusCode() == 500)
                    {
                        Support.showAlertDialog(LoginActivity.this , "Invalid login");
                    }
                }
                else
                {
                    Support.showAlertDialog(LoginActivity.this , "Invalid login!");
                }
            }

            @Override
            public void onFailure(Call<SignInOneModel> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }

}
