package com.udhipe.musemviewer.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.udhipe.musemviewer.R
import com.udhipe.musemviewer.data.collection.CollectionRepository
import com.udhipe.musemviewer.databinding.FragmentHomeBinding
import com.udhipe.musemviewer.ui.detail.DetailActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(CollectionRepository.instance)
    }

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var adapter: CollectionAdapter

    private val onItemCallBack = object : CollectionAdapter.OnItemClickCallback {
        override fun onItemClick(objectNumber: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.OBJECT_NUMBER, objectNumber)
            startActivity(intent)
        }
    }

//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)

        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewModel.getCollectionList(1)
        viewModel.apply {
            viewState.observe(
                viewLifecycleOwner, Observer {
                    if (it != null) {
                        if (it.error == null) {
                            val data = it.data
                            if (data != null) {
                                adapter.updateData(data)
                            }
                        }
                    }
                }
            )
        }
    }

    private fun setAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = CollectionAdapter(onItemCallBack)
        homeBinding.rvMuseumCollection.layoutManager = linearLayoutManager
        homeBinding.rvMuseumCollection.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}