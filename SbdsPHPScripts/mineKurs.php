
<?php

require "init.php";



if (mysqli_connect_errno())
  {
  
  }
$user_ID=$_POST["brukerId"];

$sql="SELECT * FROM Kurs AS K,Paakurs AS P WHERE K.KursNr LIKE P.KursNr AND P.Nr='$user_ID'";



$result=mysqli_query($con,$sql);

$response=array();

while($row = mysqli_fetch_array($result)){

  array_push($response, array("KursNr"=>(int)$row[0],"Kursnavn"=>$row[1],"Dag"=>$row[2],"Kursholder"=>$row[3]));

}
  echo json_encode(array("Kurs"=>$response));

mysqli_close($con);



?>