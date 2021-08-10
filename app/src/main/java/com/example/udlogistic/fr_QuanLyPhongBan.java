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

import com.developer.kalert.KAlertDialog;
import com.example.udlogistic.adapter.Adapter_LvDanhSachPhongBan;
import com.example.udlogistic.database.MySQL_Manage;
import com.example.udlogistic.model.PhongBan;

import java.util.ArrayList;


public class fr_QuanLyPhongBan extends Fragment implements frDialog_ThemPhongBan.OnInputSelected {
    KAlertDialog pDialog ;

    static final String TAG = "fr_QuanLyPhongBan";
    SearchView searchView;
    ListView lvDanhSachKhachHang;
    View view;
    TextView txtHoTen;
    Adapter_LvDanhSachPhongBan adapter_lvDanhSachPhongBan;
    ArrayList<PhongBan> phongBans = new ArrayList<PhongBan>();
    Button btnThemPhongBan;
    MySQL_Manage mySQL_manage;
    public fr_QuanLyPhongBan() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setControl() {
        lvDanhSachKhachHang = view.findViewById(R.id.lvDanhSachPhongBan);
        btnThemPhongBan = view.findViewById(R.id.btnThemPhongBan);
        txtHoTen = view.findViewById(R.id.txtHoTen);
        searchView = view.findViewById(R.id.searchView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fr__quan_ly_phong_ban, container, false);
        mySQL_manage = new MySQL_Manage();
        setControl();
        setEvent();
        loadData();
        return view;
    }

    private void loadData() {
        phongBans = mySQL_manage.getPhongBan();
        adapter_lvDanhSachPhongBan = new Adapter_LvDanhSachPhongBan(view.getContext(),R.layout.lvkhachhang_item,phongBans);
        adapter_lvDanhSachPhongBan.setFragmentManage(getFragmentManager());
        adapter_lvDanhSachPhongBan.setfr_QuanLyKhachHang(fr_QuanLyPhongBan.this);
        lvDanhSachKhachHang.setAdapter(adapter_lvDanhSachPhongBan);
    }
    private void setEvent() {
        btnThemPhongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frDialog_ThemPhongBan frDialogThemPhongBan = new frDialog_ThemPhongBan(null);
                if (getFragmentManager() != null) {
                    frDialogThemPhongBan.setTargetFragment(fr_QuanLyPhongBan.this,1);
                    frDialogThemPhongBan.show(getFragmentManager(),"frDialog_ThemPhongBan");
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_lvDanhSachPhongBan.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_lvDanhSachPhongBan.getFilter().filter(newText);
                return true;
            }
        });
    }


    @Override
    public void setInput(PhongBan phongBan) {
        mySQL_manage.writePhongBan(phongBan);
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void setInputUpdate(PhongBan phongBan) { ;
        mySQL_manage.updatePhongBan(phongBan);
        loadData();
    }

}