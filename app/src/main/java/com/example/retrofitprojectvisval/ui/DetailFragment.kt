package com.example.retrofitprojectvisval.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.retrofitprojectvisval.R
import com.example.retrofitprojectvisval.api.ApiUtils
import com.example.retrofitprojectvisval.databinding.FragmentDetailBinding
import com.example.retrofitprojectvisval.model.ProductResponseItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {
    private var _binding:FragmentDetailBinding?=null
    private val binding get() =_binding!!
    private val args:DetailFragmentArgs by navArgs()
    private val api=ApiUtils.getProductApi()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProductSingleData()
    }

    private fun getProductSingleData(){
        val id=args.productId
        api.getSingleProduct(id).enqueue(object : Callback<ProductResponseItem> {
            override fun onResponse(
                call: Call<ProductResponseItem>,
                response: Response<ProductResponseItem>
            ) {
                if(response.isSuccessful){
                    binding.product=response.body()
                    Picasso.get().load(response.body()?.image).into(binding.imageViewRug);
                }
            }

            override fun onFailure(call: Call<ProductResponseItem>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}