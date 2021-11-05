package com.andriod.githubapiapp.di

import com.andriod.githubapiapp.model.CombineDataProvider
import com.andriod.githubapiapp.model.DataProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

val combineModule = module {
    single<DataProvider> {
        CombineDataProvider(
            get(named("web")),
            get(named("local"))
        )
    }
}
