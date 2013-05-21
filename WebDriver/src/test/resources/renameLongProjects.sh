#!/bin/bash
sed -i '' 's/AMEXCOMPARE/AMEXCOMPA/g' ./entities.xml 
sed -i '' 's/DELLSNASHAREVOICE/DELLSNASH/g' ./entities.xml 
sed -i '' 's/SITECOREKIT/SITECOREK/g' ./entities.xml 
sed -i '' 's/SOLSETWEBOPS/SOLSETWEB/g' ./entities.xml 

mv /var/lib/jira/import/attachments/SOLSETWEBOPS /var/lib/jira/import/attachments/SOLSETWEB
