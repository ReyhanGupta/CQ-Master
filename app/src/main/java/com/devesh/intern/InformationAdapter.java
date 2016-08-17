package com.devesh.intern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//import com.example.mysqldemo.Information;
//import com.example.mysqldemo.R;
//import com.example.mysqldemo.InformationAdapter.ContactHolder;
//

public class InformationAdapter extends ArrayAdapter {
    List list= new ArrayList();



    public InformationAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        // TODO Auto-generated constructor stub
    }

    public void add(Information object) {
        // TODO Auto-generated method stub
        super.add(object);

        list.add(object);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View row;
        row=convertView;
        ContactHolder contactHolder;
        if(row== null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row= layoutInflater.inflate(R.layout.row, parent, false);
            contactHolder= new ContactHolder();
            contactHolder.tv2=(TextView) row.findViewById(R.id.nameT);
            contactHolder.tv1=(TextView) row.findViewById(R.id.idT);
            contactHolder.tv4=(TextView) row.findViewById(R.id.ageT);
            contactHolder.tv5=(TextView) row.findViewById(R.id.usernameT);
            contactHolder.tv6=(TextView) row.findViewById(R.id.passwordT);
            contactHolder.tv3=(TextView) row.findViewById(R.id.surnameT);
            contactHolder.tv7=(TextView) row.findViewById(R.id.emailT);
            row.setTag(contactHolder);
        }

        else
        {
            contactHolder=(ContactHolder) row.getTag();
        }


        Information information= (Information) this.getItem(position);
        contactHolder.tv1.setText(information.id);
        contactHolder.tv2.setText(information.name);
        contactHolder.tv3.setText(information.surname);
        contactHolder.tv4.setText(information.age);
        contactHolder.tv5.setText(information.username);
        contactHolder.tv6.setText(information.password);
        contactHolder.tv7.setText(information.email);


        return row;
    }


    static class ContactHolder
    {
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;

    }


}
