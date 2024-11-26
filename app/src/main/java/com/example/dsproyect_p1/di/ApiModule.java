package com.example.dsproyect_p1.di;

import com.example.dsproyect_p1.data.api.*;
import com.example.dsproyect_p1.data.local.*;
import com.example.dsproyect_p1.data.repository.*;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public abstract class ApiModule {


  @Binds
  @Singleton
  public abstract ContactApi bindContactApi(LocalStorageContactApi contactApi);
}
