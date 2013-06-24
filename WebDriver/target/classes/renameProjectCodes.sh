#!/bin/bash

unzip ./$1

## if run on a linux machine, rather than a mac the structure of the 
## sed argments is slightly different
## sed -i 's/AMEXCOMPARE/AMEXCOMPA/g' ./entities.xml 


sed -i '' 's/AMEXCOMPARE/AMEXCOMPA/g' ./entities.xml 
sed -i '' 's/DELLSNASHAREVOICE/DELLSNASH/g' ./entities.xml 
sed -i '' 's/SITECOREKIT/SITECOREK/g' ./entities.xml 
sed -i '' 's/SOLSETWEBOPS/WEBOPS/g' ./entities.xml 

filename=$1
filename2=${filename%.*}

echo $filename2

zip ${filename2}_modified.zip ./entities.xml ./activeobjects.xml



