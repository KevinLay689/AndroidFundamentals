package com.example.kevinlay.androidfundamentalspractice.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kevinlay.androidfundamentalspractice.R;

/**
 * Custom Dialog Fragment
 *
 * Major Takeaways
 * -Bare minimum is to override onCreateView or onCreateDialog, onCreateView is when entire dialog will
 * be defined as custom xml, onCreateDialog to configure a standard Dialog class (such as AlertDialog)
 * -All must use the support library namespace
 *
 *
 * Steps to creating a Custom Dialog Fragment
 *      1. Create a static newInstance method for instantiating the fragment
 *      2. Override onCreateView and inflate the layout
 *      3. Override onViewCreated and target and handle view data
 *
 * Steps to calling the new Custom Dialog Fragment from another Fragment
 *      1. Get the Fragment Manager
 *      2. Create the instance of your Custom Dialog Fragment
 *      3. Show the fragment with fragment.show(fm, "tag")
 */

public class MyCustomDialogFragment extends DialogFragment {

    private EditText editText;
    private TextView textView;

    public MyCustomDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    // Use this static method to add arguments because constructor won't allow it
    // This is best practice because android uses the empty constructor fragment when re-creating a fragment
    public static MyCustomDialogFragment newInstance(String title) {
        MyCustomDialogFragment fragment = new MyCustomDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_custom_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = (EditText) view.findViewById(R.id.etCustomDialog);
        textView = (TextView) view.findViewById(R.id.tvCustomDialogTitle);

        String title = getArguments().getString("title", "No title given");
        getDialog().setTitle(title);

    }
}
