package com.kotlinexample.moviesapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlinexample.moviesapp.R
import com.kotlinexample.moviesapp.adapters.CategoryAdapter
import com.kotlinexample.moviesapp.databinding.FragmentCategoryBinding
import com.kotlinexample.moviesapp.models.CategoryData
import java.util.Locale


class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private var categoryList = ArrayList<CategoryData>()
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val navController = findNavController()

        binding.categoryRecyclerView.setHasFixedSize(true)
        binding.categoryRecyclerView.layoutManager= LinearLayoutManager (requireContext())

        addDataToList()
        adapter = CategoryAdapter(categoryList)
        binding.categoryRecyclerView.adapter= adapter




        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

        return binding.root
    }
    private fun filterList(query: String?) {

            if (query != null) {
                val filteredList = ArrayList<CategoryData>()
                for (i in categoryList) {
                    if (i.categoryTitle.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(i)
                    }
                }

                if (filteredList.isEmpty()) {
                    Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
                } else {
                    adapter.setFilteredList(filteredList)
                }
            }
        }
    private fun addDataToList() {
        categoryList.add(CategoryData("Action", R.drawable.ic_ninja))
        categoryList.add(CategoryData("Romance", R.drawable.ic_romance))
        categoryList.add(CategoryData("Fantasy", R.drawable.ic_fantasy))
        categoryList.add(CategoryData("Horror", R.drawable.ic_horror))
        categoryList.add(CategoryData("Sci-Fi", R.drawable.ic_scifi))
        categoryList.add(CategoryData("Adventure", R.drawable.ic_adventure))
        categoryList.add(CategoryData("Comedy", R.drawable.ic_comedy))
        categoryList.add(CategoryData("Drama", R.drawable.ic_drama))

    }

}