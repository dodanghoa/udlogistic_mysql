package com.example.udlogistic.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.udlogistic.R;
import com.example.udlogistic.database.MySQL_Manage;
import com.example.udlogistic.frDialog_ThemThongTinVanChuyen;
import com.example.udlogistic.fr_TienTe;
import com.example.udlogistic.model.ThongTinVanChuyen;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Adapter_LvDanhSachThongTinVanChuyen extends ArrayAdapter implements Filterable {
    Context context; int resource;
    ArrayList<ThongTinVanChuyen>thongTinVanChuyens = new ArrayList<>();
    ArrayList<ThongTinVanChuyen>source = new ArrayList<>();
    FragmentManager fragmentManager;
    fr_TienTe fr_tienTe;
    int tam =0;
    MySQL_Manage mySQL_manage = new MySQL_Manage();
    LinearLayout linearLayout;
    public Adapter_LvDanhSachThongTinVanChuyen(@NonNull Context context, int resource, @NonNull ArrayList<ThongTinVanChuyen>thongTinVanChuyens) {
        super(context, resource, thongTinVanChuyens);
        this.context = context;
        this.resource = resource;
        this.thongTinVanChuyens = thongTinVanChuyens;
        this.source = thongTinVanChuyens;
    }
    public  void setFragmentManage( FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }
    public  void setffr_TienTe(fr_TienTe fr_tienTe)
    {
        this.fr_tienTe = fr_tienTe;
    }
    @Override
    public int getCount() {
        return thongTinVanChuyens.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    thongTinVanChuyens = source;
                } else {
                    ArrayList<ThongTinVanChuyen> list = new ArrayList<>();
                    for (ThongTinVanChuyen thongTinVanChuyen : source) {
                        if (thongTinVanChuyen.getKhachHang().getHoTen().toLowerCase().contains(strSearch.toLowerCase())||
                                thongTinVanChuyen.getNhanVien().getHoTen().toLowerCase().contains(strSearch.toLowerCase())||
                                thongTinVanChuyen.getSoBill().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(thongTinVanChuyen);
                        }
                    }
                    thongTinVanChuyens = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = thongTinVanChuyens;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                thongTinVanChuyens = (ArrayList<ThongTinVanChuyen>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.lvthongtinvanchuyen_item,null);
        //Nạp layout
        TextView txtSTT = view.findViewById(R.id.txtSTT);
        TextView  txtKhachHang = view.findViewById(R.id.txtKhachHang);
        TextView txtNhanVien = view.findViewById(R.id.txtNhanVien);
        TextView txtSoToKhai = view.findViewById(R.id.txtSoToKhai);
        TextView txtNgayDiGiao = view.findViewById(R.id.txtNgayDiGiao);
        TextView txtNoiLayCong = view.findViewById(R.id.txtNoiLayCong);
        TextView txtDonGia = view.findViewById(R.id.txtDonGia);
        TextView txtPhiCauDuong = view.findViewById(R.id.txtPhíCauDuong);
        TextView txtNeoXe = view.findViewById(R.id.txtNeoXe);
        TextView txtTamUng = view.findViewById(R.id.txtTamUng);
        TextView txtsoBill = view.findViewById(R.id.txtSoBill);
        TextView txtNoiDongHang = view.findViewById(R.id.txtNoiDongHang);
        TextView txtNhienLieu = view.findViewById(R.id.txtNhienLieu);
        TextView txtPhiNangCong = view.findViewById(R.id.txtPhiNangCong);
        TextView txtLuongTheoChuyen = view.findViewById(R.id.txtLuongTheoChuyen);
        TextView txtsoContainer = view.findViewById(R.id.txtSoLuong);
        TextView txtsoXe = view.findViewById(R.id.txtSoXe);
        TextView txtTongLoiNhuan = view.findViewById(R.id.txtTongLoiNhuan);

        LinearLayout ln = view.findViewById(R.id.linearInfo);
        Button btnSua = view.findViewById(R.id.btnSua);
        ThongTinVanChuyen thongTinVanChuyen = thongTinVanChuyens.get(position);
        //Nạp dữ liệu
        if (thongTinVanChuyen == null) {
            return null;
        }
        txtSTT.setText((position+1)+"");
        txtKhachHang.setText(thongTinVanChuyen.getKhachHang().getHoTen());
        txtNhanVien.setText(thongTinVanChuyen.getNhanVien().getHoTen());
        txtsoXe.setText(thongTinVanChuyen.getSoXe());
        txtLuongTheoChuyen.setText(thongTinVanChuyen.getLuongTheoChuyen());
        txtPhiNangCong.setText(thongTinVanChuyen.getPhiNangCong());
        txtNhienLieu.setText(thongTinVanChuyen.getNhienLieu());
        txtNoiDongHang.setText(thongTinVanChuyen.getNoiDongHang());
        txtsoContainer.setText(thongTinVanChuyen.getSoConTainer());
        txtsoBill.setText(thongTinVanChuyen.getSoBill());
        txtTamUng.setText(thongTinVanChuyen.getTamUng());
        txtNeoXe.setText(thongTinVanChuyen.getNeoXe());
        txtPhiCauDuong.setText(thongTinVanChuyen.getPhiCauDuong());
        txtNoiLayCong.setText(thongTinVanChuyen.getNoiLayCong());
        txtDonGia.setText(thongTinVanChuyen.getDonGia());
        txtSoToKhai.setText(thongTinVanChuyen.getSoToKhai());
        txtNgayDiGiao.setText(thongTinVanChuyen.getNgayDiGiaoHang());

        int LoiNhuan = (Integer.parseInt(txtDonGia.getText().toString())
                * Integer.parseInt(txtsoXe.getText().toString()))
                - (Integer.parseInt(txtLuongTheoChuyen.getText().toString())
                -  Integer.parseInt(txtTamUng.getText().toString()))
                -(Integer.parseInt(txtNhienLieu.getText().toString())
                    +Integer.parseInt(txtPhiCauDuong.getText().toString())
                    +Integer.parseInt(txtPhiNangCong.getText().toString())
                    +Integer.parseInt(txtNeoXe.getText().toString()));
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);


        txtTongLoiNhuan.setText(format.format(LoiNhuan));
        //Sự kiện
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khởi tạo popup menu
                PopupMenu popupMenu = new PopupMenu(v.getContext(), btnSua);
                popupMenu.inflate(R.menu.lv_edit_button);
                popupMenu.show();
                //Set Sự kiện khi nhấn vào 1 item trong pop up menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return menuItemClicked(item,position);
                    }
                });
            }
        });
        ln.setVisibility(View.GONE);
        txtSTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tam==0)
                {
                    txtSTT.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_remove_circle_24, 0, 0, 0);
                    AnimationSet set = new AnimationSet(true);

                    Animation animation = new AlphaAnimation(0.0f, 1.0f);
                    animation.setDuration(1000);
                    set.addAnimation(animation);

                    animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, -1.0f,Animation.RELATIVE_TO_SELF, 0.0f
                    );
                    set.addAnimation(animation);
                    ln.setAnimation(set);
                    ln.setVisibility(View.VISIBLE);
                    tam = 1;
                }
                else {
                    AnimationSet set = new AnimationSet(true);
                    txtSTT.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_add_circle_24, 0, 0, 0);
                    Animation animation = new AlphaAnimation(0.0f, 1.0f);
                    animation.setDuration(1000);
                    set.addAnimation(animation);

                    animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, -1.0f,Animation.RELATIVE_TO_SELF, 0.0f,
                            Animation.RELATIVE_TO_SELF, 0,Animation.RELATIVE_TO_SELF, 0.0f
                    );
                    set.addAnimation(animation);
                    ln.setAnimation(set);
                    ln.setVisibility(View.GONE);
                    tam = 0;
                }
            }
        });
        return view;

    }
    private boolean menuItemClicked(MenuItem item, int position) {
        FragmentActivity activity = (FragmentActivity)(context);
        FragmentManager fm = activity.getSupportFragmentManager();
        android.app.FragmentManager fm2 = activity.getFragmentManager();

        switch (item.getItemId()) {
            case R.id.menuItem_Edit:
                frDialog_ThemThongTinVanChuyen frDialog_themThongTinVanChuyen = new frDialog_ThemThongTinVanChuyen(thongTinVanChuyens.get(position));

                frDialog_themThongTinVanChuyen.setTargetFragment(fr_tienTe,1);
                frDialog_themThongTinVanChuyen.show(fragmentManager,"frDialog_ThemThongTinVanChuyen");
                break;
            case R.id.menuItem_Delete:
                //Tạo message box ? Bạn có muốn xóa
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");
                //set Text và sự kiện cho nút accept
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        mySQL_manage.deleteThongTinVanChuyen(thongTinVanChuyens.get(position));
                        thongTinVanChuyens.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                //set Text và sự kiện cho nút reject
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                //Hiển thị dialog
                alert.show();
                break;
        }
        return true;
    }

}
