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
	      dataTable.addColumn(ColumnType.STRING, "" );
	      dataTable.addColumn(ColumnType.STRING, "SID");
	      dataTable.addColumn(ColumnType.STRING, "CN");
	      dataTable.addColumn(ColumnType.STRING, "Company");
	      dataTable.addColumn(ColumnType.STRING, "City");
	      dataTable.addColumn(ColumnType.STRING, "EmailAddress");
	      dataTable.addColumn(ColumnType.STRING, "Manager");
	      dataTable.addColumn(ColumnType.STRING, "Combo");
	      
	    	 
	      
	      
	      String[] employee = {"1","S-1-5-21-1507308509-937497195-2021582516-1303","Courtney Burrows","HBA Legal","Perth","Courtney.Burrows@hbalegal.com","Ruth Slater",
	      "2","S-1-5-21-1507308509-937497195-2021582516-1169","Ashlee Harrison","HBA Legal","Perth","ashlee.harrison@hbalegal.com","" ,
	      "3","S-1-5-21-1507308509-937497195-2021582516-1164","Brett Ablong","HBA Legal","Perth","brett.ablong@hbalegal.com","Brett Ablong" ,
	      "4","S-1-5-21-1507308509-937497195-2021582516-1168","Michelle Togher","HBA Legal","Perth","michelle.togher@hbalegal.com","Brett Ablong" ,
	      "5","S-1-5-21-1507308509-937497195-2021582516-1185","Nathan Hepple","HBA Legal","Sydney","nathan.hepple@hbalegal.com","" ,
	      "6","S-1-5-21-1507308509-937497195-2021582516-1188","Fiona Smith","","Perth","fiona.smith@hbalegal.com","Cath Cipro" ,
	      "7","S-1-5-21-1507308509-937497195-2021582516-1193","Claire Tota","HBA Legal","Perth","claire.tota@hbalegal.com","Brett Ablong" ,
	      "8","S-1-5-21-1507308509-937497195-2021582516-1202","Melissa Wroe","HBA Legal","Perth","Melissa.Wroe@hbalegal.com","Mark Birbeck" ,
	      "9","S-1-5-21-1507308509-937497195-2021582516-1265","Ruth Slater","HBA Legal","Perth","ruth.slater@hbalegal.com","Brett Ablong" ,
	      "10","S-1-5-21-1507308509-937497195-2021582516-1271","Rebecca Tloczek","HBA Legal","Perth","Rebecca.Tloczek@hbalegal.com","Brett Ablong" ,
	      "11","S-1-5-21-1507308509-937497195-2021582516-1274","Geoff Brookes","HBA Legal","Sydney","geoff.brookes@hbalegal.com","Brett Ablong" ,
	      "12","S-1-5-21-1507308509-937497195-2021582516-1275","Mario Raciti","HBA Legal","Sydney","mario.raciti@hbalegal.com","Geoff Brookes" ,
	      "13","S-1-5-21-1507308509-937497195-2021582516-1276","Katreena Belford","HBA Legal","Sydney","katreena.belford@hbalegal.com","Geoff Brookes" ,
	      "14","S-1-5-21-1507308509-937497195-2021582516-1281","Natasha Fiodoroff","HBA Legal","Sydney","Natasha.Fiodoroff@hbalegal.com","Nathan Hepple" ,
	      "15","S-1-5-21-1507308509-937497195-2021582516-1282","Iona Sjahadi","HBA Legal","Sydney","iona.sjahadi@hbalegal.com","Nathan Hepple" ,
	      "16","S-1-5-21-1507308509-937497195-2021582516-1286","Niksa Stanisic","HBA Legal","Sydney","nik.stanisic@hbalegal.com","Geoff Brookes" ,
	      "17","S-1-5-21-1507308509-937497195-2021582516-2626","Devina Addabbo","HBA Legal","Sydney","Devina.Addabbo@hbalegal.com","Nathan Hepple" ,
	      "18","S-1-5-21-1507308509-937497195-2021582516-2628","Alexandra Cameron","HBA Legal","Sydney","Alexandra.Cameron@hbalegal.com","Simone Joannou" ,
	      "19","S-1-5-21-1507308509-937497195-2021582516-2633","Rosan Santangelo","HBA Legal","Sydney","Rosan.Santangelo@hbalegal.com","Nathan Hepple" ,
	      "20","S-1-5-21-1507308509-937497195-2021582516-2634","Simone Joannou","HBA Legal","Sydney","Simone.Joannou@hbalegal.com","Nathan Hepple" ,
	      "21","S-1-5-21-1507308509-937497195-2021582516-3131","Jasmin Darroch-Dobbie","HBA Legal","Sydney","Jasmin.Darroch-Dobbie@hbalegal.com","Rosan Santangelo" ,
	      "22","S-1-5-21-1507308509-937497195-2021582516-3135","Lana Drummond","HBA Legal","Sydney","Lana.Drummond@hbalegal.com","Geoff Brookes" ,
	      "23","S-1-5-21-1507308509-937497195-2021582516-2646","Jason Francl","HBA Legal","Sydney","Jason.Francl@hbalegal.com","Rosan Santangelo" ,
	      "24","S-1-5-21-1507308509-937497195-2021582516-2647","James Makowiak","HBA Legal","Sydney","James.Makowiak@hbalegal.com","Nathan Hepple" ,
	      "25","S-1-5-21-1507308509-937497195-2021582516-2648","Joseph Constantine","HBA Legal","Sydney","Joseph.Constantine@hbalegal.com","Nathan Hepple" ,
	      "26","S-1-5-21-1507308509-937497195-2021582516-2649","James Baird","HBA Legal","Sydney","James.Baird@hbalegal.com","Brett Ablong" ,
	      "27","S-1-5-21-1507308509-937497195-2021582516-1635","Chris Murphy","HBA Legal","Brisbane","Chris.Murphy@hbalegal.com","Brett Ablong" ,
	      "28","S-1-5-21-1507308509-937497195-2021582516-2653","Sue Stevenson","HBA Legal","Brisbane","Sue.Stevenson@hbalegal.com","Chris Murphy" ,
	      "29","S-1-5-21-1507308509-937497195-2021582516-2668","Iva Peric","HBA Legal","Sydney","Iva.Peric@hbalegal.com","James Baird" ,
	      "30","S-1-5-21-1507308509-937497195-2021582516-2686","Liz Davey","HBA Legal","Brisbane","Liz.Davey@hbalegal.com","Chris Murphy" ,
	      "31","S-1-5-21-1507308509-937497195-2021582516-2687","Hamish Craib","HBA Legal","Brisbane","Hamish.Craib@hbalegal.com","Chris Murphy" ,
	      "32","S-1-5-21-1507308509-937497195-2021582516-3149","Eszter Little","Pillion","Newcastle West","eszter.little@pillionnow.com","Jessica Shaffer" ,
	      "33","S-1-5-21-1507308509-937497195-2021582516-3151","Annette Charlton","Pillion","Newcastle West","annette.charlton@pillionnow.com","Jessica Shaffer" ,
	      "34","S-1-5-21-1507308509-937497195-2021582516-2710","Lara Pepper","","Sydney","Lara.Pepper@hbalegal.com","" ,
	      "35","S-1-5-21-1507308509-937497195-2021582516-3154","Emily Gibson","Pillion","Newcastle West","Emily.Gibson@pillionnow.com","Jessica Shaffer" ,
	      "36","S-1-5-21-1507308509-937497195-2021582516-3157","Naomi Adams","HBA Legal","Perth","Naomi.Adams@hbalegal.com","" ,
	      "37","S-1-5-21-1507308509-937497195-2021582516-2732","Lauren McKeown","HBA Legal","Perth","Lauren.McKeown@hbalegal.com","Ruth Slater" ,
	      "38","S-1-5-21-1507308509-937497195-2021582516-2737","Courtney Alexander","HBA Legal","Perth","Courtney.Alexander@hbalegal.com","Ruth Slater" ,
	      "39","S-1-5-21-1507308509-937497195-2021582516-2746","Sean Turner","","Perth","Sean.Turner@hbalegal.com","" ,
	      "40","S-1-5-21-1507308509-937497195-2021582516-3159","Andrew Gulyas","HBA Legal","Sydney","Andrew.Gulyas@hbalegal.com","Nathan Hepple" ,
	      "41","S-1-5-21-1507308509-937497195-2021582516-2747","Sandra Raub","HBA Legal","Perth","Sandra.Raub@hbalegal.com","" ,
	      "42","S-1-5-21-1507308509-937497195-2021582516-3164","Richard Tempest","Pillion","Newcastle West","Richard.Tempest@pillionnow.com","Nathan Hepple" ,
	      "43","S-1-5-21-1507308509-937497195-2021582516-2751","Charlotte Wylie","Pillion","Newcastle West","Charlotte.Wylie@pillionnow.com","Jessica Shaffer" ,
	      "44","S-1-5-21-1507308509-937497195-2021582516-2752","Zachary Haas","Pillion","Newcastle West","Zachary.Haas@pillionnow.com","Jessica Shaffer" ,
	      "45","S-1-5-21-1507308509-937497195-2021582516-2755","Madelaine Lu","HBA Legal","Sydney","Madelaine.Lu@hbalegal.com","Geoff Brookes" ,
	      "46","S-1-5-21-1507308509-937497195-2021582516-2756","Sean Hayes","","","Sean.Hayes@paratusclaims.com","" ,
	      "47","S-1-5-21-1507308509-937497195-2021582516-2761","Craig Lee","","Perth","Craig.Lee@hbalegal.com","Brett Ablong" ,
	      "48","S-1-5-21-1507308509-937497195-2021582516-2764","Nicholas Powe","HBA Legal","Perth","nicholas.powe@hbalegal.com","" ,
	      "49","S-1-5-21-1507308509-937497195-2021582516-2768","Emily Wood","Pillion","Newcastle West","emily.wood@pillionnow.com","Jessica Shaffer" ,
	      "50","S-1-5-21-1507308509-937497195-2021582516-2769","Jessica Shaffer","Pillion","Newcastle West","Jessica.Shaffer@pillionnow.com","Sean Turner" ,
	      "51","S-1-5-21-1507308509-937497195-2021582516-2770","Hamshini Sathiyamoorthy","HBA Legal","Sydney","Hamshini.Sathiyamoorthy@hbalegal.com","Simone Joannou" ,
	      "52","S-1-5-21-1507308509-937497195-2021582516-2774","Josh Murphy","","","Josh.Murphy@paratusclaims.com","" ,
	      "53","S-1-5-21-1507308509-937497195-2021582516-2781","Ashlee Carter","Pillion","Newcastle West","Ashlee.Carter@pillionnow.com","Jessica Shaffer" ,
	      "54","S-1-5-21-1507308509-937497195-2021582516-2784","Tanya Turner","HBA Legal","Perth","Tanya.Turner@hbalegal.com","Ruth Slater" ,
	      "55","S-1-5-21-1507308509-937497195-2021582516-2787","Vicky Teichman","","","Vicky.Teichman@paratusclaims.com","" ,
	      "56","S-1-5-21-1507308509-937497195-2021582516-2789","Nathan Ogden","","","Nathan.Ogden@pillionnow.com","Craig Lee" ,
	      "57","S-1-5-21-1507308509-937497195-2021582516-2801","Riley Turner","","","Riley.Turner@hbalegal.com","" ,
	      "58","S-1-5-21-1507308509-937497195-2021582516-2816","Kaylie Adams","","","Kaylie.Adams@hbalegal.com","" ,
	      "59","S-1-5-21-1507308509-937497195-2021582516-2826","Tadijana Ilicic","Pillion","","Tadijana.Ilicic@pillionnow.com","Jessica Shaffer" ,
	      "60","S-1-5-21-1507308509-937497195-2021582516-2827","Bella McNamee","","","Bella.McNamee@hbalegal.com","" ,
	      "61","S-1-5-21-1507308509-937497195-2021582516-2831","Danica Kemp","","","Danica.Kemp@hbalegal.com","" ,
	      "62","S-1-5-21-1507308509-937497195-2021582516-2832","Kellie Hayman","","","Kellie.Hayman@hbalegal.com","Claire Tota" ,
	      "63","S-1-5-21-1507308509-937497195-2021582516-2836","Marianne Schwartz","","","Marianne.Schwartz@hbalegal.com","" ,
	      "64","S-1-5-21-1507308509-937497195-2021582516-2837","Yen Tan","","","Yen.Tan@hbalegal.com","" ,
	      "65","S-1-5-21-1507308509-937497195-2021582516-2838","Petenei Adcock","","","Petenei.Adcock@hbalegal.com","" ,
	      "66","S-1-5-21-1507308509-937497195-2021582516-2842","Mitchell McMaugh","Pillion","","Mitchell.McMaugh@pillionnow.com","Craig Lee" ,
	      "67","S-1-5-21-1507308509-937497195-2021582516-2850","David De Santa-Ana","","","","" ,
	      "68","S-1-5-21-1507308509-937497195-2021582516-2853","Ebony Aughey","","","Ebony.Aughey@hbalegal.com","" ,
	      "69","S-1-5-21-1507308509-937497195-2021582516-2858","Sarah Tempest","","","Sarah.Tempest@hbalegal.com","Brett Ablong" ,
	      "70","S-1-5-21-1507308509-937497195-2021582516-2859","Elizabeth Stoten","HBA Legal","","Elizabeth.Stoten@hbalegal.com","Mario Raciti" ,
	      "71","S-1-5-21-1507308509-937497195-2021582516-2861","Emily Gostelow","","","Emily.Gostelow@hbalegal.com","Brett Ablong" ,
	      "72","S-1-5-21-1507308509-937497195-2021582516-2863","Andrew Lu","","","Andrew.Lu@hbalegal.com","" ,
	      "73","S-1-5-21-1507308509-937497195-2021582516-2864","Kayla Johnstone","","","Kayla.Johnstone@hbalegal.com","" ,
	      "74","S-1-5-21-1507308509-937497195-2021582516-2865","Nimi Karunakaram","","","Nimi.Karunakaram@hbalegal.com","Fiona Smith" ,
	      "75","S-1-5-21-1507308509-937497195-2021582516-2867","Mitchell Kowalski","","","","" ,
	      "76","S-1-5-21-1507308509-937497195-2021582516-2870","Toby Whyte","","","Toby.Whyte@hbalegal.com","" ,
	      "77","S-1-5-21-1507308509-937497195-2021582516-2871","Michaela Broughton","","","Michaela.Broughton@pillionnow.com","Jessica Shaffer" ,
	      "78","S-1-5-21-1507308509-937497195-2021582516-2872","Matthew Kudla","","","Matthew.Kudla@hbalegal.com","Simone Joannou" ,
	      "79","S-1-5-21-1507308509-937497195-2021582516-2873","Mauricio Quiroga","","","Mauricio.Quiroga@pillionnow.com","" ,
	      "80","S-1-5-21-1507308509-937497195-2021582516-3201","Allegra Thomas","HBA Legal","","Allegra.Thomas@hbalegal.com","Chris Murphy"};

	      dataTable.addRows(employee.length);
	      
	      
	      
	      
	      Integer column = 0;
	      Integer row = 0;
	      Integer index = 0;
	      
	     
	      
	      while (index < employee.length) {
	    	  while (column < 7) {
//	    		  Window.confirm("Row = " + String.valueOf(row) + ", " + "Column = " + String.valueOf(column) + ", " + "Value: " + employee[index]);
	    		  dataTable.setValue(row, column, employee[index]);
	    		  column = column + 1;
	    		  index = index + 1;
	    		  
	    	  }
	    	  column = 0;
	    	  row = row + 1;
	    	  //Window.confirm(String.valueOf(row));
	    	  
	      }
	      
	      dataTable.removeColumn(7);
	      dataTable.removeColumn(5);
	      dataTable.removeColumn(4);
	      dataTable.removeColumn(3);
	      dataTable.removeColumn(1);
	      dataTable.removeColumn(0);
	      
	      
//	      int[] inties = new int[] {2, 4, 5};
//	      PatternFormat format = PatternFormat.create("{0} {1} {2}");
//	      format.format(dataTable, 2, 4, 5);
	      
	      
	      
	     

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
	

