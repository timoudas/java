#! /usr/bin/awk -f
  
#awk command to wrangle data

BEGIN{
    NR==FNR{
        name[$1]++;
        next
    }
    $1 in name
}
