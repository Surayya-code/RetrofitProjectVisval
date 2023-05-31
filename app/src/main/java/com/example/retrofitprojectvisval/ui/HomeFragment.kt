package com.example.retrofitprojectvisval.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.retrofitprojectvisval.R
import com.example.retrofitprojectvisval.adapter.ProductAdapter
import com.example.retrofitprojectvisval.api.ApiUtils
import com.example.retrofitprojectvisval.databinding.FragmentHomeBinding
import com.example.retrofitprojectvisval.model.ProductResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
 private var _binding:FragmentHomeBinding?=null
 private val binding get()=_binding!!
 private val productAdapter=ProductAdapter()
 private val api=ApiUtils.getProductApi()

 override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?
 ): View {
  _binding= FragmentHomeBinding.inflate(inflater,container,false)
  return binding.root
 }

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)
  setRecycleView()
  getProduct()
 }

 private fun getProduct(){
  api.getAllProductApi().enqueue(object : Callback<List<ProductResponseItem>> {
   override fun onResponse(
    call: Call<List<ProductResponseItem>>,
    response: Response<List<ProductResponseItem>>
   ) {
        if(response.isSuccessful){
         response.body()?.let { productAdapter.updateList(it) }
          //Log.e("comeData",response.body().toString())
        }
   }

   override fun onFailure(call: Call<List<ProductResponseItem>>, t: Throwable) { }

   })
 }

 private fun  setRecycleView(){
   binding.rvProducts.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
  binding.rvProducts.adapter = productAdapter

 }

 override fun onDestroyView() {
  super.onDestroyView()
  _binding=null
 }
}