package com.abernikov.multiplatformapp.android

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://api.kinopoisk.dev"

    private const val API_CALL_NO_TIMEOUT = 0L
    private const val API_CALL_TIMEOUT_DURATION_SECONDS = 60L

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    @Singleton
    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val initialRequest = chain.request()

            val newUrl = initialRequest.url.newBuilder()
                .addQueryParameter("X-API-KEY", "BRD4ZAX-3TJ45XQ-Q3SYHYW-6JZXA89")
                .build()

            val newRequest = initialRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }
    @Singleton
    @Provides
    fun providesOkHttpClient(
        authenticator: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(API_CALL_NO_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(API_CALL_NO_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(API_CALL_NO_TIMEOUT, TimeUnit.MILLISECONDS)
        .callTimeout(API_CALL_TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

}