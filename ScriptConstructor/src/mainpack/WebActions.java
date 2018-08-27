package mainpack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.http.W3CHttpCommandCodec;
import org.openqa.selenium.remote.http.W3CHttpResponseCodec;

public class WebActions {
	
	
	WebDriver driver;

	
	
	public static String[][] importData(String fileName, int tabNumber) throws FileNotFoundException, IOException, InvalidFormatException, NullPointerException
    {
 
        String[][] data;
 
        //Create Workbook from Existing File
        InputStream fileIn = new FileInputStream(fileName);
        Workbook wb = WorkbookFactory.create(fileIn);
        Sheet sheet = wb.getSheetAt(tabNumber);
 
        //Define Data & Row Array and adjust from Zero Base Numer
        data = new String[sheet.getLastRowNum()+1][];
        Row[] row = new Row[sheet.getLastRowNum()+1];
        Cell[][] cell = new Cell[row.length][];
 
        //Transfer Cell Data to Local Variable
        for(int i = 0; i < row.length; i++)
        {
            try {
				row[i] = sheet.getRow(i);
 
				//Note that cell number is not Zero Based
				cell[i] = new Cell[row[i].getLastCellNum()];
				data[i] = new String[row[i].getLastCellNum()];
 
				for(int j = 0; j < cell[i].length; j++)
				{
				    cell[i][j] = row[i].getCell(j);
				    data[i][j] = cell[i][j].getStringCellValue();
				}
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
        }
 
        fileIn.close();
        return data;
    }	

	public static void exportData(String fileName, String tabName, String[][] data) throws FileNotFoundException, IOException
    {
        //Create new workbook and tab
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(fileName);
        Sheet sheet = wb.createSheet(tabName);
 
        //Create 2D Cell Array
        Row[] row = new Row[data.length];
        Cell[][] cell = new Cell[row.length][];
 
        //Define and Assign Cell Data from Given
        for(int i = 0; i < row.length; i ++)
        {
            row[i] = sheet.createRow(i);
            cell[i] = new Cell[data[i].length];
 
            for(int j = 0; j < cell[i].length; j ++)
            {
                
            	cell[i][j] = row[i].createCell(j);
                cell[i][j].setCellValue(data[i][j]);
            }
 
        }
 
        //Export Data
        wb.write(fileOut);
        fileOut.close();
        wb.close();
 
    }
	
	public void sleep(int time) 
	{
		
		int rnd = ThreadLocalRandom.current().nextInt(80,120);
		
		try {Thread.sleep(time*rnd/100);} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public static RemoteWebDriver createDriverFromSession(final SessionId sessionId, URL command_executor){
	    CommandExecutor executor = new HttpCommandExecutor(command_executor) {

	    @Override
	    public Response execute(Command command) throws IOException {
	        Response response = null;
	        if (command.getName() == "newSession") {
	            response = new Response();
	            response.setSessionId(sessionId.toString());
	            response.setStatus(0);
	            response.setValue(Collections.<String, String>emptyMap());

	            try {
	                Field commandCodec = null;
	                commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
	                commandCodec.setAccessible(true);
	                commandCodec.set(this, new W3CHttpCommandCodec());

	                Field responseCodec = null;
	                responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
	                responseCodec.setAccessible(true);
	                responseCodec.set(this, new W3CHttpResponseCodec());
	            } catch (NoSuchFieldException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }

	        } else {
	            response = super.execute(command);
	        }
	        return response;
	    }
	    };

	    return new RemoteWebDriver(executor, new DesiredCapabilities());
	}
	
	public void goTo(String website) 
	{
		System.setProperty("webdriver.gecko.driver","/C:\\Utils\\geckodriver.exe");  
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("network.proxy.type", 0);
		driver = new FirefoxDriver(options);
	
	
		driver.get(website);   
	}
	
}
	
