package com.example.custom_home_widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetBackgroundIntent
import es.antonborri.home_widget.HomeWidgetLaunchIntent
import es.antonborri.home_widget.HomeWidgetProvider
import org.json.JSONObject
import org.json.JSONStringer

class HomeWidgetProviderEx : HomeWidgetProvider(){


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        widgetData: SharedPreferences
    ) {
        appWidgetIds.forEach { widgetId ->
            val views = RemoteViews(context.packageName,R.layout.custom_widget_layout).apply{

                // Open App on Widget Click
                val pendingIntent = HomeWidgetLaunchIntent.getActivity(context, MainActivity::class.java)
                setOnClickPendingIntent(R.id.widget_container, pendingIntent)


                val title =widgetData.getString("title", null)
                val pathImage = widgetData.getString("pathImage", null)



//              val titleData = JSONObject(title)
                println(title)

//              val  titleModel :TitleModel =TitleModel(
//                  titleData.getString("name"),titleData.getString("age"))

                print(title)
                print(pathImage)

                val backgroundIntent = HomeWidgetBackgroundIntent.getBroadcast(context, Uri.parse("homeWidgetExample://titleClicked"))
                setOnClickPendingIntent(R.id.widget_title, backgroundIntent)
                setTextViewText(R.id.widget_title, title ?: "No Title Set")


                val pendingIntentWithData = HomeWidgetLaunchIntent.getActivity(
                    context,
                    MainActivity::class.java,
                    Uri.parse("homeWidgetExample://message?message"))

                setOnClickPendingIntent(R.id.widget_message, pendingIntentWithData)

                setTextViewText(R.id.widget_message, pathImage ?: "No message Set")

            }




//            itemWidgetAdapter = ItemWidgetAdapter(context, arrayList)
//            viewPager.adapter = itemWidgetAdapter

            appWidgetManager.updateAppWidget(widgetId, views)


        }

    }
}