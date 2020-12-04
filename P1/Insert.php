<?php

// Create connection
$db = pg_connect (" host=java2010rev.cfqzgdfohgof.us-east-2.rds.amazonaws.com port=5432 dbname=postgres user=jenny77 password=zeus1418 ");


if(!$db) {
    echo "Error : Unable to open database\n";
 } else {
    echo "Opened database successfully\n";
 }

    $sdate = date("m-d-y");
    $edate = date("m-d-y");
    $etime = date("h:m");
    $tmiss = date("h:m");
    $preap = 'yes' || 'no';
    $eid = intval($eid);
    $cgra = 1234;
    $ramo = 1234;
    $urg = 'yes' || 'no';
    $fstat = "";
    $sapp = 'yes' || 'no'; 
    $dhapp = 'yes' || 'no';   
    $bco = 'yes' || 'no';
    $appre = "";
    $denre = "";




 $query = pg_query($db, "INSERT  INTO jensquared.form (eid, submission_date, event_type, event_name, event_description, event_date, event_time, time_missed, event_location, event_cost, grade_format, current_grade, reimbursement_amount, pre_approval, urgent, form_status, supervisor_approval, department_head_approval, benefit_co_approval, approval_reponse, denial_response) VALUES ('$eid','$sdate', '$etype', '$ename', '$edes', '$edate', '$etime', '$tmiss', '$eloc', '$ecost', '$gfor', '$cgra', '$ramo', '$preap', '$urg', '$fstat', '$sapp', '$dhapp', '$bco', '$appre', '$denre');");
    
//  $result = pg_query($query);

    if ( $query ) {
        echo  "Record Successfully Added!";
    }

?>