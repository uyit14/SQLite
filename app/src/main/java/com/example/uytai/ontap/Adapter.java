package com.example.uytai.ontap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by uytai on 12/3/2017.
 */

public class Adapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<Model> list;

    public Adapter(MainActivity context, int layout, List<Model> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //
    private class ViewHolder{
        TextView txtTenCV;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.txtTenCV = convertView.findViewById(R.id.txtTenCV);
            viewHolder.imgDelete = convertView.findViewById(R.id.imgDelete);
            viewHolder.imgEdit = convertView.findViewById(R.id.imgEdit);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Model cv = list.get(position);
        viewHolder.txtTenCV.setText(cv.getTenCV());

        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogEdit(cv.getId(), cv.getTenCV());
            }
        });

        //
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoa(cv.getId(), cv.getTenCV());
            }
        });
        return convertView;
    }
}
