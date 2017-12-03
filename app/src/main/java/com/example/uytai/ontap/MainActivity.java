package com.example.uytai.ontap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
   ListView listView;
    ArrayList<Model> arrayList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        TruyVan();
        ThucThi();

    }

    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listViewCongViiec);
    }


    //show icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_cv, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //when select icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_addcv){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }


    //them
    private void DialogThem(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.them_congviec);

        Button btnOK = dialog.findViewById(R.id.btnOK);
        Button btnCancle = dialog.findViewById(R.id.btnCancle);
        final EditText edtTenCV = dialog.findViewById(R.id.edtaddcv);

        //
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenCV = edtTenCV.getText().toString();
                if(tenCV.equals("")){
                    Toast.makeText(MainActivity.this, "Fill in Name", Toast.LENGTH_LONG).show();
                }else{
                    database.QueryData("INSERT INTO CongViec VALUES(null, '"+tenCV+"')");
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
                    ThucThi();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    //sua
    public void DialogEdit(final int id, String tenCV){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sua_congviec);
        Button btnOK = dialog.findViewById(R.id.btn_EditOK);
        Button btnCancle = dialog.findViewById(R.id.btn_EditCancle);
        final EditText edtTenCV = dialog.findViewById(R.id.edt_edit);

        edtTenCV.setText(tenCV);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = edtTenCV.getText().toString().trim();
                database.QueryData("UPDATE CongViec SET TenCV = '"+newName+"' WHERE Id = '"+id+"'");
                dialog.dismiss();
                ThucThi();
            }
        });
        dialog.show();
    }

    //xoa
    public void DialogXoa(final int id , String tenCV){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Delete");
        dialog.setMessage("Do you want to delete "+tenCV+"?");
        //
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM CongViec WHERE Id='"+id+"'");
                ThucThi();
            }
        });

        //
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    //truy van database
    private void TruyVan() {
        database = new Database(this, "ghichu.sqlite", null, 1);
        //Tao table CongViec
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200))");
    }


    //thu thi
    private void ThucThi() {
        arrayList = new ArrayList<>();
        adapter = new Adapter(this, R.layout.item_cv, arrayList);
        listView.setAdapter(adapter);
        //tao database ten "ghichu"
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        arrayList.clear();
        while (dataCongViec.moveToNext()){
            int id = dataCongViec.getInt(0);
            String ten = dataCongViec.getString(1);
            arrayList.add(new Model(id, ten));
        }
        adapter.notifyDataSetChanged();
    }



    //them du lieu vao database
    private void Insert() {
        //insert de lieu
        database.QueryData("INSERT INTO CongViec VALUES(null, 'Hoc Android')");
    }

}
