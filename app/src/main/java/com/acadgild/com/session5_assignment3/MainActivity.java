package com.acadgild.com.session5_assignment3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listContacts;
    private TextView txtTitle;
    private static final int MENU_ID_01 = 100;
    private static final int MENU_ID_02 = 101;

    String[] names = new String[]{"Anand", "Manoj", "Murali", "Akshay", "Deepak", "Gaurav", "Krishna", "Pankaj", "Raj", "Suraj", "Vinay"};
    String[] numbers = new String[]{"988009988", "8814251908", "9800982415", "9825617889", "9800090091", "9811234189", "9090221425" ,
            "9081112615", "7990098789", "9111125617", "12341567890"};

    ArrayList<CustomHandler> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setPaintFlags(txtTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        listContacts = (ListView)findViewById(R.id.lstContacts);

        for(int i=0;i<names.length;i++)
        {
            CustomHandler handler = new CustomHandler();
            handler.setNames(names[i]);
            handler.setPhoneNumbers(numbers[i]);
            arrayList.add(handler);
        }
        CustomAdapter adp = new CustomAdapter(this, arrayList);
        listContacts.setAdapter(adp);
        registerForContextMenu(listContacts);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Contacts Menu");
        menu.add(0, MENU_ID_01, 1, "Call");//groupId, itemId, order, title
        menu.add(0, MENU_ID_02, 2, "Send sms");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;

        String menuItemName =names[(int)info.id];
        String menuItemNumber = numbers[(int)info.id];
        boolean flag = false;
       // Object phoneNum = listContacts.getAdapter().getItem(listPosition).toString();
        if(item.getItemId()==MENU_ID_01 && item.getGroupId()==0)
        {
           // Toast.makeText(getApplicationContext(),"Clicked on " +item.getGroupId()+"..."+item.getItemId(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Calling: "+ menuItemName ,Toast.LENGTH_LONG).show();
            Intent call = new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:"+menuItemNumber));
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                flag = true;
            }
            else
            {
                flag = false;
                Toast.makeText(getApplicationContext(),"Please check the Phone connection",Toast.LENGTH_SHORT).show();
            }
            startActivity(call);
        }


        else if(item.getItemId()==MENU_ID_02 && item.getGroupId()==0)
        {
            // Toast.makeText(getApplicationContext(),"Clicked on " +item.getGroupId()+"..."+item.getItemId(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Sending sms to : "+ menuItemName ,Toast.LENGTH_LONG).show();
            Intent sms = new Intent(Intent.ACTION_VIEW);
            sms.setType("vnd.android-dir/mms-sms");
            sms.putExtra("address", menuItemNumber.toString());

            try{

                startActivity(sms);

            } catch (Exception ex) {

                Toast.makeText(MainActivity.this, "Your sms has failed...",

                        Toast.LENGTH_LONG).show();

                ex.printStackTrace();

            }

        }


      //  return super.onContextItemSelected(item);
        return true;
    }



}
