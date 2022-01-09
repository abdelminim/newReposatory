package hatley.supermarket.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table( name = "supermarkets" )
public class Supermarket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "aname")
	private String aName;
	
	@NotNull
	@Column(name = "ename")
	private String eName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "image")
	private String image;

	public Supermarket() {
		super();
	}

	public Supermarket(int id, String aName, String eName, String address, String image) {
		super();
		this.id = id;
		this.aName = aName;
		this.eName = eName;
		this.address = address;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Supermarket [id=" + id + ", aName=" + aName + ", eName=" + eName + ", address=" + address + ", image="
				+ image + "]";
	}

}
