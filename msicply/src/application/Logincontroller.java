package application;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

import javax.swing.JOptionPane;

public class Logincontroller {
	
	
	@FXML
	private JFXButton signintop;
	
	@FXML
	private JFXButton signuptop;
	
	@FXML
	private TextField signinusernm;
	
	@FXML
	private PasswordField passsignin;
	
	@FXML
	private JFXButton signinbtn;
	
	@FXML
	private JFXButton frgtbtn;
	
	@FXML
	private TextField signupusrnm;
	
	@FXML
	private TextField signupemail;
	
	@FXML
	private PasswordField signupass;
	
	@FXML
	private TextField signupsecretkey;
	
	@FXML
	private JFXButton Goo;
	
	@FXML
	private TextField frgtusrnm;
	
	@FXML
	private TextField frgtscrtkey;
	
	@FXML
	private JFXButton createnewpass;
	
	@FXML
	private PasswordField newpass;
	
	@FXML
	private JFXButton finish;
	
	@FXML
	private JFXButton closee;
	
	
	
	
	 public static Connection connection=null;

	  public String useremail="";
	    public String username="";
	    public static FXMLLoader loader;
	    public static Controller controller;
	    
	    
	    
	    @FXML
	    private void LoginGo(ActionEvent event) throws Exception{

	        String em=signinusernm.getText();
	        String pw=passsignin.getText();

	        if(em.equals("")||pw.equals("")){
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.initStyle(StageStyle.UTILITY);
	            alert.setTitle(":(");
	            alert.setHeaderText(null);
	            if(em.equals(""))
	                alert.setContentText("Please enter the e-mail.");
	            else
	                alert.setContentText("Please enter the password.");
	            alert.showAndWait();
	        }
	        else {

	            try {
	                String query = "SELECT username FROM users WHERE email_id=? AND password=?";
	                setConnection();
	                PreparedStatement statement = connection.prepareStatement(query);
	                statement.setString(1, em);
	                statement.setString(2, pw);

	                ResultSet rs = statement.executeQuery();
	                rs.next();
	                if (!rs.getString(1).equals("")) {

	                    System.out.println("Login successfull");

	                    Alert alert = new Alert(Alert.AlertType.INFORMATION);  //This works like JOptionpane.
	                    alert.initStyle(StageStyle.UTILITY);
	                    alert.setTitle("Yayy !");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Login Successful" + "\n" + ":)");
	                    alert.showAndWait();

	                    loader = new FXMLLoader();
	                    loader.setLocation(getClass().getResource("home.fxml"));
	                    controller = loader.getController();
	                    useremail = signinusernm.getText();
	                    username = frgtusrnm.getText();
	                    controller.UnlockFunctionalities(true);
	                    controller.initData(useremail, rs.getString(1));

	                    Stage stage = (Stage) signinusernm.getScene().getWindow();
	                    stage.close();

	                } else {
	                    System.out.println("wrong cedentials");
	                    JOptionPane.showMessageDialog(null, "Couldn't login. check the credentials");
	                }

	            } catch (Exception e) {
	                //JOptionPane.showMessageDialog(null,e);

	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.initStyle(StageStyle.UTILITY);
	                alert.setTitle(":(");
	                alert.setHeaderText(null);
	                alert.setContentText("Wrong Credentials !" + "\n" + "Please try again.");
	                alert.showAndWait();
	            }
	        }

	    }
	    
	    public void SignUp() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
	        boolean ebool=true;
	        String username=signupusrnm.getText();
	        String email=signupemail.getText();
	        String password=signupass.getText();
	        String secretkey=signupsecretkey.getText();
	        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
	        alert1.initStyle(StageStyle.UTILITY);
	        alert1.setTitle("Uh-oh!");
	        alert1.setHeaderText(null);

	        ebool=emailCheck(email);




	       /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.initStyle(StageStyle.UTILITY);
	        alert.setTitle("Yayy !");
	        alert.setHeaderText(null);
	        alert.setContentText("You're signed up."+"\n"+"\n"+"\n"+"use the secret kry provided in case you forgot the password");
	        alert.showAndWait(); */

	        if(!username.equals("")&&!email.equals("")&&!password.equals("")&&!secretkey.equals("")&&ebool) {

	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource("home.fxml"));
	            Controller controller = loader.getController();
	            controller.initDataSignUp(username, email, password, secretkey);
	            controller.UnlockFunctionalities(false);
	            signupusrnm.setVisible(false);
	            signupemail.setVisible(false);
	            signupass.setVisible(false);
	            signupsecretkey.setVisible(false);
	            signinbtn.setVisible(false);
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle(".");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Registered.Now please Sign in");
                alert.showAndWait();
	            
	            
	        }else{
	            if(ebool==false){
	                alert1.setContentText("Please enter a valid e-mail id.");
	                alert1.showAndWait();
	            }
	            if(username.equals("")){
	                alert1.setContentText("Please enter the username");
	                alert1.showAndWait();
	            }
	            if(email.equals("")){
	                alert1.setContentText("Please enter the email");
	                alert1.showAndWait();
	            }
	            if(password.equals("")){
	                alert1.setContentText("Please enter the password");
	                alert1.showAndWait();
	            }
	            if(secretkey.equals("")){
	                alert1.setContentText("Please enter the secret-key");
	                alert1.showAndWait();
	            }
	        }

	    }
	    public boolean emailCheck(String e){
	       if(!e.contains("@")){
	           return false;
	       }else{
	           if(!e.substring((e.length()-4),e.length()).equals(".com")){//||!e.substring((e.length()-3),e.length()).equals(".in")){
	               return false;
	           }else{
	               String s[]=e.split("@");
	               if(s[0]==""){
	                   return false;
	               }

	           }
	       }

	       return true;
	    }
	    
	    
	    public static void setConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
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
	    void signindis(ActionEvent event) {
              
	    	signupusrnm.setVisible(false);
	    	signupemail.setVisible(false);
	    	signupass.setVisible(false);
	    	signupsecretkey.setVisible(false);
	    	signinbtn.setVisible(false);
	    	signinusernm.setVisible(true);
	    	passsignin.setVisible(true);
	    	Goo.setVisible(true);
	    	frgtbtn.setVisible(true);
	    	frgtusrnm.setVisible(false);
	    	frgtscrtkey.setVisible(false);
	    	createnewpass.setVisible(false);
	    	newpass.setVisible(false);
	    	finish.setVisible(false);
	        
	    }
	    
	    @FXML
	    void signupdis(ActionEvent event) {
            
	    	signupusrnm.setVisible(true);
	    	signupemail.setVisible(true);
	    	signupass.setVisible(true);
	    	signupsecretkey.setVisible(true);
	    	signinbtn.setVisible(true);
	    	signinusernm.setVisible(false);
	    	passsignin.setVisible(false);
	    	Goo.setVisible(false);
	    	frgtbtn.setVisible(false);
	    	frgtusrnm.setVisible(false);
	    	frgtscrtkey.setVisible(false);
	    	createnewpass.setVisible(false);
	    	newpass.setVisible(false);
	    	finish.setVisible(false);
	        
	    }
	    
	    @FXML
	    void frgtpassmethod(ActionEvent event) {
             
	    	
	    	if(signinusernm.getText().equals(""))
	        {
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.initStyle(StageStyle.UTILITY);
	            alert.setTitle("");
	            alert.setHeaderText(null);
	            alert.setContentText("Please enter your email");
	            alert.showAndWait();
	        }else {
	    	signupusrnm.setVisible(false);
	    	signupemail.setVisible(false);
	    	signupass.setVisible(false);
	    	signupsecretkey.setVisible(false);
	    	signinbtn.setVisible(false);
	    	signinusernm.setVisible(false);
	    	passsignin.setVisible(false);
	    	Goo.setVisible(false);
	    	frgtbtn.setVisible(false);
	    	frgtusrnm.setVisible(true);
	    	frgtscrtkey.setVisible(true);
	    	createnewpass.setVisible(true);
	    	newpass.setVisible(false);
	    	finish.setVisible(false);
	        }
	        
	    }
	    
	    public void createNewPswd() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {

	        String userName=frgtusrnm.getText();
	        String secretKey=frgtscrtkey.getText();

	        String query="SELECT username FROM users WHERE username=? AND secret_key=?";

	        setConnection();
	        PreparedStatement statement=connection.prepareStatement(query);
	        statement.setString(1,userName);
	        statement.setString(2,secretKey);
	        ResultSet rs1=statement.executeQuery();
	        if(rs1.next()){
	            frgtscrtkey.setVisible(false);
	            //frgt_username.setVisible(false);
	            createnewpass.setVisible(false);

	            newpass.setVisible(true);
	            finish.setVisible(true);

	        }else{
	            //JOptionPane.showMessageDialog(null,"Wrong username/secret key");
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.initStyle(StageStyle.UTILITY);
	            alert.setTitle("");
	            alert.setHeaderText(null);
	            alert.setContentText("Please enter correct username/secret-key");
	            alert.showAndWait();
	        }

	    }
	    
	    public void finishnewpass() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {


	        String userName=frgtusrnm.getText();
	        String NewPwd=newpass.getText();

	        String query="UPDATE users u SET u.password=? WHERE u.username=?";
	        //setConnection();
	        PreparedStatement statement=connection.prepareStatement(query);
	        statement.setString(1,NewPwd);
	        statement.setString(2,userName);
	        int affectedRows=statement.executeUpdate();
	        System.out.println("password change Affected rows are "+affectedRows);

	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.initStyle(StageStyle.UTILITY);
	        alert.setTitle("Done");
	        alert.setHeaderText(null);
	        alert.setContentText("Password changed successfully"+"\n"+"\n"+"Now sign-in using new password");
	        alert.showAndWait();

	        FXMLLoader loader=new FXMLLoader();
	        loader.setLocation(getClass().getResource("home.fxml"));
	        Controller controller=loader.getController();
	        Stage stage=(Stage)signinusernm.getScene().getWindow();
	        stage.close();



	    }
	    
	    public void closs()
	    {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home.fxml"));
            Stage stage = (Stage)signinusernm.getScene().getWindow();
            stage.close();
	    }
	    
	    
	    
	    

}
