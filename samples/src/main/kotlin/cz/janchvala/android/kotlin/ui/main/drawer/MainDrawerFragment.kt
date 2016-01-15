package cz.janchvala.android.kotlin.ui.main.drawer

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.bindView
import com.bumptech.glide.Glide
import cz.janchvala.android.kotlin.R
import cz.janchvala.android.kotlin.ui.AbstractFragment
import cz.janchvala.android.kotlin.ui.util.ext.inflate
import jp.satorufujiwara.binder.Section
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter

public class MainDrawerFragment : AbstractFragment() {

    companion object {
        private const val IMAGE_URL = "https://raw.githubusercontent.com/satorufujiwara/kotlin-android-example/master/art/header_image.jpg"
    }

    val recyclerView: RecyclerView by bindView(R.id.recyclerView)
    val headerImage: ImageView by bindView(R.id.headerImage)
    val headerLayout: View by bindView(R.id.headerLayout)
    val adapter: RecyclerBinderAdapter<MainDrawerSection, MainDrawerViewType> = RecyclerBinderAdapter()
    val scrollListener: OnScrollListener by lazy { OnScrollListener(headerLayout) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflate(R.layout.main_drawer_fragment, inflater, container)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addOnScrollListener(scrollListener)

        adapter.add(MainDrawerSection.HEADER, MainDrawerTransparentBinder(activity))
        adapter.add(MainDrawerSection.NAVIGATION, MainDrawerNavigationBinder(activity,
                getString(R.string.main_drawer_navigation_home)))
        for (i in 0..10) {
            adapter.add(MainDrawerSection.NAVIGATION, MainDrawerNavigationBinder(activity,
                    "Menu : " + i))
        }
        Glide.with(this).load(IMAGE_URL).into(headerImage)
    }

    override fun onDestroyView() {
        recyclerView.removeOnScrollListener(scrollListener)
        super.onDestroyView()
    }

    enum class MainDrawerSection : Section {
        HEADER,
        NAVIGATION;

        override fun position(): Int = ordinal
    }

    class OnScrollListener(val headerLayout: View) : RecyclerView.OnScrollListener() {
        var y: Int = 0
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            y += dy
            if (y > headerLayout.height) {
                return
            }
            headerLayout.translationY = (-y / 2).toFloat()
        }
    }
}
