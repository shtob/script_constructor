package mainpack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WebElement implements java.io.Serializable{

	
	private String name=null;
	private String type=null;
	private String value=null;
	
	
	
	public WebElement() {
		// TODO Auto-generated constructor stub
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getType()
	{
		return type;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public String getValue()
	{
		return value;
	}
	public WebElement readObj(String name)
	{
		 WebElement e = null;
	      try {
	         FileInputStream fileIn = new FileInputStream("C:\\Users\\shtob\\eclipse-workspace\\ScriptConstructor\\webelements\\"+name+".ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (WebElement) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return e;
	      } catch (ClassNotFoundException c) {
	         System.out.println("WebElement class not found");
	         c.printStackTrace();
	         return e ;
	      }	
	      return e;
	}
	public void writeObj(WebElement e)
	{
		
		
			
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("C:\\Users\\shtob\\eclipse-workspace\\ScriptConstructor\\webelements\\"+e.name+".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(e);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in "+"/webelements/"+e.name+".ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	
}
