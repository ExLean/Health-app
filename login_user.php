<?php
$host = "localhost";  
$user = "root";  
$pass = '';  
$db_name = "health_app";  

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$username = $_POST['user_name'];
	$password = $_POST['user_password'];
	if($username == '' || $password == ''){
		echo "fail";
		exit;
	}
	
	$con = mysqli_connect($host, $user, $pass, $db_name) or die("Unable to Connect");
	
	$query = "SELECT * FROM user WHERE username = '$username' AND password = '$password'";
	$result = mysqli_query($con, $query);
	$data = mysqli_fetch_array($result);
	if(isset($data)){
		echo "login";
	}else{
		echo "fail";
	}
	mysqli_close($con);
}
?>