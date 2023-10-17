package com.example.eduguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class uploadfile extends AppCompatActivity {

    Button btnBrowse,btnUpload;
    EditText fileName,subjectName;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadfile);

        btnBrowse = findViewById(R.id.btnBrowse);
        btnUpload = findViewById(R.id.btnUpload);
        fileName = findViewById(R.id.fileName);
        subjectName = findViewById(R.id.subjectName);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("uploadedFiles/sem1");

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFiles();
            }
        });
    }

    private void selectedFiles() {
        Intent i = new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"select pdf file.."),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            UploadFiles(data.getData());
        }
    }

    private void UploadFiles(Uri data) {

        long fileSize = getFileSize(data);

        if (fileSize > 2 * 1024 * 1024) {
            Toast.makeText(this, "File size exceeds the limit (2MB)", Toast.LENGTH_SHORT).show();
            return;
        }


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("uploading...");
        progressDialog.show();

        String subject = subjectName.getText().toString().trim();
        String uploadedFileName = fileName.getText().toString().trim();

        String fileNameWithTimestamp = "file_" + System.currentTimeMillis() + ".pdf";

        StorageReference reference = storageReference.child("uploadedFiles/sem1/" + subject + "/" + fileNameWithTimestamp);
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri url = uriTask.getResult();

                                pdfClass pdfClass = new pdfClass(fileName.getText().toString(),subjectName.getText().toString(),url.toString());
                                databaseReference.child(databaseReference.push().getKey()).setValue(pdfClass);



                                Toast.makeText(uploadfile.this,"file uploaded",Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(uploadfile.this, "Failed to get download URL", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        double progress = (100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("uploaded:" + (int)progress+"%");

                    }
                });
    }

    private long getFileSize(Uri uri) {
        long fileSize = 0;
        try {
            ContentResolver contentResolver = getContentResolver();
            AssetFileDescriptor assetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
            if (assetFileDescriptor != null) {
                fileSize = assetFileDescriptor.getLength();
                assetFileDescriptor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileSize;
    }


}