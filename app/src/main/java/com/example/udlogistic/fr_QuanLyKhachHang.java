package com.example.udlogistic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.udlogistic.adapter.Adapter_LvDanhSachKhachHang;
import com.example.udlogistic.database.MySQL_Manage;
import com.example.udlogistic.model.KhachHang;

import java.util.ArrayList;


public class fr_QuanLyKhachHang extends Fragment implements frDialog_ThemKhachHang.OnInputSelected {



    static final String TAG = "fr_QuanLyKhachHang";
    SearchView searchView;
    ListView lvDanhSachKhachHang;
    View view;
    TextView txtHoTen;
    Adapter_LvDanhSachKhachHang adapter_lvDanhSachKhachHang;
    ArrayList<KhachHang>khachHangs = new ArrayList<KhachHang>();
    Button btnThemKhachHang;
    MySQL_Manage mySQL_manage;
    public fr_QuanLyKhachHang() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setControl() {
        lvDanhSachKhachHang = view.findViewById(R.id.lvDanhSachKhachHang);
        btnThemKhachHang = view.findViewById(R.id.btnThemKhachHang);
        txtHoTen = view.findViewById(R.id.txtHoTen);
        searchView = view.findViewById(R.id.searchView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fr__quan_ly_khach_hang, container, false);

        mySQL_manage = new MySQL_Manage();
        setControl();
        loadData();
        setEvent();
        return view;
    }

    private void loadData() {

        khachHangs = mySQL_manage .getKhachHang();
        adapter_lvDanhSachKhachHang = new Adapter_LvDanhSachKhachHang(view.getContext(),R.layout.lvkhachhang_item,khachHangs);
        adapter_lvDanhSachKhachHang.setFragmentManage(getFragmentManager());
        adapter_lvDanhSachKhachHang.setfr_QuanLyKhachHang(fr_QuanLyKhachHang.this);
        lvDanhSachKhachHang.setAdapter(adapter_lvDanhSachKhachHang);
    }

    private void setEvent() {
        btnThemKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frDialog_ThemKhachHang frDialog_themKhachHang = new frDialog_ThemKhachHang(null);
                if (getFragmentManager() != null) {
                    frDialog_themKhachHang.setTargetFragment(fr_QuanLyKhachHang.this,1);
                    frDialog_themKhachHang.show(getFragmentManager(),"frDialog_ThemKhachHang");
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_lvDanhSachKhachHang.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_lvDanhSachKhachHang.getFilter().filter(newText);
                return true;
            }
        });
    }


    @Override
    public void setInput(KhachHang khachHang) {
        mySQL_manage.writeKhachHang(khachHang);
        adapter_lvDanhSachKhachHang.notifyDataSetChanged();
        loadData();
    }
    @Override
    public void setInputUpdate(KhachHang khachHang) { ;
                mySQL_manage.updateKhachHang(khachHang);
                adapter_lvDanhSachKhachHang.notifyDataSetChanged();
        loadData();
    }
    }



