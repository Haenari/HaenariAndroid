package com.haenari.haenari.data.repository.sample

import com.haenari.haenari.data.repository.sample.source.SampleLocalDataSource
import com.haenari.haenari.data.repository.sample.source.SampleRemoteDataSource
import com.haenari.haenari.domain.repository.sample.SampleRepository

class SampleRepositoryImpl(
    private val localDataSource: SampleLocalDataSource,
    private val remoteDataSource: SampleRemoteDataSource
) : SampleRepository {

}