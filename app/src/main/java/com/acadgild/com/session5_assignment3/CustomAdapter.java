package com.acadgild.com.session5_assignment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Valyoo on 12/25/2016.
 */
public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<CustomHandler> arrList2;
    LayoutInflater inflater;
    public CustomAdapter(Context c1, ArrayList<CustomHandler> arrList) {
        this.context = c1;
        this.arrList2 = arrList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return arrList2.size();
    }

    @Override
    public Object getItem(int pos) {
        return arrList2.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
       ViewHolder holder;
        if(view==null){
            view = inflater.inflate(R.layout.custom_row,viewGroup,false);
            holder = new ViewHolder();
            holder.txtName1 = (TextView)view.findViewById(R.id.txtName);
            holder.txtPhone1 = (TextView)view.findViewById(R.id.txtPhone);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtName1.setText(arrList2.get(pos).getNames());
        holder.txtPhone1.setText(arrList2.get(pos).getPhoneNumbers());

        return view;
    }

    public class ViewHolder{
        TextView txtName1;
        TextView txtPhone1;
    }
}


