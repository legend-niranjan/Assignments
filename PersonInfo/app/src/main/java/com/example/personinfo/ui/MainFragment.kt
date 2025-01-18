package com.example.personinfo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personinfo.R
import com.example.personinfo.adapter.PeopleAdapter
import com.example.personinfo.api.RetrofitInstance
import com.example.personinfo.model.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {
    private lateinit var peopleAdapter: PeopleAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView=view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        fetchPeople()

        return view
    }

    private fun fetchPeople(){
        RetrofitInstance.api.getAllPeople().enqueue(object : Callback<List<Person>> {
            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                if(response.isSuccessful){
                    val people=response.body()?: emptyList()

                    peopleAdapter=PeopleAdapter(people){ person:Person ->
                        val bundle=Bundle().apply { putInt("personId",person.id) }
                        findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)
                    }
                    recyclerView.adapter=peopleAdapter

                }else{
                    Toast.makeText(requireContext(),"Error to fetching people",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                Toast.makeText(requireContext(),"${t.message}",Toast.LENGTH_SHORT).show()
            }

        })
    }

}
