package com.example.custom_home_widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide


class ItemWidgetAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ItemWidgetModel>
) : PagerAdapter() {

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false)
        val imageView: ImageView = view.findViewById(R.id.companyImageView)
        val textView: TextView = view.findViewById(R.id.widget_title)

        val model = arrayList[position]
        val nameCompany = model.nameCompany
        val pathImage = model.pathImage



        Glide.with(context).load(pathImage).placeholder(R.drawable.launch_background).into(imageView)

        textView.text = nameCompany


        container.addView(view, position)

        return view
//        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}


