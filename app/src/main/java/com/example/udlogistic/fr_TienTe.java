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

import com.example.udlogistic.adapter.Adapter_LvDanhSachNhanVien;
import com.example.udlogistic.adapter.Adapter_LvDanhSachThongTinVanChuyen;
import com.example.udlogistic.database.FireBaseManage;
import com.example.udlogistic.model.NhanVien;
import com.example.udlogistic.model.ThongTinVanChuyen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fr_TienTe extends Fragment implements frDialog_ThemThongTinVanChuyen.OnInputSelected {

    FireBaseManage fireBaseManage;

    static final String TAG = "fr_TienTe";
    SearchView searchView;
    ListView lvDanhSachNhanVien;
    View view;
    TextView txtHoTen;
    Adapter_LvDanhSachThongTinVanChuyen adapter_lvDanhSachThongTinVanChuyen;
    ArrayList<ThongTinVanChuyen> thongTinVanChuyens = new ArrayList<ThongTinVanChuyen>();
    Button btnThemThongTin;
    public fr_TienTe() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setControl() {
        lvDanhSachNhanVien = view.findViewById(R.id.lvDanhSachThongTinVanChuyen);
        btnThemThongTin = view.findViewById(R.id.btnThemThongTin);
        txtHoTen = view.findViewById(R.id.txtHoTen);
        searchView = view.findViewById(R.id.searchView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fr__tien_te, container, false);
        fireBaseManage = new FireBaseManage();
        setControl();
        loadData();
        setEvent();
        return view;
    }

    private void loadData() {

        fireBaseManage.childThongTinVanChuyen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                thongTinVanChuyens.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ThongTinVanChuyen thongTinVanChuyen = postSnapshot.getValue(ThongTinVanChuyen.class);
                    thongTinVanChuyens.add(thongTinVanChuyen);
                }
                adapter_lvDanhSachThongTinVanChuyen.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setEvent() {
        adapter_lvDanhSachThongTinVanChuyen = new Adapter_LvDanhSachThongTinVanChuyen(view.getContext(),R.layout.lvthongtinvanchuyen_item,thongTinVanChuyens);
        adapter_lvDanhSachThongTinVanChuyen.setFragmentManage(getFragmentManager());
        adapter_lvDanhSachThongTinVanChuyen.setffr_TienTe(fr_TienTe.this);
        lvDanhSachNhanVien.setAdapter(adapter_lvDanhSachThongTinVanChuyen);
        btnThemThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frDialog_ThemThongTinVanChuyen frDialog_themThongTinVanChuyen = new frDialog_ThemThongTinVanChuyen(null);
                if (getFragmentManager() != null) {
                    frDialog_themThongTinVanChuyen.setTargetFragment(fr_TienTe.this,1);
                    frDialog_themThongTinVanChuyen.show(getFragmentManager(),"frDialog_ThemThongTinVanChuyen");
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_lvDanhSachThongTinVanChuyen.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_lvDanhSachThongTinVanChuyen.getFilter().filter(newText);
                return true;
            }
        });
    }




    @Override
    public void setInputUpdate(ThongTinVanChuyen thongTinVanChuyen) {
        fireBaseManage.writeThongTinVanChuyen(thongTinVanChuyen);
    }

    @Override
    public void setInput(ThongTinVanChuyen thongTinVanChuyen) {
        fireBaseManage.writeThongTinVanChuyen(thongTinVanChuyen);
    }
}