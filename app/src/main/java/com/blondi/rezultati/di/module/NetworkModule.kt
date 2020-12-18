package com.blondi.rezultati.di.module

import android.util.Log
import com.blondi.rezultati.BuildConfig
import com.blondi.rezultati.common.ResponseHandler
import com.blondi.rezultati.common.RezultatiApi
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val CURL_LOG_TAG = "< CURL >"

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get(), get()) }
    single { provideRezultatiApi(get()) }
    single { provideLoggingInterceptor() }
    single { ResponseHandler() }
    single { provideCurlInterceptor() }
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.ROOT_API_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideOkHttpClient(
    logger: HttpLoggingInterceptor,
    curlInterceptor: CurlInterceptor
): OkHttpClient {
    val client = OkHttpClient().newBuilder()
    client.addInterceptor(logger)
    client.addInterceptor(curlInterceptor)
    return client.build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}


fun provideRezultatiApi(retrofit: Retrofit): RezultatiApi =
    retrofit.create(RezultatiApi::class.java)



fun provideCurlInterceptor(): CurlInterceptor =
    CurlInterceptor(Loggable() {
        if (BuildConfig.DEBUG) {
            Log.d(CURL_LOG_TAG, it)
        }
    })

