#! /usr/bin/awk -f

#awk command to wrangle data
BEGIN{}
{ 
    if($7-$6==$8){
        print $1;
    }
    else if($11-$10==$12){
        print $2;
   }
   
}
