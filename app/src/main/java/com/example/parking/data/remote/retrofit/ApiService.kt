package com.example.parking.data.remote.retrofit

import com.example.parking.data.model.Parking.BodyAddParking
import com.example.parking.data.model.Payment.BodyCreatePayment
import com.example.parking.data.model.Auth.BodyRegister
import com.example.parking.data.model.WA.BodySendOtp
import com.example.parking.data.model.Parking.BodyUpdateParking
import com.example.parking.data.model.Auth.BodyValidation
import com.example.parking.data.model.ParkingHistory.BodyCreateHistory
import com.example.parking.data.model.ParkingHistory.BodyUpdateHistory
import com.example.parking.data.model.ParkingHistory.QueryAggregateHistory
import com.example.parking.data.model.User.BodyCreateUser
import com.example.parking.data.model.User.QueryGetUser
import com.example.parking.data.remote.response.Parking.AddParkingResponse
import com.example.parking.data.remote.response.Payment.CreatePaymentResponse
import com.example.parking.data.remote.response.Parking.GetParkingOwnerResponse
import com.example.parking.data.remote.response.WA.OtpResponse
import com.example.parking.data.remote.response.Auth.RegisterResponse
import com.example.parking.data.remote.response.Parking.UpdateParkingResponse
import com.example.parking.data.remote.response.Auth.ValidationResponse
import com.example.parking.data.remote.response.Parking.GetParkingResponse
import com.example.parking.data.remote.response.ParkingHistory.CreateHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.GetDetailHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.GetHistoryResponse
import com.example.parking.data.remote.response.ParkingHistory.UpdateHistoryResponse
import com.example.parking.data.remote.response.User.CreateUserResponse
import com.example.parking.data.remote.response.User.GetUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // auth
    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @POST("auth/register")
    suspend fun registerUser(
        @Body bodyRegister: BodyRegister
    ):Response<RegisterResponse>

    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @POST("auth/activate-phone-number")
    suspend fun validationUser(
        @Body bodyValidation: BodyValidation
    ):Response<ValidationResponse>

    // wa
    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @POST("whatsapp/send")
    suspend fun sendOtp(
        @Body bodyOtp: BodySendOtp
    ):Response<OtpResponse>

    // parking lot
    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @POST("parking-lot")
    suspend fun addParking(
        @Body bodyParking: BodyAddParking
    ):Response<AddParkingResponse>

    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @PATCH("parking-lot/{id}")
    suspend fun updateParking(
        @Path("id") id:String,
        @Body bodyParking: BodyUpdateParking
    ):Response<UpdateParkingResponse>

    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @GET("parking-lot/owner/{id}")
    suspend fun getParkingByOwner(
        @Path("id") id:String,
    ):Response<GetParkingOwnerResponse>


    @GET("parking-lot/{id}")
    suspend fun getParkingById(
        @Path("id") id:String,
    ):Response<GetParkingResponse>



    // payment
    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @POST("payment/generate")
    suspend fun createPayment(
        @Body bodyCreatePayment: BodyCreatePayment
    ):Response<CreatePaymentResponse>

    //user
    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @POST("user")
    suspend fun createUser(
        @Body bodyCreateUser: BodyCreateUser
    ):Response<CreateUserResponse>

    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @PATCH("user/{phone}")
    suspend fun updateUser(
        @Path("phone") phone:String,
        @Body bodyUpdateUser: BodyCreateUser
    ):Response<CreateUserResponse>

    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @GET("user/aggregate")
    suspend fun getUser(
        queryGetUser: QueryGetUser
    ):Response<GetUserResponse>


    //parking history
    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @GET("parking-history/aggregate")
    suspend fun aggregateHistory(
        queryAggregateHistory: QueryAggregateHistory
    ):Response<GetHistoryResponse>

    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @GET("parking-history/{id}")
    suspend fun getHistoryById(
        @Path("id") id:String
    ):Response<GetDetailHistoryResponse>

    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @POST("parking-history")
    suspend fun createHistory(
        @Body bodyHistory:BodyCreateHistory
    ):Response<CreateHistoryResponse>

    @Headers(
        value = [
            "User-Agent: request",
            "Content-Type: application/json"
        ]
    )
    @PATCH("parking-history/{id}")
    suspend fun updateHistory(
        @Path("id") id:String,
        @Body bodyHistory:BodyUpdateHistory
    ):Response<UpdateHistoryResponse>
}