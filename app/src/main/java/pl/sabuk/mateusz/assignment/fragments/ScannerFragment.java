package pl.sabuk.mateusz.assignment.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

import pl.sabuk.mateusz.assignment.AddBookActivity;
import pl.sabuk.mateusz.assignment.BookAPIClass;
import pl.sabuk.mateusz.assignment.R;

public class ScannerFragment extends Fragment {
    private SurfaceView surfaceView;
    private EditText textISBNField;

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

        surfaceView = getView().findViewById(R.id.surface_view);
        initialiseDetectorsAndSources();


        addManuallyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Opens "add book" activity
                Intent intent = new Intent(getActivity(), AddBookActivity.class);
                startActivity(intent);
            }
        });


        Button addISBNButton = (Button) getView().findViewById(R.id.isbnNumBtn);
        textISBNField = (EditText) getView().findViewById(R.id.isbnNumFinder);
        addISBNButton.setOnClickListener(view1 -> {
            String ISBNNum = textISBNField.getText().toString();

            // TODO keep on fragment when changing to from vertical



            Log.d("isbn Num", ISBNNum);
            Toast.makeText(getContext(), "Getting Book from the web", Toast.LENGTH_SHORT).show();
            new BookAPIClass(ISBNNum, this.requireContext(), this.getActivity());


        });
    }






    // this method is based on code from:
    // https://medium.com/analytics-vidhya/creating-a-barcode-scanner-using-android-studio-71cff11800a2

    private void initialiseDetectorsAndSources() {

        Toast.makeText(getContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();

        barcodeDetector = new BarcodeDetector.Builder(getContext())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(getContext(), barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                // Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    String barcodeVal =  barcodes.valueAt(0).displayValue;
                    Log.d("barcode", barcodeVal);
                    textISBNField.setText(barcodeVal);


                }
            }
        });
    }












}