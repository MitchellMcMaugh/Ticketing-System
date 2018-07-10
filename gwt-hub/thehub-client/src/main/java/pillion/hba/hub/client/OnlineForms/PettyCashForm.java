package pillion.hba.hub.client.OnlineForms;

import java.util.Date;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.event.dom.client.ClickHandler;

public class PettyCashForm {
	public static FlexTable trustToOfficeTransferForm(String status, FlexTable gridPC) {
		if (status != "Delete") {
			//Trust To Office Transfer Form Labels
			// (int row, int column)
			
			//Client Name
			Label PCClientNameLabel = new Label();
			PCClientNameLabel.setText("Client Name");
			TextBox PCClientName = new TextBox();
			gridPC.setWidget(2, 1, PCClientNameLabel);
			gridPC.setWidget(2, 2, PCClientName);
			
			//Matter Number
			Label PCMatterNumberLabel = new Label();
			PCMatterNumberLabel.setText("Matter Number");
			TextBox PCMatterNumber = new TextBox();
			gridPC.setWidget(4, 1, PCMatterNumberLabel);
			gridPC.setWidget(4, 2, PCMatterNumber);
			
			//Matter Name
			Label PCMatterNameLabel = new Label();
			PCMatterNameLabel.setText("Matter Name");
			TextBox PCMatterName = new TextBox();
			gridPC.setWidget(6, 1, PCMatterNameLabel);
			gridPC.setWidget(6, 2, PCMatterName);
			
			//Supplier
			Label PCSupplierLabel = new Label();
			PCSupplierLabel.setText("Supplier");
			TextBox PCSupplier = new TextBox();
			gridPC.setWidget(8, 1, PCSupplierLabel);
			gridPC.setWidget(8, 2, PCSupplier);
			
			//Description
			Label PCDescriptionLabel = new Label();
			PCDescriptionLabel.setText("Description");
			TextBox PCDescription = new TextBox();
			gridPC.setWidget(10, 1, PCDescriptionLabel);
			gridPC.setWidget(10, 2, PCDescription);
			
			//Total Amount
			Label PCAmountLabel = new Label();
			PCAmountLabel.setText("Total Amount");
			TextBox PCAmount = new TextBox();
			gridPC.setWidget(12, 1, PCAmountLabel);
			gridPC.setWidget(12, 2, PCAmount);
		
		}
		if (status == "Delete") {
			//Remove Grid
			gridPC.clear();
			RootPanel.get("two").remove(gridPC);
			RootPanel.get("two").clear();
				}
			return gridPC;
		}
}
