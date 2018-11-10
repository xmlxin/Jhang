package com.xiaoxin.feng.jhang.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.xiaoxin.feng.jhang.bean.Contact;


/**
 * 系统的短信工具类
 * @author 小B
 *
 */
public class SmsTools {

	/**
	 * 声明一个接口，里面提供备份短信的回调函数
	 * @author 小B
	 *
	 */
	public interface BackupCallback{
		/**
		 * 短信备份前调用的代码
		 * @param max 一共多少条短信需要备份
		 */
		void beforeSmsBackup(int max);
		/**
		 * 短信备份过程中调用的代码
		 * @param progress 当前备份的进度
		 */
		void onSmsBackup(int progress);
	}


	/**
	 * 短信的备份
	 * @param context 上下文
	 * @param BackupCallback callback
	 */
	public static void backUpSms(Context context, BackupCallback callback){
		try {
			ContentResolver resolver = context.getContentResolver();
			Uri uri = Uri.parse("content://sms/");
			XmlSerializer  serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),"smsbackup.xml");
			FileOutputStream fos = new FileOutputStream(file);
			serializer.setOutput(fos, "utf-8");
			serializer.startDocument("utf-8", true);
			serializer.startTag(null, "infos");
			Cursor cursor = resolver.query(uri, new String[]{"address","body","type","date"}, null, null, null);
			//设置进度的最大值，具体是用什么控件设置最大值，小B不关心
			int progress = 0;
			callback.beforeSmsBackup(cursor.getCount());
			while(cursor.moveToNext()){
				serializer.startTag(null, "info");
				String address = cursor.getString(0);
				serializer.startTag(null, "address");
				serializer.text(address);
				serializer.endTag(null, "address");
				String body = cursor.getString(1);
				serializer.startTag(null, "body");
				serializer.text(body);
				serializer.endTag(null, "body");
				String type = cursor.getString(2);
				serializer.startTag(null, "type");
				serializer.text(type);
				serializer.endTag(null, "type");
				String date = cursor.getString(3);
				serializer.startTag(null, "date");
				serializer.text(date);
				serializer.endTag(null, "date");
				serializer.endTag(null, "info");
//				SystemClock.sleep(1500);//备份太快，用户一般不会相信
				//更新进度，具体是用什么控件去更新进度小B也不关心
				progress++;
				callback.onSmsBackup(progress);
			}
			cursor.close();
			serializer.endTag(null, "infos");
			serializer.endDocument();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 短信还原 4.4之后需要设置成默认短信应用
	 * https://segmentfault.com/q/1010000002298471
	 * @param context
	 */
	public void restoreSms(Context context) {
		ContentResolver resolver = context.getContentResolver();
		Uri uri = Uri.parse("content://sms/");
		XmlPullParser newPullParser = Xml.newPullParser();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(context.getFilesDir().getAbsolutePath()
					+ "/sms.xml");
			newPullParser.setInput(inputStream, "utf-8");
			int eventType = newPullParser.getEventType();
			ContentValues values = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if ("sms".equals(newPullParser.getName())) {
						values = new ContentValues();
					} else if ("address".equals(newPullParser.getName())) {
						values.put("address", newPullParser.nextText());
					} else if ("body".equals(newPullParser.getName())) {
						values.put("body", newPullParser.nextText());
					} else if ("date".equals(newPullParser.getName())) {
						values.put("date", newPullParser.nextText());
					} else if ("type".equals(newPullParser.getName())) {
						values.put("type", newPullParser.nextText());
					}

				} else if (eventType == XmlPullParser.END_TAG) {
					if ("sms".equals(newPullParser.getName())) {
						resolver.insert(uri, values);
					}
				}
				eventType = newPullParser.next();
			}
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public  void insertSms(Context context) {

//        Uri uri = Uri.parse("content://sms");
//        ContentValues value;
//        ContentResolver cr = context.getContentResolver();
//                value = new ContentValues();
//                value.put("address","106575595555");
//                value.put("body", "您的银行卡余额为：-1000000");
//                value.put("date", System.currentTimeMillis());
//                value.put("type", "1");
////                value.put("protocol", si.getProtocol());
////                value.put("read", si.getRead());
////                value.put("status", si.getStatus());
//        Uri insert = cr.insert(uri, value);
//        Toast.makeText(context, "插入短信为什么会失败:" + insert, Toast.LENGTH_SHORT).show();

		ContentValues my_values = new ContentValues();
		my_values.put("address", "+923359110795");//sender name
		my_values.put("body", "this is my text");
		my_values.put("date", System.currentTimeMillis());
		my_values.put("type", "1");
		Uri insert = context.getContentResolver().insert(Uri.parse("content://sms/inbox"), my_values);
		Toast.makeText(context,"插入短信为什么会失败:" + insert, Toast.LENGTH_SHORT).show();
	}

	public static List<Contact> queryContacts(Context context){
		//0.创建一个集合
		List<Contact> contactLists = new ArrayList<>();

		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		Cursor cursor = context.getContentResolver().query(uri, new String[]{"contact_id"}, null, null, null);
		while (cursor.moveToNext()) {
			//1.先查询row_contacts表的contact_id列 可以得知一共有几条联系人
			String contact_id = cursor.getString(0);
			Log.i("Tag", "contact_id: " + contact_id);

			if(contact_id != null) {
				Contact contact = new Contact();
				contact.setId(contact_id);

				//2.根据contact_id去查询data表，查询data1列和mimetype_id
				//※ 在查询data表时，其实查询的是view_data的视图(view_data是由data表和mimetype表的组合)

				Cursor dataCursor = context.getContentResolver().query(dataUri, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{contact_id}, null);
				while (dataCursor.moveToNext()) {
					String data1 = dataCursor.getString(0);
					String mimeType = dataCursor.getString(1);
					Log.i("Tag", "data1: " + data1 + "    mimeType: " + mimeType);
					//3.根据mimetype 区分data1数据
					if ("vnd.android.cursor.item/name".equals(mimeType)) {
						contact.setName(data1);
					} else if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) {
						contact.setPhone(data1);
					} else if ("vnd.android.cursor.item/email_v2".equals(mimeType)) {
						contact.setEmail(data1);
					}
				}

				contactLists.add(contact);
			}
		}

		return contactLists;
	}

	/**
	 * 联系人备份
	 * @param context
	 * @param callback
	 */
	public static void backContack(Context context,BackupCallback callback) {
		try {
			XmlSerializer  serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),"contactBack.xml");
			FileOutputStream fos = new FileOutputStream(file);
			serializer.setOutput(fos, "utf-8");
			serializer.startDocument("utf-8", true);
			serializer.startTag(null, "contacts");
			int progress = 0;
			List<Contact> contacts = queryContacts(context);
			callback.beforeSmsBackup(contacts.size());
			for (int i = 0; i < contacts.size(); i++) {
				serializer.startTag(null, "contact");

				serializer.startTag(null, "id");
				serializer.text(contacts.get(i).getId());
				serializer.endTag(null, "id");

				serializer.startTag(null, "name");
				serializer.text(contacts.get(i).getName());
				serializer.endTag(null, "name");

				serializer.startTag(null, "phone");
				serializer.text(contacts.get(i).getPhone());
				serializer.endTag(null, "phone");

				serializer.startTag(null, "email");
				serializer.text(contacts.get(i).getEmail());
				serializer.endTag(null, "email");

				serializer.endTag(null, "contact");

				//更新进度，具体是用什么控件去更新进度小B也不关心
				progress++;
				callback.onSmsBackup(progress);
			}
			serializer.endTag(null, "contacts");
			serializer.endDocument();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加一个新的联系人数据
	 *
	 * @param name
	 * @param phoneNumber
	 * @return 是否添加成功
	 */
	public void add(Context context,String name, String phoneNumber) {
		// 根据号码找数据，如果存在则不添加，因为有号码但无名字是不允许的
		// if (!findNameByPhoneNumber(phoneNumber)) {
		// 插入raw_contacts表，并获取_id属性
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		long contact_id = ContentUris.parseId(resolver.insert(uri, values));
		// 插入data表
		uri = Uri.parse("content://com.android.contacts/data");
		// 添加姓名
		values.put("raw_contact_id", contact_id);
		values.put(ContactsContract.Contacts.Data.MIMETYPE, "vnd.android.cursor.item/name");
		values.put("data2", name);
		// values.put("data1", "Jack");
		resolver.insert(uri, values);
		values.clear();
		// 添加手机号码
		values.put("raw_contact_id", contact_id);
		values.put(ContactsContract.Contacts.Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
		values.put("data2", "2"); // 2表示手机
		values.put("data1", phoneNumber);
		Uri insert = resolver.insert(uri, values);
		values.clear();
		Toast.makeText(context, "插入联系人为什么会失败:" + insert, Toast.LENGTH_SHORT).show();
	}

}
