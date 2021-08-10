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
import com.example.udlogistic.frDialog_ThemKhachHang;
import com.example.udlogistic.fr_QuanLyKhachHang;
import com.example.udlogistic.model.KhachHang;

import java.util.ArrayList;

public class Adapter_LvDanhSachKhachHang extends ArrayAdapter implements Filterable {
    Context context; int resource;
    ArrayList<KhachHang>khachHangs = new ArrayList<>();
    ArrayList<KhachHang>source = new ArrayList<>();
    FragmentManager fragmentManager;
    fr_QuanLyKhachHang fr_quanLyKhachHang;

    MySQL_Manage mySQL_manage = new MySQL_Manage();
    int tam =0;
    public Adapter_LvDanhSachKhachHang(@NonNull Context context, int resource, @NonNull ArrayList<KhachHang>khachHangs) {
        super(context, resource, khachHangs);
        this.context = context;
        this.resource = resource;
        this.khachHangs = khachHangs;
        this.source = khachHangs;
    }
    public  void setFragmentManage( FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }
    public  void setfr_QuanLyKhachHang( fr_QuanLyKhachHang fr_quanLyKhachHang)
    {
        this.fr_quanLyKhachHang = fr_quanLyKhachHang;
    }
    @Override
    public int getCount() {
        return khachHangs.size();
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
                    khachHangs = source;
                } else {
                    ArrayList<KhachHang> list = new ArrayList<>();
                    for (KhachHang khachHang : source) {
                        if (khachHang.getHoTen().toLowerCase().contains(strSearch.toLowerCase())||
                                khachHang.getSoDienThoai().toLowerCase().contains(strSearch.toLowerCase())||
                                khachHang.getDiaChi().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(khachHang);
                        }
                    }
                    khachHangs = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = khachHangs;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                khachHangs = (ArrayList<KhachHang>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.lvkhachhang_item,null);
        //Nạp layout
        TextView txtSTT = view.findViewById(R.id.txtSTT);
        TextView txtHoTen = view.findViewById(R.id.txtHoTen);
        TextView txtSoDienThoai = view.findViewById(R.id.txtSoDienThoai);
        TextView txtDiaChi = view.findViewById(R.id.txtDiaChi);
        LinearLayout ln = view.findViewById(R.id.linearInfo);
        Button btnSua = view.findViewById(R.id.btnSua);
        KhachHang khachHang = khachHangs.get(position);
        //Nạp dữ liệu
        if (khachHang == null) {
            return null;
        }
        txtSTT.setText((position+1)+"");
        txtHoTen.setText((khachHang.getHoTen()));
        txtSoDienThoai.setText(khachHang.getSoDienThoai());
        txtDiaChi.setText(khachHang.getDiaChi());
        ln.setVisibility(View.GONE);
        //Sự kiện
        btnSua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               // ln.startAnimation(animation);
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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tam==0)
                {
                    AnimationSet set = new AnimationSet(true);
                    txtSTT.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_remove_circle_24, 0, 0, 0);
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
                frDialog_ThemKhachHang frDialog_themKhachHang = new frDialog_ThemKhachHang(khachHangs.get(position));
                    frDialog_themKhachHang.setTargetFragment(fr_quanLyKhachHang,1);
                    frDialog_themKhachHang.show(fragmentManager,"frDialog_ThemKhachHang");
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
                        mySQL_manage.deleteKhachHang(khachHangs.get(position));
                        khachHangs.remove(position);
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
