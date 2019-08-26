package br.com.siatiquosque.webmotorstest.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.siatiquosque.webmotorstest.vo.Resource
import retrofit2.Response

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.Loading()

        val db = loadFromDb()
        result.addSource(db) { data ->
            result.removeSource(db)
            if (shouldFetch(data)) {
                fetchFromNetwork(db)
            }

        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData ->
            setValue(Resource.Loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.isSuccessful) {
                true -> {
                    saveCallResult(response.body())
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.Success(newData))
                    }
                }
                 else -> {
                     onFetchFailed()
                     result.addSource(dbSource) { newData ->
                         setValue(Resource.Error(response.errorBody()?.string().toString(), newData))
                     }
                 }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<Response<RequestType>>


}