package cn.edu.neusoft.phonebook;

public class PeopleInfo {
	private int ID;
	public String Phone_number;
	public String Name;
	public String Address;
	public String E_mail;

	public PeopleInfo(String name,String phone_number,String address,String e_mail)	{
		this.Name=name;
		this.Phone_number=phone_number;
		this.Address=address;
		this.E_mail=e_mail;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getE_mail() {
		return E_mail;
	}

	public void setE_mail(String e_mail) {
		E_mail = e_mail;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone_number() {
		return Phone_number;
	}

	public void setPhone_Number(String phone_number) {
		Phone_number = phone_number;
	}
}
