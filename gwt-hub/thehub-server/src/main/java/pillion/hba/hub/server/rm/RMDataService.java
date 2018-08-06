package pillion.hba.hub.server.rm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pillion.hba.hub.server.wp.WPDataService;
import pillion.hba.hub.shared.Comment;

public class RMDataService {

	public static Collection<Comment> getJournals(int issueID) {
		Collection<Comment> comments = new ArrayList();
		try {
			String query = "SELECT users.firstname, users.lastname, journals.* FROM redmine.users INNER JOIN redmine.journals ON redmine.users.id = redmine.journals.user_id WHERE journalized_id = " + issueID;
		    Optional<Connection> connOptional = dbConnection();
		    if (connOptional.isPresent()) {
		    	Connection conn = connOptional.get();
			    Statement st = conn.createStatement();
			    
			    ResultSet rs = st.executeQuery(query);
			    
			    while (rs.next())
			    { 
			        String notes = rs.getString("notes");
			        
			        Date dateDate = rs.getDate("created_on");
			        Calendar calDate = Calendar.getInstance();
			        calDate.setTime(dateDate);
			        LocalDate date = LocalDate.of(calDate.get(Calendar.YEAR), calDate.get(Calendar.MONTH) + 1, calDate.get(Calendar.DAY_OF_MONTH));
			        
			        Date dateTime = rs.getTime("created_on");
			        Calendar calTime = Calendar.getInstance();
			        calTime.setTime(dateTime);
			        LocalTime time = LocalTime.of(calTime.get(Calendar.HOUR), calTime.get(Calendar.MINUTE), calTime.get(Calendar.SECOND));
			        LocalDateTime dateTimeDate = LocalDateTime.of(date, time);

			        Instant instant = dateTimeDate.toInstant(ZoneOffset.UTC);
			        
			        Date dateTimeDateDate = Date.from(instant);
			        
			        boolean privateNotes = rs.getBoolean("private_notes");
			        String firstname = rs.getString("firstname");
			        String lastname = rs.getString("lastname");
			        
			        if (!privateNotes) {
				        Comment comment = new Comment();
						comment.setLogged(dateTimeDateDate);
						comment.setComment(notes);
						comment.setUser(firstname + " " + lastname);
					
						Optional<String> userURL = WPDataService.userImageURL(comment.getUser().replaceAll("\\s+",""));
						if (userURL.isPresent()) {
							String urlSearchParameter = "i:250;";
							int indexStart = ordinalIndexOf(userURL.get(), urlSearchParameter , 1);
							String substring1 = userURL.get().substring(indexStart + urlSearchParameter.length() + 1);
							String urlSearchParameter2 = "-250x250.jpg";
							int indexEnd = ordinalIndexOf(substring1, urlSearchParameter2, 1);
							String substring2 = substring1.substring(0, indexEnd + urlSearchParameter2.length());
							Pattern r = Pattern.compile("((?:http|https)(?::\\/{2}[\\w]+)(?:[\\/|\\.]?)(?:[^\\s\\\"]*))");
							String data = "";
							Matcher m = r.matcher(substring2);
							if (m.find()) {
								 data = m.group(0);
							}	
							comment.setImageUrl(data);
						}
						comments.add(comment);
			        }
			    }
			    conn.close();
			    return comments;
			}
		}
		catch(Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		    return comments;
		}
		return comments;
	}
	
	
	public static Optional<Connection> dbConnection() {
		try {
			String myDriver = "com.mysql.cj.jdbc.Driver";
		    String myUrl = "jdbc:mysql://192.168.2.160/redmine";
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, "redmine", "ieR_ee7dahKi");
		    return Optional.of(conn);
		}
		catch(Exception e) {
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return Optional.empty();
	}
	
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}
}
