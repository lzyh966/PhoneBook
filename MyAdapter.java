package cn.edu.neusoft.phonebook;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter{

		private LayoutInflater inflater;
		private List<PeopleInfo> list;
		private Context context;
		
		private MyOpenHelper myHelper;
		private SQLiteDatabase db;

	    public static final String DB_NAME="my_contact";
	    public static final String TABLE_NAME="personInfo";

		public MyAdapter(Context context, List<PeopleInfo> list) {
			inflater = LayoutInflater.from(context);
			this.list = list;
			this.context=context;
			myHelper=new MyOpenHelper(context, DB_NAME, null, 1);
			db=myHelper.getWritableDatabase();
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.person_item, null);
				viewHolder = new ViewHolder();

				viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				viewHolder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
				viewHolder.btn_edit = (Button) convertView.findViewById(R.id.btn_edit);
				viewHolder.btn_del = (Button) convertView.findViewById(R.id.btn_del);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.tv_name.setText(list.get(position).getName());
			viewHolder.tv_phone.setText(list.get(position).getPhone_number());
			final int id=list.get(position).getID();
			viewHolder.btn_edit.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent((MainActivity)context,NewPersonActivity.class);
					intent.putExtra("id", id);
					((MainActivity)context).startActivity(intent);
					
				}
			});
			viewHolder.btn_del.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {					
					db.delete(TABLE_NAME, "ID="+id, null);
					Intent intent=new Intent((MainActivity)context,MainActivity.class);
					((MainActivity)context).startActivity(intent);
				}
			});
			return convertView;
		}
		public static class ViewHolder {
			TextView tv_phone,tv_name;
			Button btn_edit,btn_del;
		}
	
}

