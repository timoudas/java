#! /usr/bin/awk -f

#awk command to wrangle data
BEGIN{}
{ 
    if($7-$6==$8){
    }
    else if($11-$10==$12){
   }
   else{
       print $0;
   }
   
}
