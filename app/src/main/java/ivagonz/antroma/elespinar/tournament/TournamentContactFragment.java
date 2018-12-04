package ivagonz.antroma.elespinar.tournament;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ivagonz.antroma.elespinar.R;


public class TournamentContactFragment extends Fragment implements View.OnClickListener{

    private Button beforeButton, comButton,secButton,officeButton,befPhoneButton,comPhoneButton;
    private Resources resources;

    public TournamentContactFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_tournament_contact_fragment, container, false);
        resources = getResources();
        beforeButton = (Button)v.findViewById(R.id.tournament_contact_before_email_address);
        comButton = (Button)v.findViewById(R.id.tournament_contact_com_email_address);
        secButton = (Button)v.findViewById(R.id.tournament_contact_secretary_phone);
        officeButton = (Button)v.findViewById(R.id.tournament_office_phone);
        befPhoneButton = (Button)v.findViewById(R.id.tournament_contact_before_phone_number);
        comPhoneButton = (Button)v.findViewById(R.id.tournament_contact_com_phone_number);

        beforeButton.setOnClickListener(this);
        comButton.setOnClickListener(this);
        secButton.setOnClickListener(this);
        officeButton.setOnClickListener(this);
        befPhoneButton.setOnClickListener(this);
        comPhoneButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tournament_contact_before_email_address:
                sendMail(resources.getString(R.string.tournament_contact_before_email_address));
                break;
            case R.id.tournament_contact_com_email_address:
                sendMail(resources.getString(R.string.tournament_contact_com_email_address));
                break;
            case R.id.tournament_contact_secretary_phone:
                phoneCall(resources.getString(R.string.tournament_contact_secretary_phone));
                break;
            case R.id.tournament_office_phone:
                phoneCall(resources.getString(R.string.tournament_contact_office_phone));
                break;
            case R.id.tournament_contact_before_phone_number:
                phoneCall(resources.getString(R.string.tournament_contact_before_phone_number));
                break;
            case R.id.tournament_contact_com_phone_number:
                phoneCall(resources.getString(R.string.tournament_contact_com_number));
                break;
        }

    }

    private void phoneCall(String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(Intent
                .createChooser(intent, getString(R.string.select_app)));
    }

    private void sendMail(String address){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
        startActivity(Intent
                .createChooser(intent, getString(R.string.select_app)));

    }


}
