<?php
   $fname = $_POST["fname"];
   $lname = $_POST["lname"];
   $addr = $_POST["address"];
   $city = $_POST["city"];
   $state = $_POST["state"];
   $zip = $_POST["zip"];
   $amt = $_POST["amount"];
   $output = exec("python3 /home/pi/dmr_pur_comm/create_customer_account.py '$fname' '$lname' '$addr' '$city' '$state' '$zip' '$amt'");
   echo $output;
?>
