#!/bin/bash

unzip ./$1

sed -i '' 's/AMEXCOMPARE/AMEXCOMPA/g' ./entities.xml 
sed -i '' 's/DELLSNASHAREVOICE/DELLSNASH/g' ./entities.xml 
sed -i '' 's/SITECOREKIT/SITECOREK/g' ./entities.xml 
sed -i '' 's/SOLSETWEBOPS/WEBOPS/g' ./entities.xml 

filename=$1
filename2=${filename%.*}

echo $filename2

zip ${filename2}_modified.zip ./entities.xml ./activeobjects.xml



