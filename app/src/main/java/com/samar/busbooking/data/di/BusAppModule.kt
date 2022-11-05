package com.samar.busbooking.data.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.*
import com.samar.busbooking.data.remote.BusApi
import com.samar.busbooking.data.repository.BusRepositoryImpl
import com.samar.busbooking.domain.repository.BusRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.Instant
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BusAppModule {


    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Singleton
    @Provides
    fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES);
        httpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader("token", "YjBOaWxGT01lQ2pNL3E1ZzNYd0wwSkl6OWw5TDY4a2ZDY0JsMVhaT1pJcEx0cVVocFpTQ1d2eWNGckNYZTdFSUlrYTRUc0FSVWdFPQ==")
                .build()
            val response = chain.proceed(request)
            response
        })
        httpClient.addInterceptor(httpLoggingInterceptor)
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun getBusApi(httpClient: OkHttpClient): BusApi {
        val gson = GsonBuilder().registerTypeAdapter(
            Instant::class.java,
            object : JsonDeserializer<Instant?> {
                @Throws(JsonParseException::class)
                override fun deserialize(
                    json: JsonElement,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): Instant? {
                    return Instant.parse(json.asString)
                }
            }).create()
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://business.paymitro.com")
            .client(httpClient)
            .build()
        return retrofit.create(BusApi::class.java)
    }

    @Singleton
    @Provides
    fun getBusRepository(busApi: BusApi): BusRepository{
        return BusRepositoryImpl(busApi = busApi)
    }


    @Singleton
    @Provides
    fun getSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
    }

}