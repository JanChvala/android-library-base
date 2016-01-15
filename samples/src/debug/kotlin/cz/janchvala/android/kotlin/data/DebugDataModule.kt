package cz.janchvala.android.kotlin.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.facebook.stetho.okhttp.StethoInterceptor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import cz.janchvala.android.kotlin.AppScope
import cz.janchvala.android.kotlin.data.api.ApiModule
import cz.janchvala.android.kotlin.data.repository.RepositoryModule
import retrofit.Endpoint
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.JacksonConverter
import javax.inject.Named

@Module(includes = arrayOf(ApiModule::class, RepositoryModule::class))
public class DebugDataModule {

    @Provides
    @AppScope
    fun provideSharedPreferences(app: Application): SharedPreferences =
            app.getSharedPreferences("kotlin", Context.MODE_PRIVATE)

    @Provides
    @AppScope
    fun provideObjectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @Provides
    @AppScope
    public fun provideOkHttpClient(app: Application): OkHttpClient {
        val client = OkHttpClient()
        client.networkInterceptors().add(StethoInterceptor())
        return client
    }

    @Provides
    @AppScope
    @Named("Api")
    fun provideApiClient(client: OkHttpClient): OkHttpClient = client.clone()

    @Provides
    @AppScope
    fun provideRestAdapter(endpoint: Endpoint, @Named("Api") client: OkHttpClient,
            mapper: ObjectMapper): RestAdapter =
            RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(OkClient(client))
                    .setEndpoint(endpoint)
                    .setConverter(JacksonConverter(mapper))
                    .build()
}
