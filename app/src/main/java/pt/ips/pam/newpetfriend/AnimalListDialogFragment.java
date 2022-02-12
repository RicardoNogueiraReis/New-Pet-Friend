package pt.ips.pam.newpetfriend;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class AnimalListDialogFragment extends DialogFragment {
    // the fragment initialization parameter
    private static final String ARG_ANIMALS = "animals";
    private List<Animal> animals;
    private AnimalListDialogFragmentListener listener;
    /*
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static AnimalListDialogFragment newInstance(ArrayList<Animal> animals) {
        AnimalListDialogFragment fragment = new AnimalListDialogFragment();
        Bundle args = new Bundle();

        args.putParcelableArrayList(ARG_ANIMALS, animals);
        fragment.setArguments(args);
        return fragment;
    }

    public interface AnimalListDialogFragmentListener {
        public void onAnimalSeleced(Animal animal);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            animals = getArguments().getParcelableArrayList(ARG_ANIMALS);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.animal_list)
                .setAdapter(new AnimalListAdapter(getActivity(), animals), (dialog, which) -> {
                    listener.onAnimalSeleced(animals.get(which));
                    this.dismiss();
                }).create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the UserListDialogFragmentListener so we can send events to the host
            listener = (AnimalListDialogFragmentListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement UserListDialogFragmentListener");
        }
    }
}