package com.box.io.di;

import android.content.Context;

import com.box.io.rest.NetworkMockHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {


    @Provides
    @ApplicationScope
    NetworkMockHelper provideNotworkModule(Context context) {
        return new NetworkMockHelper(context);
    }

}