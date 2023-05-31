package com.example.retrofitprojectvisval.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitprojectvisval.databinding.ItemProductBinding
import com.example.retrofitprojectvisval.model.ProductResponseItem
import com.example.retrofitprojectvisval.ui.HomeFragmentDirections
import com.squareup.picasso.Picasso

class ProductAdapter:RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private  val productList = arrayListOf<ProductResponseItem>()

    inner class ProductViewHolder(val itemProductBinding: ItemProductBinding):
        RecyclerView.ViewHolder(itemProductBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
      val layout = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(layout)
    }

    override fun getItemCount(): Int {
     return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
         val productItem = productList[position]
        holder.itemProductBinding.product = productItem
        Picasso.get().load(productItem.image).into(holder.itemProductBinding.imageViewCardItem);
        holder.itemProductBinding.cardViewProduct.setOnClickListener{
         Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment32(productItem.id))

        }
    }

    fun updateList(list:List<ProductResponseItem>){
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }


}