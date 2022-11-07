package pl.sabuk.mateusz.assignment.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import pl.sabuk.mateusz.assignment.AddBookActivity;
import pl.sabuk.mateusz.assignment.BookAPIClass;
import pl.sabuk.mateusz.assignment.R;

public class ScannerFragment extends Fragment {
    private SurfaceView surfaceView;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        // Inflate the layout for this fragment // Fragment inner-workings stuff
        return inflater.inflate(R.layout.fragment_scanner, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button addManuallyButton = (Button) getView().findViewById(R.id.manual_button);
        addManuallyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Opens "add book" activity
                Intent intent = new Intent(getActivity(), AddBookActivity.class);
                startActivity(intent);
            }
        });


        Button addISBNButton = (Button) getView().findViewById(R.id.isbnNumBtn);
        EditText textISBNField = (EditText) getView().findViewById(R.id.isbnNumFinder);
        addISBNButton.setOnClickListener(view1 -> {
            String ISBNNum = textISBNField.getText().toString();

            // TODO make sure that the input is a number

            // TODO implement scanner

            // TODO Start add book activity with needed info



            Log.d("isbn Num", ISBNNum);
            Toast.makeText(getContext(), "Getting Book from the web", Toast.LENGTH_SHORT).show();
            BookAPIClass book = new BookAPIClass(ISBNNum, this.requireContext());

        });
    }

    }