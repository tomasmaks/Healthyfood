package my.food.tomas.healthyfood.mainScreen;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import my.food.tomas.healthyfood.R;


/**
 * Created by Tomas on 31/10/2016.
 */

public class AboutDialogFragment extends DialogFragment {

    @Bind(R.id.about1_view)
    TextView aboutView1;
    @Bind(R.id.about2_view)
    TextView aboutView2;

    public static AboutDialogFragment newInstance() {
        AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
        Bundle bundle = new Bundle();
        aboutDialogFragment.setArguments(bundle);
        return aboutDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootView = inflater.inflate(R.layout.dialog_about, null);

        ButterKnife.bind(this, rootView);

        aboutView1.setText(Html.fromHtml("This is Healthy-Food Android App made by Tomas Maksimavicius."));

        aboutView2.setText(Html.fromHtml("Tools: Retrofit2, RxJava, Dagger2, MVP"));

        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton(R.string.positive_button_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();

    }



}
