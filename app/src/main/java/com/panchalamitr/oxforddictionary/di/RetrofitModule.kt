package com.panchalamitr.oxforddictionary.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.panchalamitr.oxforddictionary.service.MovieService
import com.panchalamitr.oxforddictionary.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp)
    }

    @Singleton
    @Provides
    fun provideMovie(retrofitBuilder: Retrofit.Builder): MovieService {
        return retrofitBuilder.build().create(MovieService::class.java)
    }

    private val okHttp: OkHttpClient
        get() {
            val loggingInterceptor = LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(Log.VERBOSE)
                .build()
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }
}