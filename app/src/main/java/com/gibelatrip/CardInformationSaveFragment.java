package com.gibelatrip;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gibelatrip.model.Passenger;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

public class CardInformationSaveFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "token";
    private static final String ARG_PARAM2 = "phone";

    TextInputEditText mCardNumber, mCardCVC, mCardExpMonth, mCardExpYear;

    private Button btnSave;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static CardInformationSaveFragment newInstance(String param1, String param2) {
        CardInformationSaveFragment fragment = new CardInformationSaveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_card_innformation_save, container, false);

        btnSave = (Button) v.findViewById(R.id.proceed);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = new Card(mCardNumber.getText().toString().trim(), Integer.parseInt(mCardExpMonth.getText().toString().trim()), Integer.parseInt(mCardExpMonth.getText().toString().trim()), mCardCVC.getText().toString());

                if ( !card.validateCard() ) {
                    // Show errors
                } else {
                    try {
                        Stripe stripe = new Stripe("pk_test_4SmzO8QHXBQZgT2bwoBdv2KN");
                        stripe.createToken(
                                card,
                                new TokenCallback() {
                                    public void onSuccess(Token token) {
                                        // Send token to your server
                                        new SaveCardInformation(token).execute();
                                    }

                                    public void onError(Exception error) {
                                        // Show localized error message
                                        Toast.makeText(getActivity(), error.getMessage(),
                                                Toast.LENGTH_LONG
                                        ).show();
                                    }
                                }
                        );
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }


            }
        });

        return v;
    }

    class SaveCardInformation extends AsyncTask<Void, Void, Passenger>{

        ProgressDialog pDialog;
        Token token;

        public SaveCardInformation(Token token){
            this.token = token;

        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(getActivity());
            pDialog.setTitle("Saving Information");
            pDialog.setMessage("Just a moment...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Passenger doInBackground(Void... params) {
            try {
                return (Passenger) Passenger.become(mParam1);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Passenger user) {
            super.onPostExecute(user);
            pDialog.dismiss();
            if(user != null){
                user.setPhone(mParam2);
                user.setToken(token.toString());
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                    }
                });
            }
        }
    }
}
