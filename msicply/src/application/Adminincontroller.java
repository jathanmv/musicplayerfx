package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class Adminincontroller {
	
	
	static Connection connection;
	
	@FXML
	JFXButton addartistbtn;
	
	@FXML
	JFXButton addalbumbtn;
	
	@FXML
	JFXButton addnewsongbtn;
	
	@FXML
	JFXButton editsongs;
	
	@FXML
	JFXButton editalbums;
	
	@FXML
	JFXButton editartist;
	
	@FXML
	JFXButton deletesbgbtn; 
	
	@FXML
	JFXButton updatesngbtn; 
	
	
	@FXML
	JFXButton addartistbtnin;
	
	@FXML
	JFXButton addalbumbtnin;
	
	@FXML
	JFXButton delalbumbtn;
	
	@FXML
	Button updtokbtn;
	
	@FXML
	TextField updtartistidfield;
	
	@FXML
	TextField updateartistnmfield;
	
	@FXML
	Label updatelabel;
	
	@FXML
	Label enterartistlabel;
	
	@FXML
	Label enteralbumlabel;
	
	@FXML
	Label updtartlabel;
	
	@FXML
	Label updtartnmlabel;
	
	@FXML
	TextField artistfield;
	
	@FXML
	TextField albumnmfield;
	
	@FXML
	TextField updtalbumfield;
	
	@FXML
	TextField updtalbmnmfield;
	
	@FXML
	Label updtalbumlabel;
	
	@FXML
	Label updtalbmnmlabel;
	
	@FXML
	VBox vboxx;
	
	@FXML
	TableView<Song> edsongtable;
	
	@FXML
	TableColumn<Song,String> editdeletesongtable;
	
	@FXML
	TextField updttrackidfield;
	
	@FXML
	TextField updttracknamefield;
	
	@FXML
	TextField updtpathnmfield;
	
	@FXML
	Label updttrackidlabel;
	
	@FXML
	Label updttracknmlabel;
	
	@FXML
	Label updtpathlbl;
	
	@FXML
	TextField insertsongfield;
	
	@FXML
	TextField insertalbumnmfield;
	
	@FXML
	TextField pathfieldinsert;
	
	@FXML
	TextField artistnminsert;
	
	@FXML
	Label addnewtrlabel;
	
	@FXML
	JFXButton insertok;
	
	@FXML
	TextField addnewalbmfield;
	
	@FXML
	TextField addartistnewalbumfield;
	
	@FXML
	TextField addnewartistfield;
	
	
	
	 private static void setConnection() throws SQLException,ClassNotFoundException {
	        String url = "jdbc:mysql://localhost/musicplayer";
	        String uname = "root";
	       String pwd = "#Jathanmv02";
	        try {
	            Class.forName("com.mysql.jdbc.Driver");

	            connection = DriverManager.getConnection(url, uname, pwd);
	            System.out.println("Connection succesful");

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }




	        }
	
	
	@FXML
	private void insertokfun() throws Exception
	{
		if(addnewtrlabel.getText()=="Add new artist")
		{
			String artistnm=addnewartistfield.getText();
			
	
			
	      if(artistnm.equals(""))
	      {
	    	  Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.initStyle(StageStyle.UTILITY);
	            alert.setTitle(":(");
	            alert.setHeaderText(null);
	            
	            alert.setContentText("Please enter the artist name");
	        
	            alert.showAndWait();
	      }
	      else
	      {  
	    	 
	    	  String query="SELECT artist_id FROM artists WHERE artist_name=?";
				setConnection();
				PreparedStatement statement=connection.prepareStatement(query);
		        statement.setString(1,artistnm);
		        ResultSet rs=statement.executeQuery();
	        
	        
	        if(rs.next())
	        {
	        	
	        	Alert alertt = new Alert(Alert.AlertType.INFORMATION);
	            alertt.initStyle(StageStyle.UTILITY);
	            alertt.setTitle(":(");
	            alertt.setHeaderText(null);
	            
	            alertt.setContentText("Artist with this name is already present");
	        
	            alertt.showAndWait();
	        	
	        }
	        else
	        {
	        	

	        	 String query1 = "INSERT INTO artists(artist_name) VALUES(?);";
		            PreparedStatement statement1 = connection.prepareStatement(query1);
		            statement1.setString(1,artistnm);                            
		                                     

		            int affectedRows = statement1.executeUpdate();
		            System.out.println("Inserted new artist to  the DB " + affectedRows);
		            

		            Alert alertt2 = new Alert(Alert.AlertType.INFORMATION);  
                   alertt2.initStyle(StageStyle.UTILITY);
                   alertt2.setTitle("Yayy !");
                   alertt2.setHeaderText(null);
                   alertt2.setContentText("Successfully added "+artistnm+"to the database");
                   alertt2.showAndWait();
	        	
	        }
			
	      }
			
		}
		else if(addnewtrlabel.getText()=="Insert new Album")
		{
			String albnmm = addnewalbmfield.getText();
			String artalbnmm=addartistnewalbumfield.getText();
			
			if(albnmm.equals("")||artalbnmm.equals(""))
			{
				 Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.initStyle(StageStyle.UTILITY);
		            alert.setTitle(":(");
		            alert.setHeaderText(null);
		            
		            if(albnmm.equals(""))
		            alert.setContentText("Please enter the album name");
		            
		            if(artalbnmm.equals(""))
		            	alert.setContentText("Please enter the artist name");
		        
		            alert.showAndWait();
			}
			else
			{

				String query="SELECT album_id from album where album_name =? ";
				setConnection();
				PreparedStatement statement=connection.prepareStatement(query);
		        statement.setString(1,albnmm);
		        ResultSet rs=statement.executeQuery();
		        
		        if(rs.next())
		        {
		        	 Alert alert = new Alert(Alert.AlertType.INFORMATION);  //This works like JOptionpane.
	                 alert.initStyle(StageStyle.UTILITY);
	                 alert.setTitle("Yayy !");
	                 alert.setHeaderText(null);
	                 alert.setContentText("This Album  is already present");
	                 alert.showAndWait();
			            
		        }
		        else
		        {

		        	 String query1 = "INSERT INTO album(album_name) VALUES(?);";
			            PreparedStatement statement1 = connection.prepareStatement(query1);
			            statement1.setString(1,albnmm);                            
			                                     

			            int affectedRows = statement1.executeUpdate();
			            System.out.println("Inserted new artist to  the DB " + affectedRows);
		        }
			}
		}
		else
		{
			
			String gotsongnm=insertsongfield.getText();
			String gotabumnm=insertalbumnmfield.getText();
		     String queryed=String.format("SELECT album_id FROM album WHERE album_name=\"%s\"",gotsongnm);
			PreparedStatement pst=connection.prepareStatement(queryed);
	        ResultSet rsalbm=pst.executeQuery();
	        rsalbm.next();
	        int albmid=rsalbm.getInt(1);
			String gotpathnm=pathfieldinsert.getText();
			String gotartistnm=artistnminsert.getText();
			
			String queryed1=String.format("SELECT artist_id FROM artist_name WHERE artist_name=\"%s\"",gotartistnm);
			PreparedStatement pst1=connection.prepareStatement(queryed1);
	        ResultSet rsartist=pst.executeQuery();
	        rsartist.next();
	        int artisttid=rsartist.getInt(1);
			
			
			if(gotsongnm.equals("")||gotabumnm.equals("")||gotpathnm.equals("")||gotartistnm.equals(""))
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);  //This works like JOptionpane.
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Yayy !");
                alert.setHeaderText(null);
                alert.setContentText("Do not leave any of the field empty");
                alert.showAndWait();
			}
			else
			{
				String query="SELECT track_id from track where track_name =? ";
				setConnection();
				PreparedStatement statement=connection.prepareStatement(query);
		        statement.setString(1,gotsongnm);
		        ResultSet rs=statement.executeQuery();
		        
		        String query1="SELECT artist_id from artist where artist_name =? ";
				setConnection();
				PreparedStatement statement1=connection.prepareStatement(query);
		        statement1.setString(1,gotartistnm);
		        ResultSet rs1=statement.executeQuery();
		        
		        String query2="SELECT artist_id from artist where artist_name =? ";
				setConnection();
				PreparedStatement statement2=connection.prepareStatement(query);
		        statement2.setString(1,gotpathnm);
		        ResultSet rs2=statement.executeQuery();
		        
		        
		        
		        if(!rs1.next() || rs.next()|| rs2.next())
		        {

					Alert alert = new Alert(Alert.AlertType.INFORMATION);  
	                alert.initStyle(StageStyle.UTILITY);
	                alert.setTitle(" Oh!");
	                alert.setHeaderText(null);
	                
	                if(!rs1.next())
	                alert.setContentText("Artist name does not exist in db ");
	                
	                if(rs.next())
	                { 	Alert alert1 = new Alert(Alert.AlertType.INFORMATION);  
	                    alert1.initStyle(StageStyle.UTILITY);
	                    alert1.setTitle(" Oh!");
	                    alert1.setHeaderText(null);
	                	alert1.setContentText("Track already exits in db");
	                	
	                    alert1.showAndWait();
	                }
	                
	                if(rs2.next())
	                {
	                	Alert alert1 = new Alert(Alert.AlertType.INFORMATION);  
	                    alert1.initStyle(StageStyle.UTILITY);
	                    alert1.setTitle(" Oh!");
	                    alert1.setHeaderText(null);
	                	alert1.setContentText("Path already exists in db");
	                	
	                    alert1.showAndWait();
	                }
		        }
		        else
		        {
		        	String queryy1 = "INSERT INTO track(track_name,album_id,path_) VALUES(?,?,?);";
		            PreparedStatement statementt1 = connection.prepareStatement(queryy1);
		            statementt1.setString(1,gotsongnm);
		            statementt1.setInt(2,albmid);
		            statementt1.setString(3,gotpathnm);
		            
		            
		            String queryeded=String.format("SELECT track_id FROM track WHERE track_name=\"%s\"",gotsongnm);
					PreparedStatement pstt=connection.prepareStatement(queryed);
			        ResultSet rstrid=pstt.executeQuery();
			        rstrid.next();
			        int trid =rstrid.getInt(1);
		            

		            int affectedRows = statement1.executeUpdate();
		            System.out.println("Inserted new artist to  the DB " + affectedRows);
		           
		            String queryy2 = "INSERT INTO track_by(artist_id,track_id) VALUES(?,?);";
		            PreparedStatement statementt2 = connection.prepareStatement(queryy2);
		            statementt2.setInt(1,artisttid);
		            statementt1.setInt(2,trid);
		            
	            }
		        }
		       
		        
			}
			
			
	
			
		}
	
	@FXML
	private void fillartist()
	{
		
         insertsongfield.setVisible(false);
		
		
		insertalbumnmfield.setVisible(false);
		
		
		 pathfieldinsert.setVisible(false);
		
		
		artistnminsert.setVisible(false);
		
		
		addnewtrlabel.setVisible(true);
		
	
		 insertok.setVisible(true);
		 
		   edsongtable.setVisible(false);
			editdeletesongtable.setVisible(false);
			deletesbgbtn.setVisible(false);
			updatesngbtn.setVisible(false);
			updtartistidfield.setVisible(false);
			updateartistnmfield.setVisible(false);
			updtokbtn.setVisible(false);
			
			updtartlabel.setVisible(false);
			
	    	updtalbumfield.setVisible(false);
	    	updtalbmnmfield.setVisible(false);
	    	
	    	
	    	updtokbtn.setVisible(false);
	    	updttrackidfield.setVisible(false);
			updtokbtn.setVisible(false);
	    	
	    	
	    	updttracknamefield.setVisible(false);
	    	
	    	
	    	updtpathnmfield.setVisible(false);
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	  
	    	
	    	addnewalbmfield.setVisible(false);
	    	addnewtrlabel.setVisible(true);
	    	addnewtrlabel.setText("Add new artist");
	    	
	    	addartistnewalbumfield.setVisible(false);
		
	    	addnewartistfield.setVisible(true);
		
		
		
	}
	
	@FXML
	private void addalbuminit()
	{
		
		insertsongfield.setVisible(false);
		
		
		insertalbumnmfield.setVisible(false);
		
		
		 pathfieldinsert.setVisible(false);
		
		
		artistnminsert.setVisible(false);
		
		
		addnewtrlabel.setVisible(true);
		
	
		 insertok.setVisible(true);
		 
		   edsongtable.setVisible(false);
			editdeletesongtable.setVisible(false);
			deletesbgbtn.setVisible(false);
			updatesngbtn.setVisible(false);
			updtartistidfield.setVisible(false);
			updateartistnmfield.setVisible(false);
			updtokbtn.setVisible(false);
			
			updtartlabel.setVisible(false);
		
	    	updtalbumfield.setVisible(false);
	    	updtalbmnmfield.setVisible(false);
	    	
	    	
	    	updtokbtn.setVisible(false);
	    	updttrackidfield.setVisible(false);
			updtokbtn.setVisible(false);
	    	
	    	
	    	updttracknamefield.setVisible(false);
	    	
	    	
	    	updtpathnmfield.setVisible(false);
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	addnewalbmfield.setVisible(true);
	    	addnewtrlabel.setVisible(true);
	    	
	    	addartistnewalbumfield.setVisible(true);
	    	addnewartistfield.setVisible(false);
	    	addnewtrlabel.setText("Insert new Album");
	    	
		
		
		
	}
	
	@FXML
	private void fillnewsong()
	{
		
		
		insertsongfield.setVisible(true);
		
	
		insertalbumnmfield.setVisible(true);
		
		
		 pathfieldinsert.setVisible(true);
		
		
		artistnminsert.setVisible(true);
		
		
		addnewtrlabel.setVisible(true);
		
	
		 insertok.setVisible(true);
		 
		   edsongtable.setVisible(false);
			editdeletesongtable.setVisible(false);
			deletesbgbtn.setVisible(false);
			updatesngbtn.setVisible(false);
			updtartistidfield.setVisible(false);
			updateartistnmfield.setVisible(false);
			updtokbtn.setVisible(false);
			
			updtartlabel.setVisible(false);
			
	    	updtalbumfield.setVisible(false);
	    	updtalbmnmfield.setVisible(false);
	    	
	    
	    	updtokbtn.setVisible(false);
	    	updttrackidfield.setVisible(false);
			updtokbtn.setVisible(false);
	    	
	    	
	    	updttracknamefield.setVisible(false);
	    	
	    	
	    	updtpathnmfield.setVisible(false);
	    	
	    	
	    	
            addnewalbmfield.setVisible(false);
	    	
	    	addartistnewalbumfield.setVisible(false);
	    	addnewtrlabel.setText("Insert new Song");
	    	
	    	
		
		
	}
	
	
	
	
		
	
	@FXML
	private void initialisetoeditdelete() throws Exception
	{
		setConnection();
		
		
		insertsongfield.setVisible(false);
		
		
		insertalbumnmfield.setVisible(false);
		
		
		 pathfieldinsert.setVisible(false);
		
		
		artistnminsert.setVisible(false);
		
		
		addnewtrlabel.setVisible(false);
		
	
		 insertok.setVisible(false);
		 
			updtartistidfield.setVisible(false);
			updateartistnmfield.setVisible(false);
			updtokbtn.setVisible(false);
			
			updtartlabel.setVisible(false);
		
	    	updtalbumfield.setVisible(false);
	    	updtalbmnmfield.setVisible(false);
	    
	    	updtokbtn.setVisible(false);
	    	updttrackidfield.setVisible(false);
			updtokbtn.setVisible(false);
	    	
	    	
	    	updttracknamefield.setVisible(false);
	    	
	    	
	    	updtpathnmfield.setVisible(false);
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
            addnewalbmfield.setVisible(false);
	    	
	    	addartistnewalbumfield.setVisible(false);
	    	
		
		
		
		
		
		
		editdeletesongtable.setCellValueFactory(new PropertyValueFactory<Song,String>("songName"));
		
		String query=  "select track_name from track";
		ObservableList<Song> songListed = getallSongsed(query);
		
		edsongtable.refresh();
		edsongtable.setItems(songListed);
		
		edsongtable.setVisible(true);
		editdeletesongtable.setVisible(true);
		
		editdeletesongtable.setText("Songs");
		
		deletesbgbtn.setVisible(true);
		updatesngbtn.setVisible(true);
		
		
		
		
	}
	
	
	
	@FXML
	private void deletesongfromdb() throws Exception
	{
		setConnection();
		
		if(editdeletesongtable.getText()=="SONGS")
		{	
		Song songobj= edsongtable.getSelectionModel().getSelectedItem();
		String song = songobj.getSongName();
		
		String queryed=String.format("SELECT track_id FROM track WHERE track_name=\"%s\"",song);
		PreparedStatement pst=connection.prepareStatement(queryed);
        ResultSet rs=pst.executeQuery();
        rs.next();
        int trackIded=rs.getInt(1);
        
        String delqueryed ="Delete * from p_contains where track_id=?";
        String delqueryed1="Delete * from track where track_id=?";
        
        PreparedStatement statement3ed=connection.prepareStatement(delqueryed);
        statement3ed.setInt(1,trackIded);
        int rowsAffecteded=statement3ed.executeUpdate();
        System.out.println("The rows deleted are "+ rowsAffecteded);
        
        
        PreparedStatement statement3ed1=connection.prepareStatement(delqueryed1);
        statement3ed1.setInt(1,trackIded);
        int rowsAffecteded1=statement3ed1.executeUpdate();
        System.out.println("The rows deleted are "+ rowsAffecteded1);
        

		editdeletesongtable.setCellValueFactory(new PropertyValueFactory<Song,String>("songName"));
		String query=  "select track_name from track";
		ObservableList<Song> songListed = getallSongsed(query);
		edsongtable.setItems(songListed);
		
		
		}
		else if(editdeletesongtable.getText()=="Albums")
		{
			Song curr_album = edsongtable.getSelectionModel().getSelectedItem();
	        String album = curr_album.getAlbumName();
	        
	        
	        String queryed=String.format("SELECT album_id FROM album where album_name=\"%s\"",album);
			PreparedStatement pst=connection.prepareStatement(queryed);
	        ResultSet rs=pst.executeQuery();
	        rs.next();
	        int albumIded=rs.getInt(1);
	        
	        String delqueryed = "Delete * from p_contains where track_id in(select track_id from track where album_id=?)";
	        
	        PreparedStatement statement3ed=connection.prepareStatement(delqueryed);
	        statement3ed.setInt(1,albumIded);
	        int rowsAffecteded=statement3ed.executeUpdate();
	        System.out.println("The rows deleted are "+ rowsAffecteded);
	        
	        String delqueryed1 = "Delete * from album where album_id=?";
	        
	        PreparedStatement statement3ed2=connection.prepareStatement(delqueryed);
	        statement3ed2.setInt(1,albumIded);
	        int rowsAffecteded2=statement3ed.executeUpdate();
	        System.out.println("The rows deleted are "+ rowsAffecteded2);
	        
	       editdeletesongtable.setCellValueFactory(new PropertyValueFactory<Song,String>("albumName"));
	       ObservableList<Song> albumList = AlbumController.getallAlbums();
	       edsongtable.setItems(albumList);
		}
		else
		{   
			Song curr_artist = edsongtable.getSelectionModel().getSelectedItem();
	        String artist = curr_artist.getArtistName();
	        

	        String queryed=String.format("SELECT artist_id FROM artists where artist_name=\"%s\"",artist);
			PreparedStatement pst=connection.prepareStatement(queryed);
	        ResultSet rs=pst.executeQuery();
	        rs.next();
	        int artistIded=rs.getInt(1);
	        
            String delqueryed = "Delete * from p_contains where track_id in(Select track_id from track_by where artist_id=?)";
	        
	        PreparedStatement statement3ed=connection.prepareStatement(delqueryed);
	        statement3ed.setInt(1,artistIded);
	        int rowsAffecteded=statement3ed.executeUpdate();
	        System.out.println("The rows deleted are "+ rowsAffecteded);
	        

            String delqueryed1 = "Delete * from track where track_id in(Select track_id from track_by where artist_id=?)";
	        
	        PreparedStatement statement4ed=connection.prepareStatement(delqueryed1);
	        statement4ed.setInt(1,artistIded);
	        int rowsAffecteded1=statement4ed.executeUpdate();
	        System.out.println("The rows deleted are "+ rowsAffecteded1);
	        
            String delqueryed2 = "Delete * from artists where artist_name=?";
	        
	        PreparedStatement statement5ed=connection.prepareStatement(delqueryed2);
	        statement4ed.setString(1,artist);
	        int rowsAffecteded2=statement5ed.executeUpdate();
	        System.out.println("The rows deleted are "+ rowsAffecteded2);
	        
	        editdeletesongtable.setCellValueFactory(new PropertyValueFactory<>("artistName"));
	        ObservableList<Song> artistList = ArtistController.getallArtist();
	        edsongtable.setItems(artistList);
            
	        
	        
	        
	        
	        
	        
	        

		}
        
        
        
	}
	
	@FXML
	private void editdelartistinitialise() throws Exception
	{   
		
      insertsongfield.setVisible(false);
		
		
		insertalbumnmfield.setVisible(false);
		
		
		 pathfieldinsert.setVisible(false);
		
		
		artistnminsert.setVisible(false);
		
		
		addnewtrlabel.setVisible(false);
		
	
		 insertok.setVisible(false);
		 
			updtartistidfield.setVisible(false);
			updateartistnmfield.setVisible(false);
			updtokbtn.setVisible(false);
			
			updtartlabel.setVisible(false);
			
	    	updtalbumfield.setVisible(false);
	    	updtalbmnmfield.setVisible(false);
	    	
	    	updtokbtn.setVisible(false);
	    	updttrackidfield.setVisible(false);
			updtokbtn.setVisible(false);
	    	
	    	
	    	updttracknamefield.setVisible(false);
	    	
	    	
	    	updtpathnmfield.setVisible(false);
	    	
	    	
	    	
            addnewalbmfield.setVisible(false);
	    	
	    	addartistnewalbumfield.setVisible(false);
		
		
		
		
		
		
		
		
		
		editdeletesongtable.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        ObservableList<Song> artistList = ArtistController.getallArtist();
        edsongtable.setItems(artistList);
        editdeletesongtable.setText("Artists");
        
        edsongtable.setVisible(true);
		editdeletesongtable.setVisible(true);
		
		deletesbgbtn.setVisible(true);
		updatesngbtn.setVisible(true);
        
        
	}
	
	@FXML
	private void eddelalbumsfunction() throws Exception
	{   
		
       insertsongfield.setVisible(false);
		
		
		insertalbumnmfield.setVisible(false);
		
		
		 pathfieldinsert.setVisible(false);
		
		
		artistnminsert.setVisible(false);
		
		
		addnewtrlabel.setVisible(false);
		
	
		 insertok.setVisible(false);
		 
			updtartistidfield.setVisible(false);
			updateartistnmfield.setVisible(false);
			updtokbtn.setVisible(false);
			
			
			
	    	updtalbumfield.setVisible(false);
	    	updtalbmnmfield.setVisible(false);
	    
	    	updtokbtn.setVisible(false);
	    	updttrackidfield.setVisible(false);
			updtokbtn.setVisible(false);
	    	
	    	
	    	updttracknamefield.setVisible(false);
	    	
	    	
	    	updtpathnmfield.setVisible(false);
	    	
	    	
	    	
	    	
            addnewalbmfield.setVisible(false);
	    	
	    	addartistnewalbumfield.setVisible(false);
		
		
		
		
		editdeletesongtable.setCellValueFactory(new PropertyValueFactory<>("albumName"));

        ObservableList<Song> albumList = AlbumController.getallAlbums();
        edsongtable.setItems(albumList);
        editdeletesongtable.setText("Albums");
        
        edsongtable.setVisible(true);
		editdeletesongtable.setVisible(true);
		
		deletesbgbtn.setVisible(true);
		updatesngbtn.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	 public static ObservableList<Song> getallSongsed(String query) throws SQLException, ClassNotFoundException {

         setConnection();
         PreparedStatement prepmnt= null;
try {
         prepmnt = connection.prepareStatement(query);
         ResultSet rs;
         rs = prepmnt.executeQuery(query);
         ObservableList<Song> allSongData=getSongObjectsed(rs);
         return allSongData;

} catch (SQLException ex) {
         System.err.println(ex.getMessage());
     }

return null;
	 }
	 
	 private static ObservableList<Song> getSongObjectsed(ResultSet rs) {
		    try {
		        ObservableList<Song> songData = FXCollections.observableArrayList();
		        while (rs.next()) {
		            Song song = new Song();
		            song.setSongName(rs.getString("track_name"));
		            System.out.println(rs.getString("track_name"));
		            songData.add(song);
		        }
		        return songData;

		} catch (SQLException throwables) {
		        throwables.printStackTrace();
		    }
		return null;
		}
	
	 @FXML
	 private void updaterouter() throws Exception
	 {      
		 
		 if(editdeletesongtable.getText()=="Artists")
		 {
		    edsongtable.setVisible(false);
			editdeletesongtable.setVisible(false);
			deletesbgbtn.setVisible(false);
			updatesngbtn.setVisible(false);
			updtartistidfield.setVisible(true);
			updateartistnmfield.setVisible(true);
			updtokbtn.setVisible(true);
			updatelabel.setVisible(true);
			updtartlabel.setVisible(true);
			updtartnmlabel.setVisible(true);
			
			
			Song curr_artist = edsongtable.getSelectionModel().getSelectedItem();
	        String artist = curr_artist.getArtistName();
	        
	        String queryed=String.format("SELECT artist_id FROM artists where artist_name=\"%s\"",artist);
			PreparedStatement pst=connection.prepareStatement(queryed);
	        ResultSet rs=pst.executeQuery();
	        rs.next();
	        int artistIded=rs.getInt(1);
	        
	        updtartistidfield.setText(String.valueOf(artistIded));
	        updateartistnmfield.setText(artist);
	        updtartlabel.setText("update artists");
		 } 
		 else if(editdeletesongtable.getText()=="Albums")   
		 {   
	        edsongtable.setVisible(false);
			editdeletesongtable.setVisible(false);
			deletesbgbtn.setVisible(false);
			updatesngbtn.setVisible(false);
	    	albumnmfield.setVisible(true);
	    	updtalbumfield.setVisible(true);
	    	updtalbmnmfield.setVisible(true);
	    	updtalbumlabel.setVisible(true);
	    	updtalbmnmlabel.setVisible(true);
	    	updtokbtn.setVisible(true);
	        
	    	Song curr_album = edsongtable.getSelectionModel().getSelectedItem();
	        String album = curr_album.getAlbumName();
	        
	        
	        String queryed2=String.format("SELECT album_id FROM album where album_name=\"%s\"",album);
			PreparedStatement pst2=connection.prepareStatement(queryed2);
	        ResultSet rs2=pst2.executeQuery();
	        rs2.next();
	        int albumIded=rs2.getInt(1);
	        
	        updtalbumfield.setText(String.valueOf(albumIded));
	    	updtalbmnmfield.setText(album);
	    	
	    	updtartlabel.setText("update album");
		 }
		 else
		 { 
	    	
	    	
	    	edsongtable.setVisible(false);
			editdeletesongtable.setVisible(false);
			deletesbgbtn.setVisible(false);
			updatesngbtn.setVisible(false);
			updttrackidfield.setVisible(true);
			updtokbtn.setVisible(true);
	    	
	    	
	    	updttracknamefield.setVisible(true);
	    	
	    	
	    	updtpathnmfield.setVisible(true);
	    	
	    	
	    	updttrackidlabel.setVisible(true);
	    	
	    	
	    	updttracknmlabel.setVisible(true);
	    	
	    	updtpathlbl.setVisible(true);
	    	
	    	Song songobj= edsongtable.getSelectionModel().getSelectedItem();
			String song = songobj.getSongName();
			
			String queryed1=String.format("SELECT track_id FROM track WHERE track_name=\"%s\"",song);
			PreparedStatement pst1=connection.prepareStatement(queryed1);
	        ResultSet rs1=pst1.executeQuery();
	        rs1.next();
	        int trackIded=rs1.getInt(1);
	        
	        String queryed5=String.format("SELECT path_ FROM track WHERE track_name=\"%s\"",song);
			PreparedStatement pst5=connection.prepareStatement(queryed5);
	        ResultSet rs5=pst5.executeQuery();
	        rs5.next();
	        String pathnn = rs5.getString(1);
	        
	        
	        
	        updttracknamefield.setText(song);
	        updttrackidfield.setText(String.valueOf(trackIded));
	        updtpathnmfield.setText(pathnn);
	        
	        updtartlabel.setText("update track");
		 }
	        
			
	 }
	 
	 @FXML
	 private void editupdatefun() throws Exception
	 {
		 
		 
		 if(updtartlabel.getText()=="update album")
		 {   
			 
			 Integer albmnid =Integer.parseInt(updtalbumfield.getText());
		     String albmnm=updtalbmnmfield.getText();
		     Song curr_album = edsongtable.getSelectionModel().getSelectedItem();
		     String album = curr_album.getAlbumName();
		     
		     
		     String delqueryed ="update album set album_id=? where album_name=?" ;
		        
		        PreparedStatement statement3ed=connection.prepareStatement(delqueryed);
		        statement3ed.setInt(1,albmnid);
		        statement3ed.setString(2,album);
		        
		        int rowsAffecteded=statement3ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded);
		        
		     String delqueryed2 = "update album set album_name=? where album_name=?";
		    	
		        PreparedStatement statement4ed=connection.prepareStatement(delqueryed);
		        statement3ed.setString(1,albmnm);
		        statement3ed.setString(2,album);
		        
		        int rowsAffecteded2=statement4ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded);		 
		    		 
			 
			 
		 }
		 else if(updtartlabel.getText()=="update track")
		 {
			 
			 String tracknm = updttracknamefield.getText();
			 
			 int trackid = Integer.parseInt(updttrackidfield.getText());
			 
			 String pathnm = updtpathnmfield.getText();
			 
			 Song songobj= edsongtable.getSelectionModel().getSelectedItem();
			 String song = songobj.getSongName();
			 
			
			 
			 String queryed1=String.format("SELECT track_id FROM track WHERE track_name=\"%s\"",song);
				PreparedStatement pst1=connection.prepareStatement(queryed1);
		        ResultSet rs1=pst1.executeQuery();
		        rs1.next();
		        int trackIded=rs1.getInt(1);
		        
		        String delqueryed = "update recentley_played set track_id=? where track_id=?" ;
		        PreparedStatement statement3ed=connection.prepareStatement(delqueryed);
		        statement3ed.setInt(1,trackid);
		        statement3ed.setInt(2,trackIded);
		        int rowsAffecteded=statement3ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded); 
		        
                String delqueryed2 = "update favourites set track_id=? where track_id=? " ;
		        PreparedStatement statement4ed=connection.prepareStatement(delqueryed2);
		        statement4ed.setInt(1,trackid);
		        statement4ed.setInt(2,trackIded);
		        int rowsAffecteded4=statement4ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded4); 
		        
		        
                String delqueryed3 = "update p_contains set track_id=? where track_id=?";
		        PreparedStatement statement5ed=connection.prepareStatement(delqueryed3);
		        statement5ed.setInt(1,trackid);
		        statement5ed.setInt(2,trackIded);
		        int rowsAffecteded5=statement5ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded5);
		        
		        
		        String delqueryed4 = "update track_by set track_id=? where track_id=?" ;
		        PreparedStatement statement6ed=connection.prepareStatement(delqueryed4);
		        statement6ed.setInt(1,trackid);
		        statement6ed.setInt(2,trackIded);
		        int rowsAffecteded6=statement6ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded5);
		        
		        String delqueryed5 = "update track set track_id=? where track_id=?" ;
		        PreparedStatement statement7ed=connection.prepareStatement(delqueryed5);
		        statement7ed.setInt(1,trackid);
		        statement7ed.setInt(2,trackIded);
		        int rowsAffecteded7=statement7ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded7);
		        
		        String delqueryed6 = "update track set track_name=? where track_name=?" ;
		        PreparedStatement statement8ed=connection.prepareStatement(delqueryed6);
		        statement8ed.setString(1,tracknm);
		        statement8ed.setString(2,song);
		        int rowsAffecteded8=statement7ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded8);
		        
		        String delqueryed7 = "update track set path=? where track_name=?" ;
		        PreparedStatement statement9ed=connection.prepareStatement(delqueryed7);
		        statement9ed.setString(1,pathnm);
		        statement9ed.setString(2,song);
		        int rowsAffecteded9=statement7ed.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded9);
		        
		 }
		 else
		 {
			 
			 
				Song curr_artist = edsongtable.getSelectionModel().getSelectedItem();
		        String artist = curr_artist.getArtistName();
		        
		        String queryed=String.format("SELECT artist_id FROM artists where artist_name=\"%s\"",artist);
				PreparedStatement pst=connection.prepareStatement(queryed);
		        ResultSet rs=pst.executeQuery();
		        rs.next();
		        int artistIded=rs.getInt(1);
		        
		        int artistidd =Integer.parseInt(updtartistidfield.getText());
		        String artistnm =updateartistnmfield.getText();
		        
		        
		        String upartistq = "update track_by set artist_id =? where artist_id=?" ;
		        PreparedStatement uprep=connection.prepareStatement(upartistq);
		        uprep.setInt(1,artistidd);
		        uprep.setInt(2,artistIded);
		        int rowsAffecteded10=uprep.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded10);
		        
		        String upartistq1 = "update track set artist_id =? where artist_id=?" ;
		        PreparedStatement uprep1=connection.prepareStatement(upartistq1);
		        uprep1.setInt(1,artistidd);
		        uprep1.setInt(2,artistIded);
		        int rowsAffecteded11=uprep1.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded11);
		        
		        String upartistq2 = "update artist set artist_id =? where artist_id=?" ;
		        PreparedStatement uprep2=connection.prepareStatement(upartistq2);
		        uprep2.setInt(1,artistidd);
		        uprep2.setInt(2,artistIded);
		        int rowsAffecteded12=uprep2.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded12);
		        
		        String upartistq3 = "update artist set artist _name=? where artist_name=?" ;
		        PreparedStatement uprep3=connection.prepareStatement(upartistq3);
		        uprep.setString(1,artistnm);
		        uprep.setString(2,artist);
		        int rowsAffecteded13=uprep3.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded13);
		        
		        String upartistq4 = "update track_by set artist_name=? where artist_name=?" ;
		        PreparedStatement uprep4=connection.prepareStatement(upartistq4);
		        uprep.setString(1,artistnm);
		        uprep.setString(2,artist);
		        int rowsAffecteded14=uprep4.executeUpdate();
		        System.out.println("The rows deleted are "+ rowsAffecteded14);
		        
		        
			 
		 }
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
			 
			 
		 
		 
		 
	 }
	

}
