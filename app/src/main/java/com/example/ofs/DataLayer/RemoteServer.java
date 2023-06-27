package com.example.ofs.DataLayer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.ofs.BusinessLogicLayer.MyOwnAdapter;
import com.example.ofs.PresentationLayer.RegistrarInformation;
import com.example.ofs.entity.Attendance;
import com.example.ofs.entity.AttendanceList;
import com.example.ofs.entity.MobileBank;
import com.example.ofs.entity.Office;
import com.example.ofs.entity.Registrar;
import com.example.ofs.entity.Student;
import com.example.ofs.entity.StudentLoginValidation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteServer {
    List<Attendance> attendance = new ArrayList<>();
    AttendanceList attend;

    public static final String BASE_URL = "http://192.168.0.108/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    public APIService  api = retrofit.create(APIService.class);

    public APIService getApi(){
        return retrofit.create(APIService.class);
    }

    public void sendPost (String department,String studentId,String attendance,String semester, String year ) throws IOException {
        Log.d("remote",department);
        int u = 6;
        Log.d("sum", String.valueOf(u));

        Call<String> call = api.getStringScalar(department,studentId,attendance,semester,year);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {


            }
        });

    }

    public void updateRegistrarInfo ( int id, String department, String initialDate, String deadlineWithoutFine, String deadlineWithFine, int feePerCredit, int finePerDay ) throws IOException {

        Call<String> call = api.updateRegistrarInfo(id, department,initialDate, deadlineWithoutFine, deadlineWithFine, feePerCredit, finePerDay);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {


            }
        });

    }

    public void updateData(int id,String department, String studentId, String attendance, String semester, String year) throws IOException{

        Call<String> call = api.update(id,department,studentId,attendance,semester,year);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                //
            }
        });
    }

    public void loadDatafromServer(String department,String semester, String year,final DataReceiver receiver) throws IOException {

        Call<List<Attendance>> call = api.showAttendanceData(department,semester,year);

        call.enqueue(new Callback<List<Attendance>>() {
            @Override
            public void onResponse(Call<List<Attendance>> call, Response<List<Attendance>> response) {
                if (response.isSuccessful() && receiver != null) {
                    receiver.onReceive(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Attendance>> call, Throwable t) {


            }
        });

    }

    public void checkAttendance(String studentId, String semester,String year, final DataReceiver receiver){
        Call<List<Attendance>> call = api.getRequiredAttendance(studentId,semester,year);

        call.enqueue(new Callback<List<Attendance>>() {
            @Override
            public void onResponse(Call<List<Attendance>> call, Response<List<Attendance>> response) {
                if (response.isSuccessful() && receiver != null) {
                    receiver.onReceive(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Attendance>> call, Throwable t) {


            }
        });
    }

    public void getStudentInfo(String studentInfo, final DataReceiver receiver){
        Call<List<Student>> call = api.getStudentInfo(studentInfo);

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful() && receiver != null) {
                    receiver.onReceive(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {


            }
        });
    }

    public void getRegistrarInfo(String department,final DataReceiver receiver){
        Call<List<Registrar>> call = api.getRegistrar(department);

        call.enqueue(new Callback<List<Registrar>>() {
            @Override
            public void onResponse(Call<List<Registrar>> call, Response<List<Registrar>> response) {
                if (response.isSuccessful() && receiver != null) {
                    receiver.onReceive(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Registrar>> call, Throwable t) {


            }
        });
    }

    public void getMobileBankingData(String mobile_number,final DataReceiver receiver){
        Call<List<MobileBank>> call = api.getBankingData(mobile_number);

        call.enqueue(new Callback<List<MobileBank>>() {
            @Override
            public void onResponse(Call<List<MobileBank>> call, Response<List<MobileBank>> response) {
                if (response.isSuccessful() && receiver != null) {
                    receiver.onReceive(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<MobileBank>> call, Throwable t) {


            }
        });
    }

    public void getOfficeData(String email, final DataReceiver receiver){
        Call<List<Office>> call = api.getOfficeData(email);

        call.enqueue(new Callback<List<Office>>() {
            @Override
            public void onResponse(Call<List<Office>> call, Response<List<Office>> response) {
                if (response.isSuccessful() && receiver != null) {
                    receiver.onReceive(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Office>> call, Throwable t) {


            }
        });
    }

    public void getStudentLoginData(String studentId, final DataReceiver receiver){
        Call<List<StudentLoginValidation>> call = api.getStudentLoginData(studentId);

        call.enqueue(new Callback<List<StudentLoginValidation>>() {
            @Override
            public void onResponse(Call<List<StudentLoginValidation>> call, Response<List<StudentLoginValidation>> response) {
                if (response.isSuccessful() && receiver != null) {
                    receiver.onReceive(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<StudentLoginValidation>> call, Throwable t) {


            }
        });
    }

}
