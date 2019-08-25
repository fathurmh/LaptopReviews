package com.digitalent.laptopreviews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.androidadvance.topsnackbar.TSnackbar;
import com.digitalent.laptopreviews.DetailActivity;
import com.digitalent.laptopreviews.R;
import com.digitalent.laptopreviews.adapter.CardViewLaptopAdapter;
import com.digitalent.laptopreviews.adapter.ListViewLaptopAdapter;
import com.digitalent.laptopreviews.model.Laptop;
import com.digitalent.laptopreviews.model.LaptopData;
import com.digitalent.laptopreviews.utils.ConnectivityHelper;
import com.digitalent.laptopreviews.utils.Constants;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String STATE_LIST_LAPTOP = "state_list_laptop";
    private static final String STATE_SAVED_STATE = "state_saved_state";
    private static final String STATE_SELECTED_MENU = "state_selected_menu";
    private static Bundle savedState;

    private SwipeRefreshLayout swipeLayout;
    private ArrayList<Laptop> listLaptop;
    private RecyclerView rvLaptops;
    private LinearLayoutManager mLayoutManager;
    private int selectedMenu;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        listLaptop = new ArrayList<>();
        selectedMenu = R.id.action_card_view;

        view = inflater.inflate(R.layout.fragment_home, container, false);
        rvLaptops = view.findViewById(R.id.rv_laptops);
        mLayoutManager = new LinearLayoutManager(view.getContext());

        swipeLayout = view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        if(savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle(STATE_SAVED_STATE);
        }
        checkSavedState();
        GetLaptopData();

        return view;
    }

    @Override
    public void onRefresh() {
        checkSavedState();

        if (ConnectivityHelper.isConnectedToNetwork(view.getContext())) {
            listLaptop.clear();
            new LaptopData(view.getContext(), this, listLaptop, selectedMenu).execute();
        } else {
            TSnackbar.make(getActivity().findViewById(R.id.fragment_container), "Internet Connection Not Available.", TSnackbar.LENGTH_LONG).show();
        }

        swipeLayout.setRefreshing(false);
    }

    private void checkSavedState() {
        if (savedState != null) {
            listLaptop = (ArrayList<Laptop>) savedState.getSerializable(STATE_LIST_LAPTOP);
            selectedMenu = savedState.getInt(STATE_SELECTED_MENU);
        }
        savedState = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.app_name);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState();
        listLaptop = null;
        selectedMenu = 0;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(STATE_SAVED_STATE, (savedState != null) ? savedState : saveState());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.view_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        selectedMenu = menuItem.getItemId();
        setMode(menuItem.getItemId());
        return super.onOptionsItemSelected(menuItem);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list_view:
                showRecyclerList();
                break;
            case R.id.action_card_view:
                showRecyclerCard();
                break;
        }
    }

    private void GetLaptopData() {
        if (ConnectivityHelper.isConnectedToNetwork(view.getContext())) {
            if (listLaptop.size() == 0) {
                new LaptopData(view.getContext(), this, listLaptop, selectedMenu).execute();
            } else {
                setMode(selectedMenu);
            }
        } else {
            TSnackbar.make(getActivity().findViewById(R.id.fragment_container), "Internet Connection Not Available.", TSnackbar.LENGTH_LONG).show();
        }
    }

    private void showRecyclerList(){
        rvLaptops.setLayoutManager(mLayoutManager);
        ListViewLaptopAdapter listViewLaptopAdapter = new ListViewLaptopAdapter(listLaptop);
        rvLaptops.setAdapter(listViewLaptopAdapter);

        listViewLaptopAdapter.setOnItemClickCallback(new ListViewLaptopAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Laptop data) {
                showSelectedLaptop(data);
            }
        });
    }

    private void showRecyclerCard(){
        rvLaptops.setLayoutManager(mLayoutManager);
        CardViewLaptopAdapter cardViewLaptopAdapter = new CardViewLaptopAdapter(listLaptop);
        rvLaptops.setAdapter(cardViewLaptopAdapter);

        cardViewLaptopAdapter.setOnItemClickCallback(new CardViewLaptopAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Laptop data) {
                showSelectedLaptop(data);
            }
        });
    }

    private void showSelectedLaptop(Laptop data) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.STATE_LAPTOP, data);
        startActivity(intent);
    }

    private Bundle saveState() {
        Bundle state = new Bundle();
        state.putSerializable(STATE_LIST_LAPTOP, listLaptop);
        state.putInt(STATE_SELECTED_MENU, selectedMenu);
        return state;
    }
}
