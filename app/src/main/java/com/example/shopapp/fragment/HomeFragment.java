package com.example.shopapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopapp.R;
import com.example.shopapp.apdapter.ProductAdapter;
import com.example.shopapp.model.Product;
import com.example.shopapp.viewmodel.ProductViewModel;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;
    private List<Product> productList = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.product_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
//        productAdapter = new ProductAdapter();
        productAdapter = new ProductAdapter(productList, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                // Handle the item click here
                ProductDetailFragment detailFragment = new ProductDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selected_product", product);
                detailFragment.setArguments(bundle);

                requireFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerView.setAdapter(productAdapter);
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productList.clear();
                productList.addAll(products);
                productAdapter.notifyDataSetChanged();
            }
        });



        Log.d("HomeFragment", "Data received: " + productAdapter.getProductCount() + " products");
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), this::onProductsReceived);

        return view;
    }
    private void onProductsReceived(List<Product> products) {
        productAdapter.setProducts(products);
        Log.d("HomeFragment", "Received " + products.size() + " products");
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Khởi tạo và thiết lập các thành phần UI tại đây (nếu cần)
    }
}