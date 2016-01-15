package cz.janchvala.android.kotlin.ui.main

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView
import cz.janchvala.android.kotlin.R
import cz.janchvala.android.kotlin.data.api.dto.Repo
import cz.janchvala.android.kotlin.data.repository.GitHubRepository
import cz.janchvala.android.kotlin.ui.AbstractFragment
import cz.janchvala.android.kotlin.ui.util.ext.inflate
import jp.satorufujiwara.binder.Section
import jp.satorufujiwara.binder.recycler.RecyclerBinderAdapter
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

public class MainFragment : AbstractFragment() {

    companion object {
        @JvmStatic fun newInstance() = MainFragment()
    }

    val recyclerView: RecyclerView by bindView(R.id.recyclerView)
    val adapter: RecyclerBinderAdapter<MainSection, MainViewType> = RecyclerBinderAdapter()
    @Inject lateinit var gitHubRepository: GitHubRepository

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        MainComponent.Initializer.init(activity as MainActivity).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflate(R.layout.main_fragment, inflater, container)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        gitHubRepository.getRepos("JanChvala")
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle<Repo>())
                .subscribe({
                    adapter.add(MainSection.CONTENTS, MainRepoBinder(activity, it))
                }, {
                    Timber.e(it, "error.")
                }, {
                    adapter.notifyDataSetChanged()
                })
    }

    override fun onDestroyView() {
        adapter.clear()
        super.onDestroyView()
    }

    enum class MainSection : Section {
        CONTENTS;

        override fun position(): Int = ordinal
    }
}
