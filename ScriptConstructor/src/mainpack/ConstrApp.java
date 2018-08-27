package mainpack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.SpringLayout;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConstrApp extends JFrame {
	
	WebActions wa = new WebActions();
	WebElement we;
	private JPanel contentPane;
	private final JTextPane txtpnFindElement = new JTextPane();
	String[] webElements = {"id","xpath","prefix","else"};
	String[] webActions = {"click","sendkeys","hover mouse","hover+click"};
	String[] Presets = {"presets"};
	String[] savedElements = {"saved elements"};
	String[][] savedWebElements = new String[100][3];
	String[][] savedPresets = new String[100][3];
	String presetFileName = "presets.shit";
	
	
	private final JComboBox comboBoxWebElement = new JComboBox(webElements);
	private final JTextField textField_el_id = new JTextField();
	private final JTextPane txtpnActionToDo = new JTextPane();
	
	private final JComboBox comboBox_actions1 = new JComboBox(webActions);
	private final JButton btnAddToScriptElement = new JButton("Add to script");
	private final JTextPane txtpnGoToWebsite = new JTextPane();
	private final JButton btnAddToScriptSite = new JButton("Add to script");
	private final JPanel AddElementPanel = new JPanel();
	private final JPanel CreateScriptPanel = new JPanel();
	private final JTextPane txtpnSpecElementsYou = new JTextPane();
	private final JTextPane txtpnGiveElement = new JTextPane();
	private final JTextField textField_el_idname = new JTextField();
	private final JButton btnSaveElement = new JButton("Save element");
	private final JTextPane txtpnCreateScript = new JTextPane();
	private final JTextField textField = new JTextField();
	private final JPanel actionpanel = new JPanel();
	private final JTextPane txtpnChooseElement = new JTextPane();
	private final JComboBox comboBox_savedelements = new JComboBox(savedElements);
	private final JTextPane txtpnElementSavedSuccesfully = new JTextPane();
	private final JPanel LoadPresetPanel = new JPanel();
	private final JTextPane txtpnPresets = new JTextPane();
	private final JButton btnSave = new JButton("Save");
	private final JButton btnLoad = new JButton("Load");
	private final JComboBox comboBox_Presets = new JComboBox();
	private final JPanel DriverPathPanel = new JPanel();
	private final JTextPane txtpane_driverPath = new JTextPane();
	private final JTextField txtField_DriverPath = new JTextField();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ConstrApp frame = new ConstrApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConstrApp() {
		txtField_DriverPath.setColumns(10);
		try {
			
			savedPresets = wa.importData(presetFileName, 0);
			
		} catch (InvalidFormatException | NullPointerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textField.setColumns(10);
		textField_el_idname.setColumns(10);
		CreateScriptPanel.setLayout(new MigLayout("", "[266.00px,grow][]", "[25px][][36.00,grow][33.00]"));
		AddElementPanel.setBackground(Color.WHITE);
		AddElementPanel.add(textField_el_id);
		textField_el_id.setColumns(10);
		initGUI();
		
		for(int i=0;i<emptySlotOf(savedPresets);i++)
		{
			comboBox_Presets.addItem(savedPresets[i][0]);
			System.out.println(savedPresets[i][0]);
			
		}
			comboBox_Presets.setEditable(true);
			
			LoadPresetPanel.add(comboBox_Presets);
			
			contentPane.add(DriverPathPanel);
			SpringLayout sl_DriverPathPanel = new SpringLayout();
			sl_DriverPathPanel.putConstraint(SpringLayout.NORTH, txtField_DriverPath, 0, SpringLayout.NORTH, txtpane_driverPath);
			sl_DriverPathPanel.putConstraint(SpringLayout.WEST, txtField_DriverPath, 5, SpringLayout.EAST, txtpane_driverPath);
			sl_DriverPathPanel.putConstraint(SpringLayout.SOUTH, txtField_DriverPath, 0, SpringLayout.SOUTH, DriverPathPanel);
			sl_DriverPathPanel.putConstraint(SpringLayout.EAST, txtField_DriverPath, -15, SpringLayout.EAST, DriverPathPanel);
			sl_DriverPathPanel.putConstraint(SpringLayout.NORTH, txtpane_driverPath, 0, SpringLayout.NORTH, DriverPathPanel);
			sl_DriverPathPanel.putConstraint(SpringLayout.WEST, txtpane_driverPath, 0, SpringLayout.WEST, DriverPathPanel);
			sl_DriverPathPanel.putConstraint(SpringLayout.SOUTH, txtpane_driverPath, 0, SpringLayout.SOUTH, DriverPathPanel);
			DriverPathPanel.setLayout(sl_DriverPathPanel);
			txtpane_driverPath.setText("Specify driver path.");
			txtpane_driverPath.setFont(new Font("Rockwell", Font.BOLD, 15));
			
			DriverPathPanel.add(txtpane_driverPath);
			
			DriverPathPanel.add(txtField_DriverPath);
		
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, DriverPathPanel, 35, SpringLayout.SOUTH, AddElementPanel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, CreateScriptPanel, 15, SpringLayout.SOUTH, DriverPathPanel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, CreateScriptPanel, 215, SpringLayout.SOUTH, DriverPathPanel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, DriverPathPanel, 15, SpringLayout.SOUTH, AddElementPanel);
		sl_contentPane.putConstraint(SpringLayout.WEST, DriverPathPanel, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, DriverPathPanel, -15, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, AddElementPanel, 15, SpringLayout.SOUTH, LoadPresetPanel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, LoadPresetPanel, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, LoadPresetPanel, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, LoadPresetPanel, 25, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, LoadPresetPanel, -15, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, CreateScriptPanel, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, CreateScriptPanel, -15, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, AddElementPanel, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, AddElementPanel, 150, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, AddElementPanel, -15, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_el_id, 0, SpringLayout.NORTH, comboBoxWebElement);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_el_id, 5, SpringLayout.EAST, comboBoxWebElement);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_el_id, 0, SpringLayout.SOUTH, comboBoxWebElement);
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBoxWebElement, 4, SpringLayout.SOUTH, txtpnFindElement);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBoxWebElement, 0, SpringLayout.WEST, txtpnFindElement);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtpnFindElement, 10, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		contentPane.add(AddElementPanel);
		SpringLayout sl_AddElementPanel = new SpringLayout();
		sl_AddElementPanel.putConstraint(SpringLayout.NORTH, txtpnElementSavedSuccesfully, 5, SpringLayout.SOUTH, btnSaveElement);
		sl_AddElementPanel.putConstraint(SpringLayout.WEST, txtpnElementSavedSuccesfully, 0, SpringLayout.WEST, btnSaveElement);
		sl_AddElementPanel.putConstraint(SpringLayout.NORTH, btnSaveElement, 0, SpringLayout.NORTH, txtpnGiveElement);
		sl_AddElementPanel.putConstraint(SpringLayout.WEST, btnSaveElement, 50, SpringLayout.EAST, textField_el_idname);
		sl_AddElementPanel.putConstraint(SpringLayout.NORTH, textField_el_idname, 0, SpringLayout.NORTH, txtpnGiveElement);
		sl_AddElementPanel.putConstraint(SpringLayout.WEST, textField_el_idname, 5, SpringLayout.EAST, txtpnGiveElement);
		sl_AddElementPanel.putConstraint(SpringLayout.NORTH, txtpnGiveElement, 5, SpringLayout.SOUTH, txtpnFindElement);
		sl_AddElementPanel.putConstraint(SpringLayout.WEST, txtpnGiveElement, 0, SpringLayout.WEST, AddElementPanel);
		sl_AddElementPanel.putConstraint(SpringLayout.NORTH, textField_el_id, 0, SpringLayout.NORTH, comboBoxWebElement);
		sl_AddElementPanel.putConstraint(SpringLayout.WEST, textField_el_id, 5, SpringLayout.EAST, comboBoxWebElement);
		sl_AddElementPanel.putConstraint(SpringLayout.NORTH, comboBoxWebElement, 0, SpringLayout.NORTH, txtpnFindElement);
		sl_AddElementPanel.putConstraint(SpringLayout.WEST, comboBoxWebElement, 5, SpringLayout.EAST, txtpnFindElement);
		sl_AddElementPanel.putConstraint(SpringLayout.NORTH, txtpnFindElement, 5, SpringLayout.SOUTH, txtpnSpecElementsYou);
		sl_AddElementPanel.putConstraint(SpringLayout.WEST, txtpnFindElement, 0, SpringLayout.WEST, AddElementPanel);
		sl_AddElementPanel.putConstraint(SpringLayout.NORTH, txtpnSpecElementsYou, 0, SpringLayout.NORTH, AddElementPanel);
		sl_AddElementPanel.putConstraint(SpringLayout.WEST, txtpnSpecElementsYou, 0, SpringLayout.WEST, AddElementPanel);
		AddElementPanel.setLayout(sl_AddElementPanel);
		AddElementPanel.add(txtpnFindElement);
		txtpnFindElement.setEditable(false);
		txtpnFindElement.setText("Find element by:");
		AddElementPanel.add(comboBoxWebElement);
		
		contentPane.add(CreateScriptPanel);
		txtpnSpecElementsYou.setEditable(false);
		txtpnSpecElementsYou.setFont(new Font("Rockwell", Font.BOLD, 15));
		txtpnSpecElementsYou.setText("Specify elements you are going to use.");
		
		AddElementPanel.add(txtpnSpecElementsYou);
		txtpnGiveElement.setText("Give element relevant name:");
		
		AddElementPanel.add(txtpnGiveElement);
		
		AddElementPanel.add(textField_el_idname);
		btnSaveElement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				we=new WebElement();
				we.setName(textField_el_idname.getText());
				we.setType(comboBoxWebElement.getSelectedItem().toString());
				we.setValue(textField_el_id.getText());
				
				we.writeObj(we);
				
				//savedWebElements[emptySlotOf(savedWebElements)][0]=textField_el_idname.getText();
				//savedWebElements[emptySlotOf(savedWebElements)][1]=comboBoxWebElement.getSelectedItem().toString();
				//savedWebElements[emptySlotOf(savedWebElements)][2]=textField_el_id.getText();
				//System.out.println("Saved element: "+textField_el_idname.getText()+", "+comboBoxWebElement.getSelectedItem().toString()+", "+textField_el_id.getText());
				
			}
		});
		
		AddElementPanel.add(btnSaveElement);
		txtpnCreateScript.setEditable(false);
		txtpnCreateScript.setFont(new Font("Rockwell", Font.BOLD, 15));
		txtpnCreateScript.setText("Create script.");
		
		CreateScriptPanel.add(txtpnCreateScript, "cell 0 0 2 1,alignx left,aligny top");
		CreateScriptPanel.add(btnAddToScriptSite, "cell 1 1,alignx left,aligny top");
		btnAddToScriptSite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAddToScriptSite, 0, SpringLayout.NORTH, txtpnGoToWebsite);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAddToScriptSite, 0, SpringLayout.SOUTH, txtpnGoToWebsite);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtpnGoToWebsite, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtpnGoToWebsite, 0, SpringLayout.WEST, txtpnFindElement);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtpnFindElement, 20, SpringLayout.SOUTH, txtpnGoToWebsite);
		CreateScriptPanel.add(txtpnGoToWebsite, "flowx,cell 0 1,alignx left,aligny top");
		txtpnGoToWebsite.setText("Go to Website:");
		
		CreateScriptPanel.add(textField, "cell 0 1,growx,aligny top");
		
		CreateScriptPanel.add(actionpanel, "cell 0 2,grow");
		SpringLayout sl_actionpanel = new SpringLayout();
		sl_actionpanel.putConstraint(SpringLayout.NORTH, comboBox_savedelements, 0, SpringLayout.NORTH, txtpnChooseElement);
		sl_actionpanel.putConstraint(SpringLayout.WEST, comboBox_savedelements, 5, SpringLayout.EAST, txtpnChooseElement);
		sl_actionpanel.putConstraint(SpringLayout.NORTH, txtpnChooseElement, 6, SpringLayout.SOUTH, txtpnActionToDo);
		sl_actionpanel.putConstraint(SpringLayout.WEST, txtpnChooseElement, 0, SpringLayout.WEST, actionpanel);
		sl_actionpanel.putConstraint(SpringLayout.WEST, comboBox_actions1, 5, SpringLayout.EAST, txtpnActionToDo);
		sl_actionpanel.putConstraint(SpringLayout.NORTH, txtpnActionToDo, 0, SpringLayout.NORTH, actionpanel);
		sl_actionpanel.putConstraint(SpringLayout.NORTH, comboBox_actions1, 0, SpringLayout.NORTH, txtpnActionToDo);
		sl_actionpanel.putConstraint(SpringLayout.WEST, txtpnActionToDo, 0, SpringLayout.WEST, actionpanel);
		actionpanel.setLayout(sl_actionpanel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtpnActionToDo, 0, SpringLayout.NORTH, txtpnFindElement);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtpnActionToDo, 80, SpringLayout.EAST, txtpnFindElement);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtpnActionToDo, 0, SpringLayout.SOUTH, txtpnFindElement);
		actionpanel.add(txtpnActionToDo);
		txtpnActionToDo.setText("Action to do:   ");
		txtpnActionToDo.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBox_actions1, 0, SpringLayout.WEST, txtpnActionToDo);
		actionpanel.add(comboBox_actions1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, comboBox_actions1, 0, SpringLayout.NORTH, comboBoxWebElement);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, comboBox_actions1, 0, SpringLayout.SOUTH, comboBoxWebElement);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_el_id, -5, SpringLayout.WEST, comboBox_actions1);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAddToScriptElement, 10, SpringLayout.EAST, comboBox_actions1);
		txtpnChooseElement.setText("Element:          ");
		
		actionpanel.add(txtpnChooseElement);
		comboBox_savedelements.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
			}
		});
		
		actionpanel.add(comboBox_savedelements);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAddToScriptElement, 0, SpringLayout.NORTH, comboBoxWebElement);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAddToScriptElement, 0, SpringLayout.SOUTH, comboBoxWebElement);
		txtpnElementSavedSuccesfully.setEnabled(false);
		txtpnElementSavedSuccesfully.setText("Element saved");
		
		AddElementPanel.add(txtpnElementSavedSuccesfully);
		CreateScriptPanel.add(btnAddToScriptElement, "cell 1 2,alignx left,growy");
		
		contentPane.add(LoadPresetPanel);
		SpringLayout sl_LoadPresetPanel = new SpringLayout();
		sl_LoadPresetPanel.putConstraint(SpringLayout.EAST, btnSave, -10, SpringLayout.WEST, btnLoad);
		sl_LoadPresetPanel.putConstraint(SpringLayout.EAST, comboBox_Presets, -15, SpringLayout.WEST, btnSave);
		sl_LoadPresetPanel.putConstraint(SpringLayout.EAST, btnLoad, -15, SpringLayout.EAST, LoadPresetPanel);
		sl_LoadPresetPanel.putConstraint(SpringLayout.NORTH, comboBox_Presets, 0, SpringLayout.NORTH, LoadPresetPanel);
		sl_LoadPresetPanel.putConstraint(SpringLayout.WEST, comboBox_Presets, 15, SpringLayout.EAST, txtpnPresets);
		sl_LoadPresetPanel.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.NORTH, LoadPresetPanel);
		sl_LoadPresetPanel.putConstraint(SpringLayout.NORTH, btnLoad, 0, SpringLayout.NORTH, LoadPresetPanel);
		sl_LoadPresetPanel.putConstraint(SpringLayout.NORTH, txtpnPresets, 0, SpringLayout.NORTH, LoadPresetPanel);
		sl_LoadPresetPanel.putConstraint(SpringLayout.WEST, txtpnPresets, 0, SpringLayout.WEST, LoadPresetPanel);
		LoadPresetPanel.setLayout(sl_LoadPresetPanel);
		txtpnPresets.setEditable(false);
		txtpnPresets.setFont(new Font("Rockwell", Font.BOLD, 15));
		txtpnPresets.setText("Presets");
		
		LoadPresetPanel.add(txtpnPresets);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				savePreset();
			}
		});
		
		LoadPresetPanel.add(btnSave);
		
		LoadPresetPanel.add(btnLoad);
	}
	
	
	public void savePreset()
	{
		boolean exists;
		exists=false;
		for(int i=0;i<emptySlotOf(savedPresets);i++)
		{
			if(savedPresets[i][0].equals(comboBox_Presets.getSelectedItem().toString()))
			{
				exists=true;
			}
			else {}
		}
		if(!exists) {savedPresets[emptySlotOf(savedPresets)][0]=comboBox_Presets.getSelectedItem().toString();
		comboBox_Presets.addItem(comboBox_Presets.getSelectedItem().toString());}
		
		
		String presetName1 = comboBox_Presets.getSelectedItem().toString();
		try {
			wa.exportData(presetFileName, presetFileName, savedPresets);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void loadPreset()
	{
		
	}
	
	public void saveElement(String name,String idtype,String id)
	{
		
	}
	
	
	
	public boolean contains(String obj,String[][] list ) 
	{
		int i;
	    
			for (i = 0; i < list.length; i++) 
			{
				
			    if (list[i][0]!=null&&list[i][0].equals(obj)) {
			    	//System.out.println("check"+i);
			    	return true;
			        
			    }
			}
		

	    return false;
	}
	
	
	
	public int emptySlotOf(String[][] list) 
	{
		int slotN=0;

		for(int i = 0; i<list.length; i++) 
		{
		    try {
				if(list[i][0].equals("")||list[i][0]==null)
				{
				   slotN = i;
				   break;
				}
			} catch (NullPointerException e) {
				slotN = i;
				   break;
				
			}
		}
		return slotN;
	}
}
