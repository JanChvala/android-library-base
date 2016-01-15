package cz.janchvala.android.kotlin.ui.main.drawer

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.bindView
import cz.janchvala.android.kotlin.R
import jp.satorufujiwara.binder.recycler.RecyclerBinder

public class MainDrawerNavigationBinder(activity: Activity, val text: String)
: RecyclerBinder<MainDrawerViewType>(activity, MainDrawerViewType.NAVIGATION) {

    override fun layoutResId(): Int = R.layout.main_drawer_navigation_binder

    override fun onCreateViewHolder(view: View?): RecyclerView.ViewHolder? = ViewHolder(view)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as ViewHolder
        holder.textView.text = text
        holder.rootLayout.setOnClickListener {}
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val rootLayout: RelativeLayout = itemView as RelativeLayout
        val textView: TextView by bindView(R.id.textView)
    }
}


