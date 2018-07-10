package pillion.hba.hub.client.OnlineForms;

import java.util.Date;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;

public class HBAGivingProgramForm {
	public static FlexTable trustToOfficeTransferForm(String status, FlexTable gridHBAGP) {
		if (status != "Delete") {
			//Trust To Office Transfer Form Labels
			// (int row, int column)
			
			//Your Name DropDownList - Pull from Wordpress
			Label HBAGPYourNameLabel = new Label();
			HBAGPYourNameLabel.setText("Your Name");
			ListBox HBAGPYourName = new ListBox();
			HBAGPYourName.addItem("Mitchell McMaugh");
			gridHBAGP.setWidget(2, 1, HBAGPYourNameLabel);
			gridHBAGP.setWidget(2, 2, HBAGPYourName);
			
			//Your Supervising Partner/ Managers Name TextBox - Pull from Active Directory/ Wordpress
			Label HBAGPManagerNameLabel = new Label();
			HBAGPManagerNameLabel.setText("Your Manager's Name");
			TextBox HBAGPManagerName = new TextBox();
			gridHBAGP.setWidget(4, 1, HBAGPManagerNameLabel);
			gridHBAGP.setWidget(4, 2, HBAGPManagerName);
			
			//Is your SP/ Manager aware of this application Radio Button - Yes/ No
			Label HBAGPIsManagerAwareLabel = new Label();
			HBAGPIsManagerAwareLabel.setText("Is your SP? Manager aware of this application?");
			RadioButton HBAGPIsManagerAwareYes = new RadioButton("HBAGPIsManagerAwareGroup", "Yes");
			RadioButton HBAGPIsManagerAwareNo = new RadioButton("HBAGPIsManagerAwareGroup", "No");
			gridHBAGP.setWidget(6, 1, HBAGPIsManagerAwareLabel);
			gridHBAGP.setWidget(6, 2, HBAGPIsManagerAwareYes);
			gridHBAGP.setWidget(6, 3, HBAGPIsManagerAwareNo);
			
			//Name of organisation you wish to support Textbox
			Label HBAGPNameOfOrganisationLabel = new Label();
			HBAGPNameOfOrganisationLabel.setText("Name of organisation you wish to support Textbox");
			TextBox HBAGPNameOfOrganisation = new TextBox();
			gridHBAGP.setWidget(8, 1, HBAGPNameOfOrganisationLabel);
			gridHBAGP.setWidget(8, 2, HBAGPNameOfOrganisation);
			
			//Has this request come via a client? If so provide details here Radio Button Yes/ No, Expand into Text Area if Yes
			Label HBAGPHasThisRequestComeViaAClientLabel = new Label();
			HBAGPHasThisRequestComeViaAClientLabel.setText("Has this request come via a client?");
			RadioButton HBAGPHasThisRequestComeViaAClientYes = new RadioButton("HBAGPHasThisRequestComeViaAClientGroup", "Yes");
			RadioButton HBAGPHasThisRequestComeViaAClientNo = new RadioButton("HBAGPHasThisRequestComeViaAClientGroup", "No");
			gridHBAGP.setWidget(10, 1, HBAGPHasThisRequestComeViaAClientLabel);
			gridHBAGP.setWidget(10, 2, HBAGPHasThisRequestComeViaAClientYes);
			gridHBAGP.setWidget(10, 3, HBAGPHasThisRequestComeViaAClientNo);
			
			//HBA Giving Program Category it fits with - Checklist
			Label HBAGPProgramCategoryLabel = new Label();
			HBAGPProgramCategoryLabel.setText("HBA Giving Program Category it fits with");
			CheckBox HBAGPGenerous = new CheckBox("Generous (eg donations, local activities)");
			CheckBox HBAGPCreative = new CheckBox("Creative (eg pro bono, guest lecture)");
			CheckBox HBAGPGenuine = new CheckBox("Genuine (bigger, more on-going project)");
			CheckBox HBAGPCollaborative = new CheckBox("Collaborative (related to out national partner)");
			gridHBAGP.setWidget(12, 1, HBAGPProgramCategoryLabel);
			gridHBAGP.setWidget(13, 1, HBAGPGenerous);
			gridHBAGP.setWidget(14, 1, HBAGPCreative);
			gridHBAGP.setWidget(15, 1, HBAGPGenuine);
			gridHBAGP.setWidget(16, 1, HBAGPCollaborative);
			
			//Timing Label
			Label HBAGPAmountLabel = new Label();
			HBAGPAmountLabel.setText("Total Amount");
			TextBox HBAGPAmount = new TextBox();
			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
			gridHBAGP.setWidget(12, 2, HBAGPAmount);
			
			//When is the event? TextBox/ DateBox
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//When do you need a response by (from the MC)? Textbox/ DateBox
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Event/ Activity details Label
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Activity details TextArea
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Activity Date DateBox
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Activity Location TextBox
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Activity Notes TextArea
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Who will be involved with this activity? Label
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Internal TextArea
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//External TextArea
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Put Forward YOur Case for supporting this venture How does this fit with the HBA Giving Program? Why should we do it? TextArea
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Cost To HBA Label
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Time? How much time will you and your colleagues need to commit to this? TextBox
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Funds? How much money are you asking HBA to commit to this? TextBox
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Do you need any promotional material to support this? Eg giveaways, something to wear etc TextBox
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//Communication Plan How will you share the news about HBA’s participation in this activity? Label/ Check List
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
//			
//			//By completing this action plan, you agree to undertake the tasks and promote HBA’s involvement in this cause. Label
//			Label HBAGPAmountLabel = new Label();
//			HBAGPAmountLabel.setText("Total Amount");
//			TextBox HBAGPAmount = new TextBox();
//			gridHBAGP.setWidget(12, 1, HBAGPAmountLabel);
//			gridHBAGP.setWidget(12, 2, HBAGPAmount);
			
			
		
		}
		if (status == "Delete") {
			//Remove Grid
			gridHBAGP.clear();
			RootPanel.get("two").remove(gridHBAGP);
			RootPanel.get("two").clear();
				}
			return gridHBAGP;
		}
}

