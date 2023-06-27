package com.example.ofs.DataLayer;

import com.example.ofs.entity.Attendance;
import com.example.ofs.entity.MobileBank;
import com.example.ofs.entity.Office;
import com.example.ofs.entity.Registrar;
import com.example.ofs.entity.Student;
import com.example.ofs.entity.StudentLoginValidation;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @FormUrlEncoded
    @POST("insertdata.php")
    Call<String> getStringScalar(@Field("department") String department,@Field("studentId") String studentId, @Field("attendance") String attendance,
                                 @Field("semester") String semester, @Field("year") String year);

    @FormUrlEncoded
    @POST("updatedata.php")
    Call<String> update(@Field("id") int id, @Field("department") String department, @Field("studentId") String studentId, @Field("attendance") String attendance,
                        @Field("semester") String semester, @Field("year") String year);

    @FormUrlEncoded
    @POST("registrar.php")
    Call<String> updateRegistrarInfo(@Field("id") int id,@Field("department") String department, @Field("initialDate") String initialDate, @Field("deadlineWithoutFine") String deadlineWithoutFine,
                                  @Field("deadlineWithFine") String deadlineWithFine, @Field("feePerCredit") int feePerCredit, @Field("finePerDay") int finePerDay);

    @GET("Attendance.php")
    Call<List<Attendance>> showAttendanceData(@Query("department") String department, @Query("semester") String semester, @Query("year") String year);

    @GET("checkAttendance.php")
    Call<List<Attendance>> getRequiredAttendance(@Query("studentId") String studentId, @Query("semester") String semester, @Query("year") String year);

    @GET("student.php")
    Call<List<Student>> getStudentInfo(@Query("studentId") String studentId);

    @GET("getRegistrar.php")
    Call<List<Registrar>> getRegistrar(@Query("department") String department);

    @GET("getMobileBank.php")
    Call<List<MobileBank>> getBankingData(@Query("mobile_number") String mobileNumber);

    @GET("officeLogin.php")
    Call<List<Office>> getOfficeData(@Query("email") String email);

    @GET("studentLogin.php")
    Call<List<StudentLoginValidation>> getStudentLoginData(@Query("studentId") String studnetId);
}