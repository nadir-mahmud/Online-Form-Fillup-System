package com.example.ofs.PresentationLayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ofs.DataLayer.DataReceiver;
import com.example.ofs.DataLayer.RemoteServer;
import com.example.ofs.R;
import com.example.ofs.entity.Student;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdmitCard extends AppCompatActivity {
    Image image,image1,image2;
    RemoteServer remoteServer;
    List<Student> students;
    String name,fatherName,motherName,departmentName,hallName,studentId,semester,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admit_card);

        students = new ArrayList<>();

        Intent intent = getIntent();
        studentId = intent.getStringExtra("studentId");
        semester = intent.getStringExtra("semester");

        Calendar cal = Calendar.getInstance();
        year = String.valueOf(cal.get(Calendar.YEAR));

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                getPackageManager().PERMISSION_GRANTED);

        Button button = findViewById(R.id.download);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    downloadPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void downloadPdf() throws FileNotFoundException, MalformedURLException {

        remoteServer = new RemoteServer();
        remoteServer.getStudentInfo(studentId, new DataReceiver() {
            @Override
            public void onReceive(List<?> dataList) {
                students = (List<Student>) dataList;
                name = students.get(0).getName();
                fatherName = students.get(0).getFatherName();
                motherName = students.get(0).getMotherName();
                departmentName = students.get(0).getDepartment();
                hallName = students.get(0).getHallName();

                String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
                File file = new File(pdfPath, "Admit Card.pdf");
                OutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                com.itextpdf.text.pdf.PdfDocument pdfDocument = new com.itextpdf.text.pdf.PdfDocument();
                //pdfDocument.setDefaultPageSize(PageSize.A4);
                Document document = new Document(PageSize.A4);
                try {
                    PdfWriter.getInstance(document,outputStream);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                document.open();
                document.setMargins(30,30,60,0);

                int width = (int) document.getPageSize().getWidth();
                Log.d("width", String.valueOf(width));
                int height = (int) document.getPageSize().getHeight();
                Log.d("Height", String.valueOf(height));

                document.open();


                Drawable d = getResources().getDrawable(R.drawable.logo1);
                BitmapDrawable bitDw = ((BitmapDrawable) d);
                Bitmap bmp = bitDw.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bitmapData = stream.toByteArray();

                Drawable d1 = getResources().getDrawable(R.drawable.signature);
                BitmapDrawable bitDw1 = ((BitmapDrawable) d1);
                Bitmap bmp1 = bitDw1.getBitmap();
                ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
                byte[] bitmapData1 = stream1.toByteArray();

                Drawable d2 = getResources().getDrawable(R.drawable.signature1);
                BitmapDrawable bitDw2 = ((BitmapDrawable) d2);
                Bitmap bmp2 = bitDw2.getBitmap();
                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                bmp2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                byte[] bitmapData2 = stream2.toByteArray();
                try {

                    image = Image.getInstance(bitmapData);
                    image.setAlignment(Image.ALIGN_CENTER);
                    image.scaleToFit(30,42);

                    image1 = Image.getInstance(bitmapData1);
                    image1.scaleToFit(50,20);

                    image2 = Image.getInstance(bitmapData2);
                    image2.scaleToFit(30,42);
                } catch (BadElementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Paragraph paragraph = new Paragraph(" " +
                        " " +
                        " ");


                Paragraph paragraph1 = new Paragraph();
                paragraph.setAlignment(Paragraph.ALIGN_CENTER);
                Phrase p = new Phrase("University of Chittagong", new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD));
                paragraph.add(p);


                Paragraph paragraph2 = new Paragraph();
                paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
                Phrase p2 = new Phrase(departmentName, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
                paragraph2.add(p2);

                Paragraph paragraph3 = new Paragraph();
                paragraph3.setAlignment(Paragraph.ALIGN_CENTER);
                Phrase p3 = new Phrase(semester + " ," + " Exam " + "- " + year, new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
                paragraph3.add(p3);

                Paragraph paragraph4 = new Paragraph(" " +
                        " " +
                        " ");

                Paragraph paragraph5 = new Paragraph();
                paragraph5.setAlignment(Paragraph.ALIGN_CENTER);
                Phrase admit = new Phrase("Admit Card", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
                paragraph5.add(admit);

                Paragraph paragraph6 = new Paragraph(" " +
                        " " +
                        " ");

                Paragraph paragraph7 = new Paragraph(" " +
                        " " +
                        " ");



                PdfPTable pdfPTable = new PdfPTable(2);
                try {
                    pdfPTable.setTotalWidth(new float[] {document.getPageSize().getWidth()/3,document.getPageSize().getHeight()/2} );

                    Phrase p4 = new Phrase("Name", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
                    PdfPCell cell1 = new PdfPCell(p4);
                    cell1.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell1);

                    Phrase p5 = new Phrase(name, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
                    PdfPCell cell2 = new PdfPCell(p5);
                    cell2.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell2);

                    Phrase p6 = new Phrase("Father's Name", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
                    PdfPCell cell3 = new PdfPCell(p6);
                    cell3.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell3);

                    Phrase p7 = new Phrase(fatherName, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
                    PdfPCell cell4 = new PdfPCell(p7);
                    cell4.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell4);

                    Phrase p8 = new Phrase("Mother's Name", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
                    PdfPCell cell5 = new PdfPCell(p8);
                    cell5.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell5);

                    Phrase p9 = new Phrase(motherName, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
                    PdfPCell cell6 = new PdfPCell(p9);
                    cell6.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell6);

                    Phrase p10 = new Phrase("Hall", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
                    PdfPCell cell7 = new PdfPCell(p10);
                    cell7.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell7);

                    Phrase p11 = new Phrase(hallName, new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
                    PdfPCell cell8 = new PdfPCell(p11);
                    cell8.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell8);

                    PdfPCell cell9 = new PdfPCell(image1);
                    cell9.setPadding(5);
                    cell9.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell9);

                    PdfPCell cell10 = new PdfPCell(image2);
                    cell10.setPadding(5);
                    cell10.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell10);


                    Phrase p12 = new Phrase("Chairman", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
                    PdfPCell cell11 = new PdfPCell(p12);
                    cell11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell11);

                    Phrase p13 = new Phrase("Controller of Examination", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD));
                    PdfPCell cell12 = new PdfPCell(p13);
                    cell12.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    pdfPTable.addCell(cell12);



                } catch (DocumentException e) {
                    e.printStackTrace();
                }


                try {
                    document.add(image);
                    document.add(paragraph);
                    document.add(paragraph1);
                    document.add(paragraph2);
                    document.add(paragraph3);
                    document.add(paragraph4);
                    document.add(paragraph5);
                    document.add(paragraph6);
                    document.add(paragraph7);
                    document.add(pdfPTable);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                document.close();


                File file1 = new File(pdfPath + "/" + "Admit Card.pdf");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file1),"application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                Intent intent1 = Intent.createChooser(intent,"open file");
                startActivity(intent1);


            }
        });




    }
}