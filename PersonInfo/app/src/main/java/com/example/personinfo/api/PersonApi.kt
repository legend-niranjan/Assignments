package com.example.personinfo.api

import com.example.personinfo.model.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApi {

    @GET("search/people")
    fun getAllPeople():Call<List<Person>>

    @GET("search/people")
    fun getPersonById(@Query("id")id:Int): Call<Person>



}