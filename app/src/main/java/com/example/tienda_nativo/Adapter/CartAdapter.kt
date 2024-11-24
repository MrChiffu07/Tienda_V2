package com.example.tienda_nativo.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.tienda_nativo.databinding.ViewholderCartBinding
import com.example.tienda_nativo.Helper.ChangeNumberItemsListener
import com.example.tienda_nativo.ManagmentCart
import com.example.tienda_nativo.Model.itemModel

class CartAdapter (

    private val listItemSelected:ArrayList<itemModel>,
    context:Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?=null
    ):RecyclerView.Adapter<CartAdapter.ViewHolder>(){
    class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root){

    }

    private val managementCart=ManagmentCart(context)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
      val binding=ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
       val item = listItemSelected[position]

        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachItem.text="$${item.price}"
        holder.binding.totalEachitem.text="$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text=item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)

        holder.binding.plusCartbtn.setOnClickListener{
            managementCart.plusItem(listItemSelected, position, object: ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()

                }

            })
        }

        holder.binding.minustCartBtn.setOnClickListener{
            managementCart.minusItem(
                listItemSelected,
                position,
                object: ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()

                }

            })
        }
    }
    override fun getItemCount(): Int=listItemSelected.size

}
