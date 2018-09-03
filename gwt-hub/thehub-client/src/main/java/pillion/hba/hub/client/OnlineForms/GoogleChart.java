package pillion.hba.hub.client.OnlineForms;

import java.util.Map;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.DataView;
import com.googlecode.gwt.charts.client.format.PatternFormat;
import com.googlecode.gwt.charts.client.orgchart.OrgChart;
import com.googlecode.gwt.charts.client.orgchart.OrgChartOptions;
import com.googlecode.gwt.charts.client.util.ArrayHelper;

public class GoogleChart {
	private static OrgChart chart;
	static FlexTable gridGoo = new FlexTable();
	
	public static FlexTable GoogleChartMethod(String status, FlexTable gridGoo) {
		
		
		ChartLoader chartLoader = new ChartLoader(ChartPackage.ORGCHART);
	      chartLoader.loadApi(new Runnable() {
	         public void run() {
	            // Create and attach the chart
	            chart = new OrgChart();
	           
	            draw();
	            
	         }
	      });
		return gridGoo;
	}

	   public static void draw() {
	      // Prepare the data
		   
	      DataTable dataTable = DataTable.create();
	      dataTable.addColumn(ColumnType.STRING, "CN");
	      dataTable.addColumn(ColumnType.STRING, "Manager");
	      dataTable.addColumn(ColumnType.STRING, "Email");
//	      dataTable.addColumn(ColumnType.STRING, "Company");
//	      dataTable.addColumn(ColumnType.STRING, "City");
//	      dataTable.addColumn(ColumnType.STRING, "EmailAddress");
	      
	      
//	      String[] employee = {
//	      "Courtney Burrows","HBA Legal","Perth","Courtney.Burrows@hbalegal.com","Ruth Slater",
//	      "Ashlee Harrison","HBA Legal","Perth","ashlee.harrison@hbalegal.com","" ,
//	      "Brett Ablong","HBA Legal","Perth","brett.ablong@hbalegal.com","Brett Ablong" ,
//	      "Michelle Togher","HBA Legal","Perth","michelle.togher@hbalegal.com","Brett Ablong" ,
//	      "Nathan Hepple","HBA Legal","Sydney","nathan.hepple@hbalegal.com","" ,
//	      "Fiona Smith","","Perth","fiona.smith@hbalegal.com","Brett Ablong" ,
//	      "Claire Tota","HBA Legal","Perth","claire.tota@hbalegal.com","Brett Ablong" ,
//	      "Melissa Wroe","HBA Legal","Perth","Melissa.Wroe@hbalegal.com","Brett Ablong" ,
//	      "Ruth Slater","HBA Legal","Perth","ruth.slater@hbalegal.com","Brett Ablong" ,
//	      "Rebecca Tloczek","HBA Legal","Perth","Rebecca.Tloczek@hbalegal.com","Brett Ablong" ,
//	      "Geoff Brookes","HBA Legal","Sydney","geoff.brookes@hbalegal.com","Brett Ablong" ,
//	      "Mario Raciti","HBA Legal","Sydney","mario.raciti@hbalegal.com","Geoff Brookes" ,
//	      "Katreena Belford","HBA Legal","Sydney","katreena.belford@hbalegal.com","Geoff Brookes" ,
//	      "Natasha Fiodoroff","HBA Legal","Sydney","Natasha.Fiodoroff@hbalegal.com","Nathan Hepple" ,
//	      "Iona Sjahadi","HBA Legal","Sydney","iona.sjahadi@hbalegal.com","Nathan Hepple" ,
//	      "Niksa Stanisic","HBA Legal","Sydney","nik.stanisic@hbalegal.com","Geoff Brookes" ,
//	      "Devina Addabbo","HBA Legal","Sydney","Devina.Addabbo@hbalegal.com","Nathan Hepple" ,
//	      "Alexandra Cameron","HBA Legal","Sydney","Alexandra.Cameron@hbalegal.com","Simone Joannou" ,
//	      "Rosan Santangelo","HBA Legal","Sydney","Rosan.Santangelo@hbalegal.com","Nathan Hepple" ,
//	      "Simone Joannou","HBA Legal","Sydney","Simone.Joannou@hbalegal.com","Nathan Hepple" ,
//	      "Jasmin Darroch-Dobbie","HBA Legal","Sydney","Jasmin.Darroch-Dobbie@hbalegal.com","Rosan Santangelo" ,
//	      "Lana Drummond","HBA Legal","Sydney","Lana.Drummond@hbalegal.com","Geoff Brookes" ,
//	      "Jason Francl","HBA Legal","Sydney","Jason.Francl@hbalegal.com","Rosan Santangelo" ,
//	      "James Makowiak","HBA Legal","Sydney","James.Makowiak@hbalegal.com","Nathan Hepple" ,
//	      "Joseph Constantine","HBA Legal","Sydney","Joseph.Constantine@hbalegal.com","Nathan Hepple" ,
//	      "James Baird","HBA Legal","Sydney","James.Baird@hbalegal.com","Brett Ablong" ,
//	      "Chris Murphy","HBA Legal","Brisbane","Chris.Murphy@hbalegal.com","Brett Ablong" ,
//	      "Sue Stevenson","HBA Legal","Brisbane","Sue.Stevenson@hbalegal.com","Chris Murphy" ,
//	      "Iva Peric","HBA Legal","Sydney","Iva.Peric@hbalegal.com","James Baird" ,
//	      "Liz Davey","HBA Legal","Brisbane","Liz.Davey@hbalegal.com","Chris Murphy" ,
//	      "Hamish Craib","HBA Legal","Brisbane","Hamish.Craib@hbalegal.com","Chris Murphy" ,
//	      "Eszter Little","Pillion","Newcastle West","eszter.little@pillionnow.com","Jessica Shaffer" ,
//	      "Annette Charlton","Pillion","Newcastle West","annette.charlton@pillionnow.com","Jessica Shaffer" ,
//	      "Lara Pepper","","Sydney","Lara.Pepper@hbalegal.com","" ,
//	      "Emily Gibson","Pillion","Newcastle West","Emily.Gibson@pillionnow.com","Jessica Shaffer" ,
//	      "Naomi Adams","HBA Legal","Perth","Naomi.Adams@hbalegal.com","" ,
//	      "Lauren McKeown","HBA Legal","Perth","Lauren.McKeown@hbalegal.com","Ruth Slater" ,
//	      "Courtney Alexander","HBA Legal","Perth","Courtney.Alexander@hbalegal.com","Ruth Slater" ,
//	      "Sean Turner","","Perth","Sean.Turner@hbalegal.com","" ,
//	      "Andrew Gulyas","HBA Legal","Sydney","Andrew.Gulyas@hbalegal.com","Nathan Hepple" ,
//	      "Sandra Raub","HBA Legal","Perth","Sandra.Raub@hbalegal.com","" ,
//	      "Richard Tempest","Pillion","Newcastle West","Richard.Tempest@pillionnow.com","Nathan Hepple" ,
//	      "Charlotte Wylie","Pillion","Newcastle West","Charlotte.Wylie@pillionnow.com","Jessica Shaffer" ,
//	      "Zachary Haas","Pillion","Newcastle West","Zachary.Haas@pillionnow.com","Jessica Shaffer" ,
//	      "Madelaine Lu","HBA Legal","Sydney","Madelaine.Lu@hbalegal.com","Geoff Brookes" ,
//	      "Sean Hayes","","","Sean.Hayes@paratusclaims.com","" ,
//	      "Craig Lee","","Perth","Craig.Lee@hbalegal.com","Brett Ablong" ,
//	      "Nicholas Powe","HBA Legal","Perth","nicholas.powe@hbalegal.com","" ,
//	      "Emily Wood","Pillion","Newcastle West","emily.wood@pillionnow.com","Jessica Shaffer" ,
//	      "Jessica Shaffer","Pillion","Newcastle West","Jessica.Shaffer@pillionnow.com","Sean Turner" ,
//	      "Hamshini Sathiyamoorthy","HBA Legal","Sydney","Hamshini.Sathiyamoorthy@hbalegal.com","Simone Joannou" ,
//	      "Josh Murphy","","","Josh.Murphy@paratusclaims.com","" ,
//	      "Ashlee Carter","Pillion","Newcastle West","Ashlee.Carter@pillionnow.com","Jessica Shaffer" ,
//	      "Tanya Turner","HBA Legal","Perth","Tanya.Turner@hbalegal.com","Ruth Slater" ,
//	      "Vicky Teichman","","","Vicky.Teichman@paratusclaims.com","" ,
//	      "Nathan Ogden","","","Nathan.Ogden@pillionnow.com","Craig Lee" ,
//	      "Riley Turner","","","Riley.Turner@hbalegal.com","" ,
//	      "Kaylie Adams","","","Kaylie.Adams@hbalegal.com","" ,
//	      "Tadijana Ilicic","Pillion","","Tadijana.Ilicic@pillionnow.com","Jessica Shaffer" ,
//	      "Bella McNamee","","","Bella.McNamee@hbalegal.com","" ,
//	      "Danica Kemp","","","Danica.Kemp@hbalegal.com","" ,
//	      "Kellie Hayman","","","Kellie.Hayman@hbalegal.com","Claire Tota" ,
//	      "Marianne Schwartz","","","Marianne.Schwartz@hbalegal.com","" ,
//	      "Yen Tan","","","Yen.Tan@hbalegal.com","" ,
//	      "Petenei Adcock","","","Petenei.Adcock@hbalegal.com","" ,
//	      "Mitchell McMaugh","Pillion","","Mitchell.McMaugh@pillionnow.com","Craig Lee" ,
//	      "David De Santa-Ana","","","","" ,
//	      "Ebony Aughey","","","Ebony.Aughey@hbalegal.com","" ,
//	      "Sarah Tempest","","","Sarah.Tempest@hbalegal.com","Brett Ablong" ,
//	      "Elizabeth Stoten","HBA Legal","","Elizabeth.Stoten@hbalegal.com","Mario Raciti" ,
//	      "Emily Gostelow","","","Emily.Gostelow@hbalegal.com","Brett Ablong" ,
//	      "Andrew Lu","","","Andrew.Lu@hbalegal.com","" ,
//	      "Kayla Johnstone","","","Kayla.Johnstone@hbalegal.com","" ,
//	      "Nimi Karunakaram","","","Nimi.Karunakaram@hbalegal.com","Fiona Smith" ,
//	      "Mitchell Kowalski","","","","" ,
//	      "Toby Whyte","","","Toby.Whyte@hbalegal.com","" ,
//	      "Michaela Broughton","","","Michaela.Broughton@pillionnow.com","Jessica Shaffer" ,
//	      "Matthew Kudla","","","Matthew.Kudla@hbalegal.com","Simone Joannou" ,
//	      "Mauricio Quiroga","","","Mauricio.Quiroga@pillionnow.com","" ,
//	      "Allegra Thomas","HBA Legal","","Allegra.Thomas@hbalegal.com","Chris Murphy"};
	      
	      String[] employee = {
	    	      "Eszter Little","Pillion","Newcastle West","eszter.little@pillionnow.com","Jessica Shaffer" ,
	    	      "Annette Charlton","Pillion","Newcastle West","annette.charlton@pillionnow.com","Jessica Shaffer" ,
	    	      "Emily Gibson","Pillion","Newcastle West","Emily.Gibson@pillionnow.com","Jessica Shaffer" ,
	    	      "Sean Turner","","Perth","Sean.Turner@hbalegal.com","" ,
	    	      "Richard Tempest","Pillion","Newcastle West","Richard.Tempest@pillionnow.com","Sean Turner" ,
	    	      "Charlotte Wylie","Pillion","Newcastle West","Charlotte.Wylie@pillionnow.com","Jessica Shaffer" ,
	    	      "Zachary Haas","Pillion","Newcastle West","Zachary.Haas@pillionnow.com","Jessica Shaffer" ,
	    	      "Craig Lee","","Perth","Craig.Lee@hbalegal.com","" ,
	    	      "Emily Wood","Pillion","Newcastle West","emily.wood@pillionnow.com","Jessica Shaffer" ,
	    	      "Jessica Shaffer","Pillion","Newcastle West","Jessica.Shaffer@pillionnow.com","Sean Turner" ,
	    	      "Ashlee Carter","Pillion","Newcastle West","Ashlee.Carter@pillionnow.com","Jessica Shaffer" ,
	    	      "Nathan Ogden","","","Nathan.Ogden@pillionnow.com","Craig Lee" ,
	    	      "Tadijana Ilicic","Pillion","","Tadijana.Ilicic@pillionnow.com","Jessica Shaffer" ,
	    	      "Mitchell McMaugh","Pillion","","Mitchell.McMaugh@pillionnow.com","Craig Lee" ,
	    	      "Michaela Broughton","","","Michaela.Broughton@pillionnow.com","Jessica Shaffer" ,
	    	      "Mauricio Quiroga","","","Mauricio.Quiroga@pillionnow.com","Craig Lee" };


	      dataTable.addRows(employee.length/5);
	      
	      
	      
	      
	      Integer column = 0;
	      Integer row = 0;
	      Integer index = 0;
	      
	     
	      
	      while (index < employee.length) {
	    	  while (column < 5) {
//	    		  Window.confirm("Row = " + String.valueOf(row) + ", " + "Column = " + String.valueOf(column) + ", " + "Value: " + employee[index]);
	    		  if (column != 1 || column != 2) {
	    			  if (column == 0) {
	    				  dataTable.setValue(row, 0, employee[index]);
	    			  }
	    			  if (column == 4) {
	    				  dataTable.setValue(row, 1, employee[index]);
	    			  }
	    			  if (column == 3) {
	    				  dataTable.setValue(row, 2, employee[index]);
	    			  }
	    		  }
	    		  column = column + 1;
	    		  index = index + 1;
	    		  
	    	  }
	    	  column = 0;
	    	  row = row + 1;
	    	  
	    	  
	      }
	      //Row Column
//	      dataTable.getValueString(0, 0);
//	      dataTable.getValueString(0, 1);
//	      dataTable.getValueString(0, 2);
//	      dataTable.getValueString(0, 3);
//	      dataTable.getValueString(0, 4);
	      
//	      Window.confirm(dataTable.getValueString(0, 0) + 
//	      dataTable.getValueString(0, 1) +
//	      dataTable.getValueString(0, 2) +
//	      dataTable.getValueString(0, 3) +
//	      dataTable.getValueString(0, 4));
//	      
//	      Window.confirm(dataTable.getValueString(1, 0) + 
//	    	      dataTable.getValueString(1, 1) +
//	    	      dataTable.getValueString(1, 2) +
//	    	      dataTable.getValueString(1, 3) +
//	    	      dataTable.getValueString(1, 4));
	      
	      
//	      int[] inties = new int[] {2, 4, 5};
	      
	
	      PatternFormat format = PatternFormat.create("{0} <br /> {1}");
	      format.format(dataTable, 0, 2);
	     

	      // Set options
	      OrgChartOptions options = OrgChartOptions.create();
	      options.setAllowHtml(true);
	      options.setAllowCollapse(true);
	      
	      
	      

	      // Draw the chart
	      chart.draw(dataTable, options);
	      chart.setWidth("400px");
	      chart.setHeight("400px");
	      
	      RootPanel.get("two").add(chart);
	   }
	 
	}
	

