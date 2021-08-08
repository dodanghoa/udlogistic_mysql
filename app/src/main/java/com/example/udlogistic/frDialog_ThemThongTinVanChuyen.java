package com.example.udlogistic;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.udlogistic.database.FireBaseManage;
import com.example.udlogistic.model.KhachHang;
import com.example.udlogistic.model.NhanVien;
import com.example.udlogistic.model.PhongBan;
import com.example.udlogistic.model.ThongTinVanChuyen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;


public class frDialog_ThemThongTinVanChuyen extends DialogFragment {
    FireBaseManage fireBaseManage;
    ThongTinVanChuyen thongTinVanChuyen;
    public  interface OnInputSelected{
        void setInputUpdate(ThongTinVanChuyen thongTinVanChuyen);
        void setInput(ThongTinVanChuyen thongTinVanChuyen);

    }
    ArrayList<NhanVien>nhanViens = new ArrayList<>();
    ArrayList<KhachHang>khachHangs = new ArrayList<>();
    public  OnInputSelected onInputSelected;
    View view;
    static final String TAG = "frDialog_ThemThongTinVanChuyen";
    Spinner spNhanVien ,spKhachHang;
    EditText edtSoXe,edtSoToKhai,edtNgayDiGiao,edtNoiLayCong,edtDonGia,edtPhiCauDuong,edtNeoXe,edtTamUng,edtsoBill,edtsoContainer
            ,edtNoiDongHang,edtNhienLieu,edtPhiNangCong,edtLuongTheoChuyen;
    Button btnClose,btnSave;

    public frDialog_ThemThongTinVanChuyen(ThongTinVanChuyen thongTinVanChuyen) {
        // Required empty public constructor
      this. thongTinVanChuyen = thongTinVanChuyen ;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment frDialog_ThemKhachHang.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void loadDataKhachHang( ) {
        fireBaseManage.childKhachHang.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                khachHangs.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    KhachHang khachHang = postSnapshot.getValue(KhachHang.class);
                    khachHangs.add(khachHang);
                }
                ArrayList<String> spinnerArray = new ArrayList<>();
                khachHangs.forEach(khachHang -> {
                    spinnerArray.add(khachHang.getHoTen());
                });
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_item,
                                spinnerArray); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                spKhachHang.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void loadDataNhanVien( ) {
        fireBaseManage.childNhanVien.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nhanViens.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    NhanVien nhanVien = postSnapshot.getValue(NhanVien.class);
                    nhanViens.add(nhanVien);
                }
                ArrayList<String> spinnerArray = new ArrayList<>();
                nhanViens.forEach(phongBan -> {
                    spinnerArray.add(phongBan.getHoTen());
                });
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_item,
                                spinnerArray); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                spNhanVien.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void checkedSpinnerNhanVien(String s ) {
        fireBaseManage.childNhanVien.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<NhanVien>nhanViens = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    NhanVien nhanVien = postSnapshot.getValue(NhanVien.class);
                    nhanViens.add(nhanVien);
                }
                nhanViens.forEach(o -> {
                    if (o.getMaNhanVien().equals(s))
                    {
                        spNhanVien.setSelection(nhanViens.indexOf(o));
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void checkedSpinnerKhacHang(String s ) {
        fireBaseManage.childKhachHang.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<KhachHang>khachHangs = new ArrayList<>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    KhachHang khachHang = postSnapshot.getValue(KhachHang.class);
                    khachHangs.add(khachHang);
                }
                khachHangs.forEach(o -> {
                    if (o.getMaKhachHang().equals(s))
                    {
                        spKhachHang.setSelection(khachHangs.indexOf(o));
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fireBaseManage = new FireBaseManage();
        view =  inflater.inflate(R.layout.fragment_fr_dialog__them_thong_tin_van_chuyen, container, false);
        spKhachHang = view.findViewById(R.id.spKhachHang);
        spNhanVien = view.findViewById(R.id.spNhanVien);
        edtSoXe = view.findViewById(R.id.edtSoXe);
        edtSoToKhai = view.findViewById(R.id.edtSoToKhai);
        edtNgayDiGiao = view.findViewById(R.id.edtNgayDiGiao);
        edtNoiLayCong = view.findViewById(R.id.edtNoiLayCong);
        edtDonGia = view.findViewById(R.id.edtDonGia);
        edtPhiCauDuong = view.findViewById(R.id.edtPhiCauDuong);
        edtNeoXe = view.findViewById(R.id.edtNeoXe);
        edtTamUng = view.findViewById(R.id.edtTamUng);
        edtsoBill = view.findViewById(R.id.edtSoBill);
        edtNoiDongHang = view.findViewById(R.id.edtnoiDongHang);
        edtNhienLieu = view.findViewById(R.id.edtNhienLieu);
        edtPhiNangCong = view.findViewById(R.id.edtPhiNangCong);
        edtLuongTheoChuyen = view.findViewById(R.id.edtLuongTheoChuyen);
        edtsoContainer = view.findViewById(R.id.edtSoContainer);
        btnClose = view.findViewById(R.id.btnClose);
        btnSave = view.findViewById(R.id.btnSave);

        loadDataNhanVien();
        loadDataKhachHang();
        //Setevent
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        if (thongTinVanChuyen!= null)
        {   checkedSpinnerNhanVien(thongTinVanChuyen.getNhanVien().getMaNhanVien());
            checkedSpinnerKhacHang(thongTinVanChuyen.getKhachHang().getMaKhachHang());
            edtSoXe.setText(thongTinVanChuyen.getSoXe());
            edtLuongTheoChuyen.setText(thongTinVanChuyen.getLuongTheoChuyen());
            edtPhiNangCong.setText(thongTinVanChuyen.getPhiNangCong());
            edtNhienLieu.setText(thongTinVanChuyen.getNhienLieu());
            edtNoiDongHang.setText(thongTinVanChuyen.getNoiDongHang());
            edtsoContainer.setText(thongTinVanChuyen.getSoConTainer());
            edtsoBill.setText(thongTinVanChuyen.getSoBill());
            edtTamUng.setText(thongTinVanChuyen.getTamUng());
            edtNeoXe.setText(thongTinVanChuyen.getNeoXe());
            edtPhiCauDuong.setText(thongTinVanChuyen.getPhiCauDuong());
            edtNoiLayCong.setText(thongTinVanChuyen.getNoiLayCong());
            edtDonGia.setText(thongTinVanChuyen.getDonGia());
            edtSoToKhai.setText(thongTinVanChuyen.getSoToKhai());
            edtNgayDiGiao.setText(thongTinVanChuyen.getNgayDiGiaoHang());

        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check())
                {
                    if (thongTinVanChuyen==null)
                    {   String ma = UUID.randomUUID().toString();;
                        NhanVien nhanVien =  nhanViens.get(spNhanVien.getSelectedItemPosition());
                        KhachHang khachHang =  khachHangs.get(spKhachHang.getSelectedItemPosition());
                        String SoXe = edtSoXe.getText().toString();
                        String LuongTheoChuyen =edtLuongTheoChuyen.getText().toString();
                        String PhiNangCong = edtPhiNangCong.getText().toString();
                        String NhienLieu =edtNhienLieu.getText().toString();
                        String NoiDongHang =edtNoiDongHang.getText().toString();
                        String soConTainer =edtsoContainer.getText().toString();
                        String soBill = edtsoBill.getText().toString();
                        String TamUng = edtTamUng.getText().toString();
                        String NeoXe =edtNeoXe.getText().toString();
                        String PhiCauDuong = edtPhiCauDuong.getText().toString();
                        String NoiLayCong = edtNoiLayCong.getText().toString();
                        String DonGia = edtDonGia.getText().toString();
                        String SoToKhai = edtSoToKhai.getText().toString();
                        String NgayDiGiao = edtNgayDiGiao.getText().toString();

                        onInputSelected.setInput(
                                new ThongTinVanChuyen(ma,SoXe, SoToKhai,
                                         NgayDiGiao, NoiLayCong,  DonGia,  PhiCauDuong,
                                         NeoXe,  TamUng,soBill  ,  soConTainer,  NoiDongHang,
                                         NhienLieu,  PhiNangCong,  LuongTheoChuyen,  khachHang,
                                         nhanVien));
                        Toast.makeText(view.getContext(),"Đã thêm Thông tin vận chuyển thành công" , Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();

                    }else
                    {
                        String ma = thongTinVanChuyen.getMaThongTinVanChuyen();;
                        NhanVien nhanVien =  nhanViens.get(spNhanVien.getSelectedItemPosition());
                        KhachHang khachHang =  khachHangs.get(spKhachHang.getSelectedItemPosition());
                        String SoXe = edtSoXe.getText().toString();
                        String LuongTheoChuyen =edtLuongTheoChuyen.getText().toString();
                        String PhiNangCong = edtPhiNangCong.getText().toString();
                        String NhienLieu =edtNhienLieu.getText().toString();
                        String NoiDongHang =edtNoiDongHang.getText().toString();
                        String soConTainer =edtsoContainer.getText().toString();
                        String soBill = edtsoBill.getText().toString();
                        String TamUng = edtTamUng.getText().toString();
                        String NeoXe =edtNeoXe.getText().toString();
                        String PhiCauDuong = edtPhiCauDuong.getText().toString();
                        String NoiLayCong = edtNoiLayCong.getText().toString();
                        String DonGia = edtDonGia.getText().toString();
                        String SoToKhai = edtSoToKhai.getText().toString();
                        String NgayDiGiao = edtNgayDiGiao.getText().toString();

                        onInputSelected.setInputUpdate(
                                new ThongTinVanChuyen(ma,SoXe, SoToKhai,
                                        NgayDiGiao, NoiLayCong,  DonGia,  PhiCauDuong,
                                        NeoXe,  TamUng,soBill  ,  soConTainer,  NoiDongHang,
                                        NhienLieu,  PhiNangCong,  LuongTheoChuyen,  khachHang,
                                        nhanVien));
                        Toast.makeText(view.getContext(),"Đã sửa Thông tin vận chuyển thành công" , Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }
                }
            }
        });
        return view;
    }

    private boolean Check() {
        boolean res = true;
        if(edtsoContainer.getText().toString().isEmpty())
        {
            res = false;
            edtsoContainer.setError("Vui lòng nhập số container");
        }
        if(edtNgayDiGiao.getText().toString().isEmpty())
        {
            res = false;
            edtNgayDiGiao.setError("Vui lòng nhập ngày đi giao");
        }
        if(edtLuongTheoChuyen.getText().toString().isEmpty())
        {
            res = false;
            edtLuongTheoChuyen.setError("Vui lòng nhập lương");
        }
        if(edtPhiNangCong.getText().toString().isEmpty())
        {
            res = false;
            edtPhiNangCong.setError("Vui lòng nhập phí nâng công");
        }
        if(edtNhienLieu.getText().toString().isEmpty())
        {
            res = false;
            edtNhienLieu.setError("Vui lòng nhập phí nhiên liệu");
        }
        if(edtNoiDongHang.getText().toString().isEmpty())
        {
            res = false;
            edtLuongTheoChuyen.setError("Vui lòng nhập nơi đóng hàng");
        }
        if(edtsoBill.getText().toString().isEmpty())
        {
            res = false;
            edtsoBill.setError("Vui lòng nhập số bill");
        }
        if(edtTamUng.getText().toString().isEmpty())
        {
            res = false;
            edtTamUng.setError("Vui lòng nhập lương tạm ứng");
        }
        if(edtNeoXe.getText().toString().isEmpty())
        {
            res = false;
            edtNeoXe.setError("Vui lòng nhập lương phí neo xe");
        }
        if(edtPhiCauDuong.getText().toString().isEmpty())
        {
            res = false;
            edtPhiCauDuong.setError("Vui lòng nhập phí cầu đường");
        }if(edtNoiLayCong.getText().toString().isEmpty())
        {
            res = false;
            edtNoiLayCong.setError("Vui lòng nhập nơi láy công");
        }if(edtDonGia.getText().toString().isEmpty())
        {
            res = false;
            edtLuongTheoChuyen.setError("Vui lòng nhập đơn giá");
        }if(edtSoToKhai.getText().toString().isEmpty())
        {
            res = false;
            edtSoToKhai.setError("Vui lòng nhập số tờ khai");
        }if(edtNgayDiGiao.getText().toString().isEmpty())
        {
            res = false;
            edtNgayDiGiao.setError("Vui lòng nhập ngày đi giao");
        }
        return  res;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onInputSelected = (OnInputSelected)getTargetFragment();
    }
}