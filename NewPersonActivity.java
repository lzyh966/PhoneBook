package cn.edu.neusoft.phonebook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPersonActivity extends AppCompatActivity {
	private EditText et_name,et_phone,et_address,et_email;
	private Button btn_ok,btn_back;
	
	private MyOpenHelper myHelper;
	public static final String DB_NAME="my_contact";
	public static final String TABLE_NAME="personInfo";
	public static final String NAME="Name";
	public static final String PHONE_NUMBER="Phone_number";
	public static final String ADDRESS="Address";
	public static final String EMAIL="E_mail";
	private SQLiteDatabase db;
	private int flag=0;
	private int id=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_person);

		myHelper=new MyOpenHelper(this, DB_NAME, null, 1);
		db=myHelper.getWritableDatabase();


		et_name=(EditText)findViewById(R.id.et_name);
		et_phone=(EditText)findViewById(R.id.et_phone);
		et_address=(EditText)findViewById(R.id.et_address);
		et_email=(EditText)findViewById(R.id.et_email);
		btn_ok=(Button)findViewById(R.id.button1);
		btn_back=(Button)findViewById(R.id.button2);


		if(getIntent().getIntExtra("id",0)>0)//判断是新增还是修改
		{
			 id=getIntent().getIntExtra("id",0);
			setPersonInfo(id);
			flag=1;
		}
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues value=new ContentValues();
				value.put(NAME, et_name.getText().toString());
				value.put(PHONE_NUMBER, et_phone.getText().toString());
				value.put(ADDRESS, et_address.getText().toString());
				value.put(EMAIL, et_email.getText().toString());
				long result;
				if(flag==0)
					result=db.insert(TABLE_NAME, null, value);
				else
					result=db.update(TABLE_NAME, value, "ID="+id, null);
				db.close();
				if(result>0)
				{
					Toast.makeText(NewPersonActivity.this, "操作成功！", Toast.LENGTH_LONG).show();
					Intent intent=new Intent(NewPersonActivity.this,MainActivity.class);
					startActivity(intent);
				}
			}
		});
		btn_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				startActivity(new Intent(NewPersonActivity.this,MainActivity.class));
			}
		});
	}

	private void setPersonInfo(int id) {
		 Cursor c=db.query(TABLE_NAME,new String[]{},"ID="+id,null,null,null,null);
		 if(c.moveToFirst())
		 {
			 String name=c.getString(c.getColumnIndex(NAME));
			 String phone_number=c.getString(c.getColumnIndex(PHONE_NUMBER));
			 String address=c.getString(c.getColumnIndex(ADDRESS));
			 String email=c.getString(c.getColumnIndex(EMAIL));
			 PeopleInfo d=new PeopleInfo(name,phone_number, address, email);
			 d.setID(c.getInt(c.getColumnIndex("ID")));
			 et_name.setText(d.getName());
			 et_phone.setText(d.getPhone_number());
			 et_address.setText(d.getAddress());
			 et_email.setText(d.getE_mail());
		 }
		c.close();
	}
}
